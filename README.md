# pekko-akka-compat

Some test scenarios to try out Pekko and Akka remote actors together. https://github.com/apache/incubator-pekko/issues/146 is open to look at facilitating this.

## PekkoAkkaNoInterop
* Starts Akka and Pekko remote actors and it shows that Akka and Pekko can communicate between actors of the same type. We know they will not interoperate yet.
* So far, the key thing is that you can already have Akka and Pekko code running in the same JVM.

## PekkoToAkkaInterop
* relies on a patched pekko-remote jar in `lib` dir - that includes the changes in https://github.com/apache/incubator-pekko/issues/108
* In this test, the PekkoLocalActor2 sends a message to the AkkaRemoteActor
* Pekko has been configured using `application2.conf` to use `akka` URIs so Akka accepts the message. Pekko also needs to accept `akka` messages so that it can handle the response.
* The Akka team are very unlikely to make an equivalent change that lets Akka use or accept `pekko` URIs

## PekkoToAkkaAeronInterop
* basically, the same as PekkoToAkkaInterop but uses Artery comms instead of Classic (Netty) comms

## PekkoAkkaAeronCluster
* tries to start an Akka cluster with seed nodes and then to get a Pekko node to join it
* uses the settings to get pekko to use and accept `akka` URIs
* fails though - it seems like the Akka nodes try to verify the Pekko node and the data that the Pekko node provides includes HOCON data that has `pekko` prefixed configs while Akka expects the configs to have an `akka` prefix - see `akka.cluster.JoinConfigCompatCheckCluster`
