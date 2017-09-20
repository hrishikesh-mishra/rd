package com.hrishikeshmishra.logger.sample1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author hrishikesh.mishra
 */
public class Foo {
    static final Logger logger = LoggerFactory.getLogger(Foo.class);

    public void doIt() {
        logger.debug("Did it again!");
    }


}
