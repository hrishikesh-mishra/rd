package com.hrishikeshmishra.lmaxdisruptor.factories;

import com.hrishikeshmishra.lmaxdisruptor.event.StringEvent;
import com.lmax.disruptor.EventFactory;

/**
 * @author hrishikesh.mishra
 */
public class StringEventFactory implements EventFactory<StringEvent> {

    @Override
    public StringEvent newInstance() {
        return new StringEvent();
    }
}
