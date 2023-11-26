package com.github.pjfanning.pekko.akka

import com.typesafe.config.ConfigFactory
import org.apache.pekko

import java.io.File

object Main extends App {
  val remoteConfigFile = getClass.getClassLoader.getResource("remote-application.conf").getFile
  val remoteConfig = ConfigFactory.parseFile(new File(remoteConfigFile))
  val akkaSystem = akka.actor.ActorSystem("akka-system")
  val akkaRemoteSystem = akka.actor.ActorSystem("akka-remote-system", remoteConfig)
  val pekkoSystem = pekko.actor.ActorSystem("pekko-system")
  val pekkoRemoteSystem = pekko.actor.ActorSystem("pekko-remote-system", remoteConfig)
  try {
    val akkaRemoteActor = akkaRemoteSystem.actorOf(akka.actor.Props[AkkaActor], "akkaRemoteActor")
    val akkaLocalActor = akkaSystem.actorOf(akka.actor.Props[AkkaLocalActor], "akkaLocalActor")

    /*
    val pekkoRemoteActor = pekkoRemoteSystem.actorOf(pekko.actor.Props[PekkoActor], "pekkoRemoteActor")
    val pekkoActor = pekkoSystem.actorOf(pekko.actor.Props[PekkoActor], "pekkoActor")
    pekkoActor ! "Pekko call"
     */

    Thread.sleep(10000)
  } finally {
    akkaSystem.terminate()
    akkaRemoteSystem.terminate()
    pekkoSystem.terminate()
    pekkoRemoteSystem.terminate()
  }
}