package com.simonepezzano.lessons.akkafundamentals.akka2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 */
public class Main {

    private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<Integer>(100);

    public static void main(String[] args) throws InterruptedException {

        ActorSystem actorSystem = ActorSystem.create();

        /*
         * created by configuration that can be found in src/main/resources/application.conf
         * The configuration describes the actor identified by "worker" to be:
         * - a router
         * - containing a definite number of child instances
         * - delivering messages using a round robin strategy
         * - with a specific dispatcher (defined in the configuration)
         * The duty of a router is to dispatch messages to its children.
         * In the following statement, we basically ask the actor system to create the router
         * as described by the configuration and to instantiate its children as "WorkerActor"
         */
        actorSystem.actorOf(new FromConfig().props(Props.create(WorkerActor.class)),"worker");

        /*
         * Same as for the "worker" router
         */
        actorSystem.actorOf(new FromConfig().props(Props.create(FinalizerActor.class)),"finalizer");

        // sink actor instantiated
        actorSystem.actorOf(Props.create(SinkActor.class), "sink");

        // instantiating the entry point
        ActorRef entry = actorSystem.actorOf(Props.create(EntryActor.class,buffer),"entry");

        // by sending the "START" message, the actor starts polling the buffer
        entry.tell("START",null);

        // and here we fill the buffer
        for(int i=0;i<100;i++){
            buffer.put(i);
            Thread.sleep(100);
        }

        // wait a little
        Thread.sleep(13000);
        buffer.put(1000);
        Thread.sleep(500);
        entry.tell("STOP",null);
        Thread.sleep(1000);
        actorSystem.terminate();
    }
}
