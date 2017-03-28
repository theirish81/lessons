package com.simonepezzano.lessons.timers.interfaces;

import java.util.Timer;

/**
 * AbstractTimer contract.
 */
public abstract class AbstractTimer extends Timer {

    /**
     * Will start the timer
     */
    public abstract void start();
}
