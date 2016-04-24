package com.simonepezzano.lessons.akkafundamentals.akka1;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Our coordinator actor. When a RunCmd is received, the actor will be busy in delivering
 * requests to the two workers.
 * The messages to the workers will alternate based on a simple logic and each message will
 * be sent with a 1 second delay.
 */
public class CoordinatorActor extends UntypedActor {

    // reference to the workers
    private ActorRef worker1, worker2;


    public CoordinatorActor(){
        /**
         * Actors are actually instantiated here, and this makes the CoordinatorActor "parent" of the workers
         * and is therefore responsible for their "health".
         */
        worker1 = context().actorOf(Props.create(WorkerActor.class),"worker1");
        worker2 = context().actorOf(Props.create(WorkerActor.class),"worker2");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        // if it's a run command...
        if(message instanceof RunCmd){
            // the party gets started
            int max = ((RunCmd) message).getMax();

            /*
             * this might be long running process. As the actor is working on this, other messages it may receive
             * will be processed once this is done. If you wish to be able to process more RunCmd instances
             * you will need to instantiate more CoordinatorActor.
             * One actor instance, one thing at a time, that's the rule
             */
            for (int i=0; i<max; i++){

                // stupid selection logic
                ActorRef ref = i%2==0 ? worker1 : worker2;

                /*
                 * We 'forward' the message. This means the sender information will be kept as they are
                 */
                ref.forward(new ProcessCmd(i),context());
                Thread.sleep(600);
            }
        }
    }
}
