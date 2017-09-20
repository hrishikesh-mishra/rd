package com.hrishikeshmishra.logger.sample1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hrishikesh.mishra
 */
public class SocketClientTest {


    static Logger logger1 = LoggerFactory.getLogger(SocketClientTest.class);
    static Logger logger2 = LoggerFactory.getLogger("logger2");

    public static void main(String[] args) throws InterruptedException {

        int counter = 0;
        while (true) {
            logger1.info(" Sending Data : {} ", counter);
           // Thread.sleep(1);

            //if (counter % 1000 == 0) {
                logger2.info("Processed Data : {} ", counter);
            //}
            counter++;
        }

    }
}
