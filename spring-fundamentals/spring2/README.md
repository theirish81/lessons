# Second Spring Example
In this example we see how to efficiently use Spring annotations by creating an interdependence between your
 application and a 3rd party library. I ask you to use a bit of imagination here.
 Consider *someotherlibrary*, *timers* and *springfundamentals.spring2* as 3 different
pieces of software, built separately and packaged together via Maven.


## What it does
*timers* exposes two interfaces, AbstractTask and AbstractTimer which are meant to be shared by the application
using the timers and the library implementing them.

*somespringlibrary*  implements AbstractTimer by exposing its MyTimer implementation using the "timer" ID.
MyTimer autowires an AbstractTask using the "task" ID.
When the *start()* method is invoked, some actions involving the task is performed (see code).

*spring2* is the application. It implements a main method which obtains a "timer" instance (in this case
provided by lessons), and delivers the "task" Spring bean by implementing the AbstractTask contract.

## Lesson Learned
* While it's perfectly OK to decide which components are going to be used by using the XML configuration file,
annotations in combination with Maven opens a whole new, wider scenario for what concerns modularity;
* You shouldn't be thinking of your application as a consumer of beans. Most of the time the relationship is mutual,
and what really drives who provides what is a matter of who holds control of processes;