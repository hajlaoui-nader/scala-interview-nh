import sbt._

object Dependencies {
  object Versions {
    val akkaActors = "2.6.9"
    val akkaStream = "2.6.9"
  }
  object Librairies {
    val akkaActors = "com.typesafe.akka" %% "akka-actor"  % Versions.akkaActors
    val akkaStream = "com.typesafe.akka" %% "akka-stream" % Versions.akkaStream
  }
}
