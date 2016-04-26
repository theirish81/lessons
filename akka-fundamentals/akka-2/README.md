# Second Akka example
In this example we focus on three:
* How the actor model simplifies **concurrency** against resources
* How to use the Akka **configuration file** and fine tune your system
* How to us **routers**

## What it does
The EntryActor will receive a FIFO queue as parameter and will register a scheduler to trigger
the actor itself to poll a message from the queue. Once the message pops out, it is sent to the
workers.

3 workers are instantiated by a 'worker' router that is declared in the application.conf file.
The router will receive messages on the workers' behalf and redistribute them to the workers in a
round robin flavor.
The workers will process the messages and send them to the 'finalizer' router, declared in the configuration
file as well.
The finalizer router instantiated 3 finalizers and works just like the 'worker' router.
The finalizers process the messages and send them to a single 'SinkActor' actor that will store them
in a map.

Another interesting fact is all workers, finalizers and routers share the same dispatcher (see configuration).
In this example we provided a very limited executor to demonstrate how you can control who's running threads,
how, and how many. Even though the example is not realistic, it gives you an idea on how you can fine tune
your resource usage.

## Lesson learned
* Even if your application is heavily multi threaded, the access to non thread-safe resources can be handled
with care by creating actors that can access those resources exclusively. Of course this example is extremely
simplified, we will cover real concurrency in a future project;
* Configuring tons of actors can be a pain, but Akka provides a handy configuration file you can use;
* There's a lot of super-fine grain tuning you can do using Akka, what you see here is just the mere basics;
When working with bare bone threads it can be hard to control how the system resources are going to be used
but Akka provides a simpler approach that is easy to visualize;
* Actors are awesome

## Notes
* The code is heavily commented, so it is a reference on its own;
* You can run the example by issuing a `mvn exec:exec` command