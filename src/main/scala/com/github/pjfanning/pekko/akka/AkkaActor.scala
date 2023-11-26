package com.github.pjfanning.pekko.akka

import akka.actor.Actor
import akka.event.Logging

class AkkaActor extends Actor {
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