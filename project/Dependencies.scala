import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"                    %% "ui-test-runner"                     % "0.53.0"     % Test,
    "org.playframework"              %% "play-ahc-ws-standalone"             % "3.0.10"     % Test,
    "com.typesafe.play"              %% "play-json-joda"          % "2.10.8"    % Test
  )
}
