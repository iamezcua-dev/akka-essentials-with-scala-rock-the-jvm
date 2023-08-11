name := "udemy-akka-essentials"
version := "0.0.1"
scalaVersion := "2.12.7"

Compile / scalaSource := baseDirectory.value / "src" / "main" / "scala"
Test / scalaSource := baseDirectory.value / "src" / "test" / "scala"

val akkaVersion = "2.8.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.2.15"
)