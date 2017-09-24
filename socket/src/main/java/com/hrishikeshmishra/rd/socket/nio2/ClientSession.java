package com.hrishikeshmishra.rd.socket.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author hrishikesh.mishra
 */
public class ClientSession {

    private SelectionKey selectionKey;
    private SocketChannel channel;
    private ByteBuffer buffer;

    public ClientSession(SelectionKey selectionKey, SocketChannel channel) throws IOException {
        this.selectionKey = selectionKey;
        this.channel = (SocketChannel) channel.configureBlocking(false);
        this.buffer = ByteBuffer.allocate(64);
    }

    public void disconnect(){
        try{
            if (selectionKey !=null) selectionKey.cancel();
            if (channel == null) return;
            System.out.println("Bye bye : " + channel.getRemoteAddress());
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        try {

            int amountRead = -1 ;
            try{

                amountRead = channel.read((ByteBuffer) buffer.clear());
            }catch (Throwable r ){

            }

            if (amountRead == -1) disconnect();
            if (amountRead < 1) return;


            System.out.println("Sending back " + buffer.position() + " bytes ");
            buffer.flip();
            channel.write(buffer);


        }catch (Throwable e){

        }
    }
}
