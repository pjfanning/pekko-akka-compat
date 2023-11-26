package com.github.pjfanning.pekko.akka

import org.apache.pekko
import pekko.actor.Actor
import pekko.event.Logging

class PekkoActor extends Actor {
  val log = Logging(context.system, this)
  override def preStart() = {
    log.info("Starting")
  }
  def receive = {
    case x =>
      log.info("Received {}", x)
      sender ! "Received " + x
  }
}