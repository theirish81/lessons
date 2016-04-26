package com.simonepezzano.lessons.akkafundamentals.akka2;

import java.util.Random;

/**
 *
 */
public class Util {

    /**
     * Utility function to slow threads down
     * @param start minimum delay
     * @param max upper bound of the random delay part
     */
    public static void randomDelay(int start,int max){
        try {
            Thread.sleep(start + new Random().nextInt(max));
        }catch(Exception e){}
    }
}
