package com.simonepezzano.lessons.akkafundamentals.akka1;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

/**
 * Tells the the two workers to send back their state.
 * When the state is sent back, it gets printed. All happens in an asynchronous flavor.
 * Since workers might be busy doing stuff, we don't simply sit there and wait for a response because
 * -in a real world scenario- that could take some time. Even though it's possible to do it, we rather
 * send out the request and expect a response in an indefinite future.
 *
 * In this example we're also referencing the two workers directly as we're aware of their hierarchy.
 * While this is perfectly fine for this examples, other times you might want to ask the coordinator
 * to intercede. In this case though, the coordinator is very busy while the workers are not, so it's
 * perfectly legit to go and query them directly.
 */
public class CheckerActor extends UntypedActor {

    /*
     * The selector that should reference the workers
     */
    ActorSelection workers;

    public CheckerActor(){
        super();
        /*
         * we select the workers by path. The path sees as first segment the 'coordinator' reference
         * because the workers have been created by the CoordinatorActor. This not only implies the coordinator
         * is responsible for it but also defines a hierarchical structure.
         * At the end of the path you can see a wildcard, which basically means "every child of coordinator".
         * If we wanted to reference "worker1" could simply write "../coordinator/worker1"
         */
        workers = context().actorSelection("../coordinator/*");
    }
    @Override
    public void onReceive(Object message) throws Exception {
        /*
         * QueryCmd, we send the message to the actors represented by the selector.
         * We are not 'forwarding' it as we did in the CoordinatorActor because we want the worker
         * to know who should send the 'response' to.
         */
        if ( message instanceof QueryCmd )
            workers.tell(message,self());
        /*
         * If it's an integer, then it's the worker reply and we can print it
         */
        if ( message instanceof Integer )
            System.out.println("last processed value by "+sender().path().name()+" ix "+message);
    }
}
