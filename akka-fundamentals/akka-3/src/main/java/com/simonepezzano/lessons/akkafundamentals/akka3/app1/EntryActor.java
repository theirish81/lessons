package com.simonepezzano.lessons.akkafundamentals.akka3.app1;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

/**
 * This actor accepts messages, prints them out and forwards them to a remote actor.
 */
public class EntryActor extends UntypedActor {

    // The selector for the remote worker actor
    private final ActorSelection remoteActor;

    public EntryActor(){
         /*
          * We explicitly reference the address of the remote actor
          */
        remoteActor = context().actorSelection("akka.tcp://app2@localhost:2553/user/worker");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println(self().path()+" accepting "+message);
        remoteActor.tell(message,self());
    }
}
