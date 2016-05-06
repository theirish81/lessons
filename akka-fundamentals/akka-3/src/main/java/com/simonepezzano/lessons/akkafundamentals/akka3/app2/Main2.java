package com.simonepezzano.lessons.akkafundamentals.akka3.app2;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * This application is going to create a worker actor to be addressed by remote actors
 */
public class Main2 {

    public static ActorSystem actorSystem;

    public static void main(String[] args){
        // loading a configuration specific to this program
        Config config = ConfigFactory.load("app2.conf");

        // creating a named actor system based on the configuration
        actorSystem = ActorSystem.create("app2",config);

        // creating the worker actor
        actorSystem.actorOf(Props.create(WorkerActor.class),"worker");
    }
}
