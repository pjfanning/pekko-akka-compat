# pekko-akka-compat

Some test scenarios to try out Pekko and Akka remote actors together. https://github.com/apache/incubator-pekko/issues/146 is open to look at facilitating this.

The Main.scala class in this repo
* Starts Akka and Pekko remote actors and it shows that Akka and Pekko can communicate between actors of the same type. We know they will not interoperate yet.
* So far, the key thing is that you can already have Akka and Pekko code running in the same JVM.
