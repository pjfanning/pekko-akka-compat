package com.github.pjfanning.pekko.akka

import com.typesafe.config.ConfigFactory
import org.apache.pekko

object PekkoToPekkoInterop extends App {
  val remoteConfig = ConfigFactory.parseString("pekko.remote.classic.manager-name-prefix = \"pekkoprotocolmanager\"")
    .withFallback(ConfigFactory.load("remote-application"))
  val remote2Config = ConfigFactory.parseString("pekko.remote.classic.manager-name-prefix = \"akkaprotocolmanager\"")
    .withFallback(ConfigFactory.load("remote2-application"))
  val pekkoSystem = pekko.actor.ActorSystem("pekko-remote-system", remoteConfig)
  val pekko2System = pekko.actor.ActorSystem("pekko2-remote-system", remote2Config)
  try {
    val pekkoRemoteActor = pekkoSystem.actorOf(pekko.actor.Props[PekkoActor], "pekkoRemoteActor")
    val pekko2RemoteActor = pekko2System.actorOf(pekko.actor.Props[PekkoLocalActor], "pekkoLocalActor")
    Thread.sleep(10000)
  } finally {
    pekkoSystem.terminate()
    pekko2System.terminate()
  }
}