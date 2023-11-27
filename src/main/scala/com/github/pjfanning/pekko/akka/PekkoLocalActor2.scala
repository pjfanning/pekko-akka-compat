package com.github.pjfanning.pekko.akka

import org.apache.pekko
import pekko.actor.Actor
import pekko.event.Logging

class PekkoLocalActor2 extends Actor {
  val log = Logging(context.system, this)
  override def preStart() = {
    val remoteAkkaActor = context.actorSelection("akka.tcp://akka-remote-system@127.0.0.1:2553/user/akkaRemoteActor")
    log.info("remote-akka-actor: " + remoteAkkaActor)
    remoteAkkaActor ! "pekko says hi"
  }
  def receive = {
    case x =>
      log.info("Response: {}", x)
  }
}