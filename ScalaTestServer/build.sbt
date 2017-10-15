import Settings._

name := "Test"

version := "1.0"

scalaVersion := "2.12.3"

val akkaHttpVersion = "10.0.5"
val akkaSprayJsonVersion = "10.0.10"
val ioSprayJsonVersion = "1.3.3"
val scalaReflectVersion = "2.12.3"

//libraryDependencies += "com.lihaoyi" %% "upickle" % "0.4.3"
libraryDependencies += "com.lihaoyi" %% "upickle" % "0.4.4"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % akkaSprayJsonVersion
libraryDependencies += "io.spray" %% "spray-json" % ioSprayJsonVersion
libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaReflectVersion
