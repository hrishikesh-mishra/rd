package com.hrishikeshmishra.lmaxdisruptor.evenproducer;

import com.hrishikeshmishra.lmaxdisruptor.event.StringEvent;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * @author hrishikesh.mishra
 */
public class StringEventProducer {

    private final Disruptor<StringEvent> disruptor;

    public StringEventProducer(Disruptor<StringEvent> disruptor) {
        this.disruptor = disruptor;
    }

    private static final EventTranslatorOneArg<StringEvent, String> TRANSLATOR_ONE_ARG =
            new EventTranslatorOneArg<StringEvent, String>() {
                public void translateTo(StringEvent writeEvent, long sequence, String message) {
                    writeEvent.setMessage(message);
                }
            };

    public void onData(String message) {
        disruptor.publishEvent(TRANSLATOR_ONE_ARG, message);
    }


}


