package com.hrishikeshmishra.logger.sample1;

import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
 * @author hrishikesh.mishra
 */
public class SocketServerTest {

    public static void main(String[] args) throws JoranException, InterruptedException {
        String configFile = "/Users/hrishikesh.mishra/hrishi/codes/rd/logger/src/main/resources/socker-receiver.xml";

        if (configFile.endsWith(".xml")) {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            lc.reset();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            configurator.doConfigure(configFile);
        }

        Thread.sleep(Long.MAX_VALUE);


    }
}
