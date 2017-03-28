package com.simonepezzano.lessons.springfundamentals.spring2;

import com.simonepezzano.lessons.timers.interfaces.AbstractTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The main application class, in a Spring bean flavor
 */
@Component("application")
public class Application {

    /**
     * Our timer, whatever that is. All we know it implements AbstractTimer
     */
    @Autowired
    AbstractTimer timer;

    /**
     * Runs the application
     * @throws InterruptedException thrown by our "sleep" command
     */
    public void run() throws InterruptedException {
        timer.start();

        // Will run for 5 secs and then cancel
        Thread.sleep(5005);
        timer.cancel();
    }
}
