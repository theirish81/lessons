# First Spring Example
In this example we explore the concept of dependency injection using Spring.
We also use two different ways to define beans and use them.

## What it does
The Main function will obtain a UselessProcess instance by asking for a bean
with "process" as ID. The UselessProcess class declares itself as a component
implementing the "process" ID so it's returned.

Among its properties, UselessProcess needs an object implementing the INotifier contract
and has Spring inject it using the @autowired annotation.

There are multiple implementations of the INotifier interface, so the beans.xml file
will define which implementation (ConsoleNotifier, HttpNotifier) will be chosen.

ConsoleNotifier will just print the notifications on the console, while HttpNotifier
will shoot them to httpbin.org website and print its response on the console.

The UselessProcess code will send some notifications and then exit.

## Lesson Learned
The fundamentals of dependency injection.
+ A class should not have direct knowledge of an object it depends on, how it's implemented
or where it comes from. Just the interface it is exposing.
+ Delegating the creation and injection of the dependencies to an external system provides you
the ability to decide which implementations have to go, and how they are created.
+ Using Spring beans.xml file you can have a centralized control of the beans, while the annotations
provide you a quick way to define beans.