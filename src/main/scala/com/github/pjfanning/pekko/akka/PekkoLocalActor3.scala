package com.github.pjfanning.pekko.akka

import org.apache.pekko
import org.apache.pekko.actor.Actor
import org.apache.pekko.event.Logging

class PekkoLocalActor3 extends Actor {
  val log = Logging(context.system, this)
  override def preStart() = {
    val remoteAkkaActor = context.actorSelection("akka://akka-remote-system@127.0.0.1:25521/user/akkaRemoteActor")
    log.info("remote-akka-actor: " + remoteAkkaActor)
    remoteAkkaActor ! "pekko says hi"
  }
  def receive = {
    case x =>
      log.info("Response: {}", x)
  }
}