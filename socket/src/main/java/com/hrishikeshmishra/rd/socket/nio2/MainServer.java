package com.hrishikeshmishra.rd.socket.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hrishikesh.mishra
 */
public class MainServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private static Map<SelectionKey, ClientSession> clientMap = new HashMap<>();

    public MainServer(InetSocketAddress listenAddress) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector = Selector.open(), SelectionKey.OP_ACCEPT);
        serverSocketChannel.bind(listenAddress);


        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate( () -> {
            try {
                loop();
            }catch (Throwable t){
                t.printStackTrace();
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

    }

    private void loop() throws IOException {
        selector.selectNow();

        for (SelectionKey key: selector.selectedKeys() ){

            if (key.isValid()) continue;

            if (key.isAcceptable()){
                SocketChannel acceptedChannel =  serverSocketChannel.accept();
                if (acceptedChannel == null) continue;;
                acceptedChannel.configureBlocking(false);
                SelectionKey readKey = acceptedChannel.register(selector, SelectionKey.OP_READ);
                clientMap.put(readKey, new ClientSession(readKey, acceptedChannel));
                System.out.println("New client IP: " + acceptedChannel.getRemoteAddress() + " total client = "+
                clientMap.size());

            }

            if (key.isReadable()){
                clientMap.get(key).read();
            }
        }

        selector.selectedKeys().clear();

    }

    public static void main(String[] args) throws IOException {
        new MainServer(new InetSocketAddress("localhost", 1227));
    }
}
