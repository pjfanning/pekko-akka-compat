package com.github.pjfanning.pekko.akka

import com.typesafe.config.ConfigFactory
import org.apache.pekko

object PekkoAkkaAeronCluster extends App {
  val clusterConfig = ConfigFactory.load("cluster-application")

  startupAkka()
  startupPekko(17355)
  Thread.sleep(10000)

  object AkkaRootBehavior {
    def apply(): akka.actor.typed.Behavior[Nothing] = akka.actor.typed.scaladsl.Behaviors.setup[Nothing] { context =>
      // Create an actor that handles cluster domain events
      context.spawn(AkkaClusterListener(), "AkkaClusterListener")

      akka.actor.typed.scaladsl.Behaviors.empty
    }
  }

  object PekkoRootBehavior {
    def apply(): pekko.actor.typed.Behavior[Nothing] = pekko.actor.typed.scaladsl.Behaviors.setup[Nothing] { context =>
      // Create an actor that handles cluster domain events
      context.spawn(PekkoClusterListener(), "PekkoClusterListener")

      pekko.actor.typed.scaladsl.Behaviors.empty
    }
  }

  def startupAkka(): Unit = {
    val ports =
      if (args.isEmpty)
        Seq(25251, 25252, 0)
      else
        args.toSeq.map(_.toInt)
    ports.foreach(startupAkka)
  }

  def startupAkka(port: Int): Unit = {
    // Override the configuration of the port
    val config = ConfigFactory.parseString(
      s"""
      akka.remote.artery.canonical.port=$port
      """).withFallback(clusterConfig)

    // Create an Akka system
    akka.actor.typed.ActorSystem[Nothing](AkkaRootBehavior(), "ClusterSystem", config)
  }

  def startupPekko(port: Int): Unit = {
    // Override the configuration of the port
    val config = ConfigFactory.parseString(
      s"""
      pekko.remote.artery.canonical.port=$port
      """).withFallback(clusterConfig)

    // Create an Akka system
    pekko.actor.typed.ActorSystem[Nothing](PekkoRootBehavior(), "ClusterSystem", config)
  }

}