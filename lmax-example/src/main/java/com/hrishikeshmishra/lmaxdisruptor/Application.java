package com.hrishikeshmishra.lmaxdisruptor;

/**
 * @author hrishikesh.mishra
 */
public class Application {

    public static void main(String[] args) {
        LMAXString lmax = new LMAXString();
        System.out.println("Initializing lmax disruptor");

        for (int i = 0; i < 10; i++) {
            lmax.publishMessage("Message Id : " + i);
        }

        System.out.println("All Messages published. ");
        lmax.close();

        System.out.println("Program closed successfully ");
    }
}
