ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

val akkaVersion = "2.6.21"
val pekkoVersion = "1.0.1"

lazy val root = (project in file("."))
  .settings(
    name := "pekko-akka-compat",
    libraryDependencies := Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-remote" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster-typed" % akkaVersion,
      //"org.apache.pekko" %% "pekko-remote" % pekkoVersion,
      "org.apache.pekko" %% "pekko-actor" % pekkoVersion,
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
      "org.apache.pekko" %% "pekko-pki" % pekkoVersion,
      "org.apache.pekko" %% "pekko-protobuf-v3" % pekkoVersion,
      ("org.apache.pekko" %% "pekko-cluster" % pekkoVersion)
        .exclude("org.apache.pekko", "pekko-remote"),
      "org.apache.pekko" %% "pekko-cluster-typed" % pekkoVersion,
      "org.agrona" % "agrona" % "1.15.1",
      "io.aeron" % "aeron-driver" % "1.38.1" % Runtime,
      "io.aeron" % "aeron-client" % "1.38.1" % Runtime,
      "io.netty" % "netty" % "3.10.6.Final" % Runtime,
      "org.slf4j" % "slf4j-simple" % "1.7.36" % Runtime
    )
  )
