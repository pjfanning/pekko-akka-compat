package com.github.pjfanning.pekko.akka

import org.apache.pekko
import pekko.actor.Actor
import pekko.event.Logging

class PekkoLocalActor extends Actor {
  val log = Logging(context.system, this)
  override def preStart() = {
    val remoteActor = context.actorSelection("pekko.tcp://pekko-remote-system@127.0.0.1:7356/user/pekkoRemoteActor")
    log.info("remote-actor: " + remoteActor)
    remoteActor ! "hi"
  }
  def receive = {
    case x =>
      log.info("Response: {}", x)
  }
}