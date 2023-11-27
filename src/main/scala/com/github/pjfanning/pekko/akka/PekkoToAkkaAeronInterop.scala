package com.github.pjfanning.pekko.akka

import com.typesafe.config.ConfigFactory
import org.apache.pekko

object PekkoToAkkaAeronInterop extends App {
  val localConfig = ConfigFactory.load("artery-application")
  val remoteConfig = ConfigFactory.load("remote-artery-application")
  val akkaRemoteSystem = akka.actor.ActorSystem("akka-remote-system", remoteConfig)
  val pekkoSystem = pekko.actor.ActorSystem("pekko-system", localConfig)
  try {
    val akkaRemoteActor = akkaRemoteSystem.actorOf(akka.actor.Props[AkkaActor], "akkaRemoteActor")
    val pekkoLocalActor = pekkoSystem.actorOf(pekko.actor.Props[PekkoLocalActor3], "pekkoLocalActor3")
    Thread.sleep(10000)
  } finally {
    akkaRemoteSystem.terminate()
    pekkoSystem.terminate()
  }
}