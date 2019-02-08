name := """play-bench-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.3.1"