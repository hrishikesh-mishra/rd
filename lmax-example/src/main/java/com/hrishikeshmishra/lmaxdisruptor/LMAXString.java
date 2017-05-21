package com.hrishikeshmishra.lmaxdisruptor;

import com.hrishikeshmishra.lmaxdisruptor.evenproducer.StringEventProducer;
import com.hrishikeshmishra.lmaxdisruptor.event.StringEvent;
import com.hrishikeshmishra.lmaxdisruptor.eventhandlers.StringEventHandler;
import com.hrishikeshmishra.lmaxdisruptor.factories.StringEventFactory;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author hrishikesh.mishra
 */
public class LMAXString {

    private Disruptor<StringEvent> disruptor;
    private StringEventProducer producer;

    public LMAXString() {
        init();
    }

    public void init() {

        Executor executor = Executors.newCachedThreadPool();

        StringEventFactory factory = new StringEventFactory();

        StringEventHandler handler = new StringEventHandler();

        int ringBufferSize = 1024;

        disruptor = new Disruptor<StringEvent>(factory, ringBufferSize, executor);
        disruptor.handleEventsWith(handler);

        disruptor.start();
        producer = new StringEventProducer(disruptor);
    }


    public void publishMessage(String message) {
        producer.onData(message);
    }

    public void close() {
        if (disruptor != null) {
            disruptor.shutdown();
        }
    }

}
