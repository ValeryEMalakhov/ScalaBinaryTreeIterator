import Dependencies._

name := "ScalaWorkTask2"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.3"

scalaSource in Test := baseDirectory.value / "src" / "test" / "scala"

libraryDependencies ++= Seq(
  jUnit
)