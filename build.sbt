ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

val akkaVersion = "2.6.21"
val pekkoVersion = "1.0.1"

lazy val root = (project in file("."))
  .settings(
    name := "pekko-akka-compat",
    libraryDependencies := Seq(
      "com.typesafe.akka" %% "akka-remote" % akkaVersion,
      //"org.apache.pekko" %% "pekko-remote" % pekkoVersion,
      "org.apache.pekko" %% "pekko-actor" % pekkoVersion,
      "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
      "org.apache.pekko" %% "pekko-pki" % pekkoVersion,
      "org.apache.pekko" %% "pekko-protobuf-v3" % pekkoVersion,
      "org.agrona" % "agrona" % "1.15.1",
      "io.netty" % "netty" % "3.10.6.Final"
    )
  )
