package com.hrishikeshmishra.logger.sample1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author hrishikesh.mishra
 */
public class MyApp1 {
    final static Logger logger = LoggerFactory.getLogger(MyApp1.class);

    public static void main(String[] args) {
        logger.info("Entering application.");

        Foo foo = new Foo();
        foo.doIt();
        logger.info("Exiting application.");
    }

}
