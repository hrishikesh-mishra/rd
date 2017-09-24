package com.hrishikeshmishra.rd.socket.nio2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author hrishikesh.mishra
 */
public class DumpClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1227);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(55);
        outputStream.write(54);
        outputStream.write(53);
        outputStream.write(52);
        outputStream.write(51);
        outputStream.write(50);


        InputStream in = socket.getInputStream();
        int read ;

        while ((read = in.read()) != -1){
            System.out.println(read);
        }


    }
}
