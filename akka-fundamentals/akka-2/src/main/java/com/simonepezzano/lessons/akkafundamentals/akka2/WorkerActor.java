package com.simonepezzano.lessons.akkafundamentals.akka2;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

/**
 * The main worker actor. Messages are sent here for the first stage of processing
 */
public class WorkerActor extends UntypedActor {

    // a reference to the finalizer router
    ActorSelection finalizerSelection;

    public WorkerActor(){
        finalizerSelection = context().actorSelection("/user/finalizer");
    }
    @Override
    public void onReceive(Object o) throws Exception {
        // some delay to make the processing more "real"
        Util.randomDelay(100, 100);

        // We print the actor path, the thread hashcode and the message
        System.out.println(self().path()+" <"+Thread.currentThread().hashCode()+"> working on "+o);

        // sending the altered message to the second processing stage, the finalizer router
        finalizerSelection.tell("W "+o,self());
    }
}
