package com.simonepezzano.lessons.timers.interfaces;

import java.util.TimerTask;

/**
 * The AbstractTask contract, extending the TimerTask java.util abstract class.
 * It represents something to be run by a timer.
 */
public abstract class AbstractTask extends TimerTask {

    /**
     * This will help us to make clear the idea behind having a contract
     * @return the ID of this task
     */
    public abstract String getId();
}
