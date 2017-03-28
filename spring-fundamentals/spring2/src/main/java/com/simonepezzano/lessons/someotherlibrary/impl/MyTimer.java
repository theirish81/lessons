package com.simonepezzano.lessons.someotherlibrary.impl;

import com.simonepezzano.lessons.timers.interfaces.AbstractTask;
import com.simonepezzano.lessons.timers.interfaces.AbstractTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The actual AbstractTimer implementation
 */
@Component("timer")
public class MyTimer extends AbstractTimer {

    /**
     * The task that is going to run
     */
    @Autowired
    AbstractTask task;

    /**
     * Starts the timer. The task will run every second
     */
    @Override
    public void start() {
        System.out.println("Scheduling: "+task.getId());
        scheduleAtFixedRate(task,1000,1000);
    }
}