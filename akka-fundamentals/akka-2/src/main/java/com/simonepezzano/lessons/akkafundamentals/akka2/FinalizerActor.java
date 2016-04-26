package com.simonepezzano.lessons.akkafundamentals.akka2;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

/**
 * The second processing stage of this chain
 */
public class FinalizerActor extends UntypedActor {


    // a reference to the sink actor
    ActorSelection sinkSelection;

    public FinalizerActor(){
        sinkSelection = context().actorSelection("/user/sink");
    }
    @Override
    public void onReceive(Object o) throws Exception {
        // add some delay to make the processing more "real"
        Util.randomDelay(100, 100);

        // we print the actor path, the thread hashcode and the message
        System.out.println(self().path() + " <"+Thread.currentThread().hashCode()+"> finalizing "+o);

        // sending the altered message to the sink actor
        sinkSelection.tell("F "+o.toString(),self());
    }
}
