import Dependencies._

name := "ScalaWorkTask2"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.3"

enablePlugins(JmhPlugin)

scalaSource in Jmh := baseDirectory.value / "src" / "jmh" / "scala"

libraryDependencies ++= Seq(
  jUnit
)