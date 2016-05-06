# Third Akka example
This example is all around the basics of Akka remote capabilities, the ability of Akka actors to be located
in different applications and talk to each other.

We also have a quick look at the "ask" pattern that gives Akka sort of synchronous "RPC" capabilities, but should
be used only if strictly necessary.

## What it does
The Main will launch two different main procedures, simulating two different processes in one. Two ActorSystems will be
instantiated as well, each one with its own configuration file, binding two ports.

app-2 is going to start first because it will act as a worker and we want it to be ready when the show begins.

app-1 will then start, and start shooting messages to its own Entry actor that will pitch them to the worker actor.
Once the worker has processed a message, it will send it back to app-1 to the log actor. Every time a message is
processed, the worker will also increment a counter that acts as state variable.

After some delay, app-1 will use the "ask" pattern to literally ask the worker for its state in a synchronous flavor.
Let me remind you again that you should avoid using this approach as much as you can. You're into the actor model for
better asynchronous control, not fake synchronous crap.

## Lesson learned
* With Akka you can seamlessly distribute your system in multiple applications talking to each other in a quite
transparent way;
* For this reason, you should avoid designing your application assuming it's going to be one monolith. Embrace the actor
model design, create actors that "know what they're doing" to set yourself free from assumptions you' will regret;
* Actors are awesome

## Notes
* The code is heavily commented, so it is a reference on its own;
* You can run the example by issuing a `mvn exec:exec` command