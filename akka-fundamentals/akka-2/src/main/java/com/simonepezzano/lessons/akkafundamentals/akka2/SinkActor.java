package com.simonepezzano.lessons.akkafundamentals.akka2;

import akka.actor.UntypedActor;

import java.util.LinkedHashMap;

/**
 * The sink actor implementation. This actor is meant to be instantiated only once as it simulates
 * a non thread-safe store
 */
public class SinkActor extends UntypedActor {

    /*
     * data stash. Note how there's no risk of concurrency because this actor will process
     * one message at a time and it's meant to be just one.
     * This simulates the access to a thread unsafe resource
     */
    private LinkedHashMap<Integer,String> data = new LinkedHashMap<Integer,String>();

    /*
     * same as before. No reason to protect this variable
     */
    private int seq = 0;

    @Override
    public void onReceive(Object o) throws Exception {
        // increase sequence
        seq++;
        // store data
        data.put(seq, (String) o);

        System.out.println(self().path()+" says "+data.size()+" objects have been stored");
    }
}
