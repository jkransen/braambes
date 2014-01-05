organization := "framboos"

name := "braambes"

version := "0.0.1-SNAPSHOT"

scalaVersion := Version.scala

libraryDependencies ++= Dependencies.braambes

unmanagedSourceDirectories in Compile := List((scalaSource in Compile).value)

unmanagedSourceDirectories in Test := List((scalaSource in Test).value)

scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

// initialCommands := "import braambes._"

EclipseKeys.withSource := true