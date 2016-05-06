package com.simonepezzano.lessons.akkafundamentals.akka3.app1;

import akka.actor.UntypedActor;

/**
 * The log actor. It prints the message.
 */
public class LogActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println(sender().path()+" says "+message+" has been processed");
    }
}
