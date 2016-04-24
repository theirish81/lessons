# First Akka example
In this example we see some fundamentals concepts of the Actor model using the Akka implementation.

## What it does
The CoordinatorActor is going to receive 2 RunCmd messages, that is a bean containing a "max" property.

For every RunCmd, it will then perform a loop (0 to max) where it will send ProcessCmd messages
(containing the iterator index) to two workers which will print the message plus some other
details and store the received number.

In the meantime, a CheckerActor is scheduled to run every 2 seconds and it will be in charge to reuqest (QueryCmd) the
workers what their state value is.

## Lesson learned
* An actor does one thing at a time. The second RunCmd message won't be executed until the first is complete;
* Actors instances are stateful, and because of the previous point, there's no risk of a race condition over the resources;
* Actors have a mailbox that will store incoming messages as they are busy doing their job;
* Actors and message delivery are asynchronous by design. Akka provides shortcuts to simplify a request / response pattern;
but it has to be used very carefully;
* Actors are awesome