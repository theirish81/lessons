package com.simonepezzano.lessons.springfundamentals.spring1.notifiers;

/**
 * The INotifier interface. These are all the methods a notifer needs to implement to be a notifier.
 */
public interface INotifier {

    /**
     * Notifies a message
     * @param message the message
     */
    void notifyMessage(String message);

    /**
     * Notifies an checkpoint
     */
    void notifyCheckpoint();

    /**
     * Notifies an error
     * @param e the exception
     */
    void notifyError(Exception e);
}
