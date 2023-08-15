ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / scalaVersion := "3.2.1"

Compile / scalaSource := baseDirectory.value / "src" / "main" / "scala"
Test / scalaSource := baseDirectory.value / "src" / "test" / "scala"

val akkaVersion = "2.8.0"

lazy val root = (project in file("."))
    .settings(
      name := "akka-essentials-with-scala-rock-the-jvm"
    )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.2.15"
)
