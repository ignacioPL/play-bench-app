name := """play-bench-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.postgresql"           %  "postgresql" % "42.2.5",
  "com.typesafe.play"        %% "play-slick" % "4.0.0",
  "com.softwaremill.macwire" %% "macros"     % "2.3.1",
  "com.softwaremill.common"  %% "tagging"    % "2.2.1"
)