package com.simonepezzano.lessons.akkafundamentals.akka2;

import akka.actor.ActorSelection;
import akka.actor.Cancellable;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Entry point actor. This is basically simulating an entry point that consumes an inbound stream of data.
 * The constructor receives the reference to a buffer that it will later poll
 */
public class EntryActor extends UntypedActor {

    // selector to the workers router
    final ActorSelection workerSelector;

    // reference to the buffer
    final ArrayBlockingQueue<Integer> buffer;

    // a reference to the scheduler, so we can cancel it if requested
    Cancellable scheduled;

    public EntryActor(ArrayBlockingQueue<Integer> buffer){
        super();
        workerSelector = context().actorSelection("/user/worker");
        this.buffer = buffer;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        /*
         * when "START" is received, a scheduler is set to send a message to this actor to trigger
         * a buffer poll
         */
        if(o.equals("START"))
            scheduled = context().system().scheduler().schedule(Duration.Zero(),Duration.create(200,TimeUnit.MILLISECONDS),self(),"POLL",context().dispatcher(),null);

        /*
         * when "POLL" is received, the actor will poll a message from the buffer, if present
         */
        if(o.equals("POLL")) {
            /*
             * we slow down things a bit so that the buffer will grow and POLL commands stack up a bit, but only one at a time
             * is processed.
             */
            Util.randomDelay(120,100);
            final Integer value = buffer.poll();
            // if the poll succeeded. We dispatch the message
            if( value !=null ) {
                System.out.println(self().path() + " processing input " + value+". Buffer size is: "+buffer.size());
                workerSelector.tell(value, self());

            } else
                System.out.println(self().path()+" Buffer empty");
        }
        /*
         * if "STOP" is received, the scheduler gets cancelled
         */
        if(o.equals("STOP")){
            scheduled.cancel();
        }
    }
}
