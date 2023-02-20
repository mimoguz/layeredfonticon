import xerial.sbt.Sonatype._

sonatypeProfileName := "io.github.mimoguz"
publishMavenStyle := true
licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
sonatypeProjectHosting := Some(GitHubHosting("mimoguz", "layeredfonticon", "mimoguz.publish@outlook.com"))
