import xerial.sbt.Sonatype._

ThisBuild / version := "0.2.0"
ThisBuild / scalaVersion := "3.2.1"
ThisBuild / scalacOptions ++= Seq(
  "-indent",
  "-new-syntax",
  "-Xfatal-warnings",
)

lazy val commonSettings = Seq(
  organization := "io.github.mimoguz",
  licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt")),
  developers := List(
    Developer(
      id = "mimoguz", 
      name = "Mumtaz Oguz Tas", 
      email = "mimoguz.publish@outlook.com", 
      url = url("https://github.com/mimoguz"),
    )
  ),
  publishTo := sonatypePublishToBundle.value,
  sonatypeCredentialHost := "s01.oss.sonatype.org",
  sonatypeProjectHosting := Some(GitHubHosting("mimoguz", "layeredfonticon", "mimoguz.publish@outlook.com")),
  publishMavenStyle := true,
)

lazy val core = project
  .in(file("core"))
  .settings(
    commonSettings,
    moduleName := "layeredfonticon-core",
    name := "core",
  )

lazy val flat = project
  .in(file("flat"))
  .dependsOn(core)
  .aggregate(core)
  .settings(
    commonSettings,
    moduleName := "layeredfonticon-flat",
    name := "flat",
    libraryDependencies += "com.formdev" % "flatlaf" % "3.0",
  )

lazy val basic = project
  .in(file("basic"))
  .dependsOn(core)
  .aggregate(core)
  .settings(
    commonSettings,
    moduleName := "layeredfonticon-basic",
    name := "basic"
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
    publishArtifact := false,
  )
