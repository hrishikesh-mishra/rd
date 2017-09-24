package com.hrishikeshmishra.rd.socket.nioserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author hrishikesh.mishra
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);


        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();

        }

    }
}
