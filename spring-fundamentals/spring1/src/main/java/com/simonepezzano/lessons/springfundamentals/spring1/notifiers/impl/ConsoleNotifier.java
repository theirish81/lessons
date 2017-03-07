package com.simonepezzano.lessons.springfundamentals.spring1.notifiers.impl;


import com.simonepezzano.lessons.springfundamentals.spring1.notifiers.INotifier;
import java.util.Date;

/**
 * This notifier is very simple and prints out the received messages
 */
public class ConsoleNotifier implements INotifier {

    public void notifyMessage(String message) {
        System.out.println(message);
    }

    public void notifyCheckpoint() {
        System.out.println("Checkpoint "+new Date());
    }

    public void notifyError(Exception e) {
        e.printStackTrace();
    }
}
