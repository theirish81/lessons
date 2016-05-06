package com.simonepezzano.lessons.akkafundamentals.akka3.app2;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

/**
 * The worker actor
 */
public class WorkerActor extends UntypedActor{

    // This is the selector for the remote log actor
    private final ActorSelection logSelection;

    // Actor state
    private int count = 0;

    public WorkerActor(){
        // we explicitly reference the remote log actor
        logSelection = context().actorSelection("akka.tcp://app1@localhost:2552/user/log");
    }
    @Override
    public void onReceive(Object message) throws Exception {
        /*
         * If it's an integer, then it's a message to be processed. These messages come from the
         * remote entry actor
         */
        if(message instanceof Integer){
            count++;
            System.out.println(self().path() + " consuming " + message);

            // when the processing is done, a message is sent to the remote log actor
            logSelection.tell(message, self());
        }
        /*
         * If the message is "QUERY", then we send back our current state
         */
        if(message.equals("QUERY"))
            sender().tell(count,self());
    }
}
