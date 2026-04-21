import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"                    %% "ui-test-runner"                     % "0.54.0"     % Test,
    "org.playframework"              %% "play-ahc-ws-standalone"             % "3.0.10"     % Test,
    "org.playframework"              %% "play-json"                          % "3.0.6"      % Test
  )
}
