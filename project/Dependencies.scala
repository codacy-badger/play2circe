import sbt._

object Dependencies {

  lazy val playJson = "com.typesafe.play" %% "play-json"  % "2.8.0"
  lazy val circe    = "io.circe"          %% "circe-core" % "0.12.3"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
}
