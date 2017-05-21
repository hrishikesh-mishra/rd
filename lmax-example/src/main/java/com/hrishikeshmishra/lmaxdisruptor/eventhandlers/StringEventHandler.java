package com.hrishikeshmishra.lmaxdisruptor.eventhandlers;

import com.hrishikeshmishra.lmaxdisruptor.event.StringEvent;
import com.lmax.disruptor.EventHandler;

import java.util.Date;

/**
 * @author hrishikesh.mishra
 */
public class StringEventHandler implements EventHandler<StringEvent> {

    @Override
    public void onEvent(StringEvent stringEvent, long l, boolean b) throws Exception {
        printToConsole(stringEvent.getMessage());
    }

    private void printToConsole(String message) {
        System.out.printf("%s : %s\n", new Date(), message);
    }

}
