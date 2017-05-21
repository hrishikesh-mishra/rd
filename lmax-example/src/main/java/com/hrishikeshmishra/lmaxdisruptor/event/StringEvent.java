package com.hrishikeshmishra.lmaxdisruptor.event;

/**
 * @author hrishikesh.mishra
 */
public class StringEvent {

    private String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StringEvent{" +
                "message='" + message + '\'' +
                '}';
    }
}
