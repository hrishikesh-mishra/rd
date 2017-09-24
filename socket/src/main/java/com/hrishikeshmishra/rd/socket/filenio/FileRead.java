package com.hrishikeshmishra.rd.socket.filenio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author hrishikesh.mishra
 */
public class FileRead {

    public static void main(String[] args) throws IOException {

        RandomAccessFile aFile = new RandomAccessFile("/Users/hrishikesh.mishra/hrishi/codes/rd/socket/src/main/java/com/hrishikeshmishra/rd/socket/filenio/FileRead.java", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);

        while (bytesRead != -1) {

            System.out.println("Read: << " + bytesRead + " >>");
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();


    }
}
