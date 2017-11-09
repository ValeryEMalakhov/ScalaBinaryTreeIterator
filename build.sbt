import Dependencies._

name := "ScalaBinaryTreeIterator"

version := "0.1.2"

scalaVersion := "2.12.3"

scalaSource in Test := baseDirectory.value / "src" / "test" / "scala"

libraryDependencies ++= Seq(
  specs2
)