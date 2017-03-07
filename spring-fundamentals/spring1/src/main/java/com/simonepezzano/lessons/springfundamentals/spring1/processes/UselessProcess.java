package com.simonepezzano.lessons.springfundamentals.spring1.processes;

import com.simonepezzano.lessons.springfundamentals.spring1.notifiers.INotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This process is indeed useless because it shoots a number of notifications here and there.
 * What is interesting here is it's annotated as a "Component". By doing so we can build one of
 * these by knowing its ID (process) using the Spring Context.
 * We shouldn't be using its default constructor directly because, as you can see, it has
 * an "autowired" dependency which requires it to be created using the right factory.
 */
@Component  ("process")
public class UselessProcess implements Runnable {

    /**
     * The iNotifier dependency. You can find out which implementation is going to be used by
     * looking a the beans.xml file.
     * by convention, the name of the instance needs to match the ID defined in the beans configuration file.
     */
    @Autowired
    INotifier iNotifier;

    public void run() {
        /*
         * For 5 times...
         */
        for(int i=0;i<5;i++){
            // Send a notification message
            iNotifier.notifyMessage("Running message "+i);
            // Every time i is even, send a checkpoint
            if((i%2)==0)
                iNotifier.notifyCheckpoint();
            // Wait a bit
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                iNotifier.notifyError(e);
            }
        }
    }
}
