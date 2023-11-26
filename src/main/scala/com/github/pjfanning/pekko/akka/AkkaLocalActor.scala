package com.github.pjfanning.pekko.akka

import akka.actor.Actor
import akka.event.Logging

class AkkaLocalActor extends Actor {
  val log = Logging(context.system, this)
  override def preStart() = {
    val remoteActor = context.actorSelection("akka,tcp://akka-remote-system@127.0.0.1:2552/user/akkaRemoteActor")
    log.info("remote-actor: " + remoteActor)
    remoteActor ! "hi"
  }
  def receive = {
    case x =>
      log.info("Received {}", x)
  }
}