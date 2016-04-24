package com.simonepezzano.lessons.akkafundamentals.akka1;

/**
 * Represents the command to start distributing the messages
 */
public class RunCmd {

    private final int max;

    public RunCmd(int max){
        this.max = max;
    }

    public int getMax() {
        return max;
    }
}
