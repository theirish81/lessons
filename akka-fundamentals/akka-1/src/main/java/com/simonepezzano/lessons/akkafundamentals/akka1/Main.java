package com.simonepezzano.lessons.akkafundamentals.akka1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Main
 */
public class Main {

    public static final void main(String[] args) throws InterruptedException {
        // instantiating the actor system
        ActorSystem actorSystem = ActorSystem.create();

        // instantiating the coordinator actor
        ActorRef coordinatorActor = actorSystem.actorOf(Props.create(CoordinatorActor.class),"coordinator");

        // instancing the checker actor
        ActorRef checkerActor = actorSystem.actorOf(Props.create(CheckerActor.class),"checker");

        /*
         * By using the Akka scheduler, we want the checkerActor to receive a QueryCmd every 2 seconds
         */
        actorSystem.scheduler().schedule(Duration.Zero(),
                Duration.create(2, TimeUnit.SECONDS), checkerActor, new QueryCmd(),
                actorSystem.dispatcher(), null);

        // getting the party started
        coordinatorActor.tell(new RunCmd(10),null);

        /*
         * sending another message to the coordinator actor, which is obviously busy.
         * The message will stack up in the mailbox and be processed later
         */
        coordinatorActor.tell(new RunCmd(2),null);

        // we wait 15 seconds for the party to be over
        Thread.sleep(15000);

        // and eventually shutdown the actor system
        actorSystem.shutdown();
    }
}
