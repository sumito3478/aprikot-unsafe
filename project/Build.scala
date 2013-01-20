import sbt._
import Keys._

import info.sumito3478.aprikot.sbt._

object build extends Build {

  lazy val project = Project(
    id = "aprikot-unsafe",
    base = file(".")
  ).settings(StandardProject.newSettings :_*
  ).settings(
    Seq(
      libraryDependencies ++= Seq(
        "net.java.dev.jna" % "jna" % "3.5.+"
        ),
      version := "0.0.5-SNAPSHOT"
    ): _*
  )

}

