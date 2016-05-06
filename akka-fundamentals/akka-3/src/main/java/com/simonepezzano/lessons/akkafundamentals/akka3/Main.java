package com.simonepezzano.lessons.akkafundamentals.akka3;

import com.simonepezzano.lessons.akkafundamentals.akka3.app1.Main1;
import com.simonepezzano.lessons.akkafundamentals.akka3.app2.Main2;

public class Main {

    /**
     * This is a major simplification. Ideally, the two "main" functions should be called in two
     * different processes to demonstrate the "remoting" capabilities.
     * To simplify the launch of the whole example, we grouped the two flows in one JVM, creating
     * two separate actor systems, binding different ports.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        /*
         * the main2 program, it will act as the host of a worker
         */
        Main2.main(args);


        /*
         * the main1 program, it will connect to main2 and throw data at it
         */
        Main1.main(args);


        Main2.actorSystem.terminate();
        Main1.actorSystem.terminate();
    }
}
