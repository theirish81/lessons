package com.simonepezzano.lessons.springfundamentals.spring2.beans;

import com.simonepezzano.lessons.timers.interfaces.AbstractTask;
import org.springframework.stereotype.Component;

/**
 * Implementation of the task
 */
@Component("task")
public class MyTask extends AbstractTask {
    @Override
    public String getId() {
        return "my-task";
    }

    @Override
    public void run() {
        System.out.println("Running my task");
    }
}
