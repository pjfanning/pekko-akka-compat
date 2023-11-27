package com.github.pjfanning.pekko.akka

import akka.actor.Actor
import akka.event.Logging

class AkkaLocalActor extends Actor {
  val log = Logging(context.system, this)
  override def preStart() = {
    val remoteActor = context.actorSelection("akka.tcp://akka-remote-system@127.0.0.1:2553/user/akkaRemoteActor")
    log.info("remote-actor: " + remoteActor)
    remoteActor ! "hi"

    val remotePekkoActor = context.actorSelection("pekko.tcp://pekko-remote-system@127.0.0.1:7356/user/pekkoRemoteActor")
    log.info("remote-pekko-actor: " + remotePekkoActor)
    remotePekkoActor ! "akka says hi"
  }
  def receive = {
    case x =>
      log.info("Response: {}", x)
  }
}