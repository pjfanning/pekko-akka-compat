package com.github.pjfanning.pekko.akka

import com.typesafe.config.ConfigFactory
import org.apache.pekko

import java.io.File

object Main extends App {
  val remoteConfig = ConfigFactory.load("remote-application")
  val akkaSystem = akka.actor.ActorSystem("akka-system")
  val akkaRemoteSystem = akka.actor.ActorSystem("akka-remote-system", remoteConfig)
  val pekkoSystem = pekko.actor.ActorSystem("pekko-system")
  val pekkoRemoteSystem = pekko.actor.ActorSystem("pekko-remote-system", remoteConfig)
  try {
    val akkaRemoteActor = akkaRemoteSystem.actorOf(akka.actor.Props[AkkaActor], "akkaRemoteActor")
    val akkaLocalActor = akkaSystem.actorOf(akka.actor.Props[AkkaLocalActor], "akkaLocalActor")

    val pekkoRemoteActor = pekkoRemoteSystem.actorOf(pekko.actor.Props[PekkoActor], "pekkoRemoteActor")
    val pekkoLocalActor = pekkoSystem.actorOf(pekko.actor.Props[PekkoLocalActor], "pekkoLocalActor")

    Thread.sleep(10000)
  } finally {
    akkaSystem.terminate()
    akkaRemoteSystem.terminate()
    pekkoSystem.terminate()
    pekkoRemoteSystem.terminate()
  }
}