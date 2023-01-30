ThisBuild / version := "0.2.0"
ThisBuild / scalaVersion := "3.2.1"
ThisBuild / scalacOptions ++= Seq(
  "-indent",
  "-new-syntax",
  "-Xfatal-warnings",
)
ThisBuild / organization := "io.github.mimoguz"
ThisBuild / licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

lazy val core = project
  .in(file("core"))
  .settings(
    moduleName := "layeredfonticon-core",
    name := "core",
  )

lazy val flat = project
  .in(file("flat"))
  .dependsOn(core)
  .aggregate(core)
  .settings(
    moduleName := "layeredfonticon-flat",
    name := "flat",
    libraryDependencies += "com.formdev" % "flatlaf" % "3.0",
  )

lazy val basic = project
  .in(file("basic"))
  .dependsOn(core)
  .aggregate(core)
  .settings(
    moduleName := "layeredfonticon-basic",
    name := "basic",
  )

lazy val scalaDemo = project
  .in(file("scalademo"))
  .dependsOn(basic)
  .aggregate(basic)
  .settings(
    name := "layeredfonticon-scalademo",
  )

lazy val javaDemo = project
  .in(file("javademo"))
  .dependsOn(basic)
  .aggregate(basic)
  .settings(
    name := "layeredfonticon-javademo",
  )

lazy val root = project
  .in(file("."))
  .dependsOn(core, basic, flat)
  .aggregate(core, basic, flat)
  .settings(
    moduleName := "layeredfonticon-all",
  )
