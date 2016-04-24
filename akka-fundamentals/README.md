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