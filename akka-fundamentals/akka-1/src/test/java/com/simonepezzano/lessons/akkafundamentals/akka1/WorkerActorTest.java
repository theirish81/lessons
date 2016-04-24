package com.simonepezzano.lessons.akkafundamentals.akka1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class WorkerActorTest{

    private ActorSystem actorSystem;
    private ActorRef worker;

    @Before
    public void setUp(){
        actorSystem = ActorSystem.create();
        worker  = actorSystem.actorOf(Props.create(WorkerActor.class));
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        actorSystem.shutdown();
    }
    @Test
    public void testStateUpdate() throws Exception {
        worker.tell(new ProcessCmd(5),null);
        Future f = Patterns.ask(worker,new QueryCmd(),100);
        Object o = Await.result(f, Duration.create(100, TimeUnit.MICROSECONDS));
        assert 5 == (Integer)o;
    }
}
