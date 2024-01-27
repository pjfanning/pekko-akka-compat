ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

ThisBuild / resolvers += Resolver.ApacheMavenSnapshotsRepo


val pekkoVersion = "1.1.0-M0+289-8e7ae52a-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "pekko-akka-compat",
    libraryDependencies := Seq(
      "org.apache.pekko" %% "pekko-actor" % pekkoVersion,
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
      "org.apache.pekko" %% "pekko-distributed-data" % pekkoVersion,
      "org.apache.pekko" %% "pekko-pki" % pekkoVersion,
      "org.apache.pekko" %% "pekko-protobuf-v3" % pekkoVersion,
      //1.1.0-M0+231-2b89026a-SNAPSHOT
      ("org.apache.pekko" %% "pekko-remote" % pekkoVersion)
        .excludeAll(ExclusionRule(organization = "org.apache.pekko")),
      ("org.apache.pekko" %% "pekko-cluster-typed" % pekkoVersion)
        .excludeAll(ExclusionRule(organization = "org.apache.pekko")),
      "io.netty" % "netty-all" % "4.1.104.Final",
      "io.netty" % "netty" % "3.10.6.Final",
      "org.agrona" % "agrona" % "1.15.1",
      "io.aeron" % "aeron-driver" % "1.38.1" % Runtime,
      "io.aeron" % "aeron-client" % "1.38.1" % Runtime,
      "org.slf4j" % "slf4j-simple" % "2.0.11" % Runtime
    )
  )
