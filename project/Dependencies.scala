import sbt._

object Dependencies {
  lazy val playJson    = "com.typesafe.play" %% "play-json"    % "2.8.0"
  lazy val circe       = "io.circe"          %% "circe-core"   % "0.12.3"
  lazy val circeParser = "io.circe"          %% "circe-parser" % "0.12.3" % Test
  lazy val scalaCheck  = "org.scalacheck"    %% "scalacheck"   % "1.14.1" % Test
}
