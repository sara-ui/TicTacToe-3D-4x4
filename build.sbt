import java.util

name := "TicTacToe-3D-4x4"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"
libraryDependencies += "org.scalafx" %% "scalafx" % "12.0.2-R18"


unmanagedJars in Compile +=
  Attributed.blank(
    file(scala.util.Properties.javaHome) / "/lib/jfxrt.jar"
  )