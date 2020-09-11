import Dependencies._

name := "scala-interview-nh"

version := "0.1"

scalaVersion := "2.13.3"
libraryDependencies ++= Seq(
  Librairies.akkaActors,
  Librairies.akkaStream
)
