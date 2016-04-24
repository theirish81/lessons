package com.simonepezzano.lessons.akkafundamentals.akka1;


/**
 * This command represents the request to process a value
 */
public class ProcessCmd {

    // Messages shouldn't be altered by actors, this is why value is final
    private final int value;

    public ProcessCmd(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
