package com.simonepezzano.lessons.akkafundamentals.akka1;

import akka.actor.UntypedActor;

import java.util.Random;

/**
 * A very simple actor printing some basic contextual data and the message
 */
public class WorkerActor extends UntypedActor{

    /*
     * Actors are stateful, therefore they can have properties without causing any kind
     * of race condition.
     */

    // random number generator
    private Random random = new Random();

    /**
     * The state of the worker, storing the latest value that has been processed by the actor instance
     */
    private Object state = 0;

    @Override
    public void onReceive(Object o) throws Exception {
        // process command
        if (o instanceof  ProcessCmd) {

            final int value = ((ProcessCmd) o).getValue();

            /*
             * we add some random delay to the worker to cause some 'shuffling' on the
             * results order
             */
            Thread.sleep(random.nextInt(1500)+500);

            // printing the actor path, the thread hash and the message
            System.out.println(self().path().toString() + " <" + Thread.currentThread().hashCode() + "> " + value);

            // we store the latest processed message for no reason
            this.state = ((ProcessCmd) o).getValue();

        }

        // replies with the last processed value
        if (o instanceof  QueryCmd)
            sender().tell(state,self());

    }
}
