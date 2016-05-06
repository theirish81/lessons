# What is Akka and why should I care
## In short
Akka is a fantastic implementation of the [Actor Model](https://en.wikipedia.org/wiki/Actor_model), a programming
model that has been designed to simplify how you deal with concurrency.

It's been developed by [Lightbend](https://www.lightbend.com) (formely Typesafe) using the [Scala](http://www.scala-lang.org/) programming language.
As Scala runs on the Java JVM, a lot of "brige" code has been created so that it could be easily used in Java as well.

Most of the examples are written in Java because of the requirements of these lessons, but I suggest you to have a look
at how it works in Scala, because it really is fascinating.


## Akka-1
The first example does not really get deep into the concurrency problem, but demonstrates some of the key aspects of the actor model
and the Akka implementation. Even so, you can immediately tell some of the benefits that made it so popular and get a hint on how
it could change the way to organize your pojects.

## Akka-2
Introduction of routers and a glimpse of the Akka configuration file.
We also configure a custom dispatcher that limits the number of spawned threads.
The example is a simple simulation of a data ingestion chain that assumes both entry and sink are not thread-safe.

## Akka-3
Very basic introduction to Akka-Remote that provides the capability to have distributed actors talking to each other
over a network. This example is just scratching the surface and will be extended in the next example.