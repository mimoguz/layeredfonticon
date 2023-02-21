import xerial.sbt.Sonatype._

sonatypeProfileName := "io.github.mimoguz"
sonatypeCredentialHost := "s01.oss.sonatype.org"
publishMavenStyle := true
licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
developers := List(
  Developer(id = "mimoguz", name = "Mumtaz Oguz Tas", email = "mimoguz.publish@outlook.com", url = url("https://github.com/mimoguz"))
)
sonatypeProjectHosting := Some(GitHubHosting("mimoguz", "layeredfonticon", "mimoguz.publish@outlook.com"))
publishTo := sonatypePublishToBundle.value