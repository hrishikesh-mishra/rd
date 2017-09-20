package com.hrishikeshmishra.logger.sample1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hrishikesh.mishra
 */
public class HelloWorld1 {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld1");
        logger.debug("Hello world.");
    }


}
