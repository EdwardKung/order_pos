name := "order_pos"

version := "0.1"

scalaVersion := "2.12.4"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.11",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.11" % Test,
"com.typesafe.akka" %% "akka-remote" % "2.5.11"
)