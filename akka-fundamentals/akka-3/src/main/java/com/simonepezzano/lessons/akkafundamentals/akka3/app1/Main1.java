package com.simonepezzano.lessons.akkafundamentals.akka3.app1;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

/**
 * This application is going to generate data and handle it to its entry actor that is then
 * going to send it to the remote worker.
 * It is also going to create log actor that will accept data to be logged (by the remote worker)
 */
public class Main1 {

    public static ActorSystem actorSystem;

    public static void main(String[] args) throws Exception {

        // loading a configuration specific to this program
        final Config config = ConfigFactory.load("app1.conf");

        // creating a named actor system based on the configuration
        actorSystem = ActorSystem.create("app1",config);

        // our entry actor
        final ActorRef entry = actorSystem.actorOf(Props.create(EntryActor.class),"entry");

        // our log actor
        actorSystem.actorOf(Props.create(LogActor.class),"log");

        // let's throw data at the entry
        for(int i=0;i<50;i++){
            entry.tell(i,null);
            Thread.sleep(100);
        }
        // we wait some time for it to complete
        Thread.sleep(2000);

        /*
         * We now introduce a pattern that allows you to "ask" an actor for a result and wait for it.
         * Remember this is a stretch of the original actor model and should be avoided wherever possible.
         */

        // let's select the remote worker now
        final ActorSelection worker = actorSystem.actorSelection("akka.tcp://app2@localhost:2553/user/worker");

        // We're going to use this duration descriptor for timeout purposes in the next lines
        final FiniteDuration duration = Duration.create(10, TimeUnit.SECONDS);

        /*
         * We "ask" the remote worker a "QUERY" command and we await for the result.
         * Note how we used the "duration" in two places. The first one in the "ask" function
         * tells the system to give up if the command doesn't hit the recipient mailbox within
         * the given duration.
         * The second use of it in the "result" function means that we're going to wait for a
         * reply for the given duration
         */
        final Future future = Patterns.ask(worker,"QUERY", Timeout.durationToTimeout(duration));
        final Object response = Await.result(future,duration);
        System.out.println("Remote worker says it processed "+response+" items");


    }
}
