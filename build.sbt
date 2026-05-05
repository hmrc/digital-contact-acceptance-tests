lazy val root = (project in file("."))
  .settings(
    name := "digital-contact-acceptance-tests",
    version := "1.0",
    scalaVersion := "3.3.4",
    libraryDependencies ++= Dependencies.test
  )
addCommandAlias("scalafmtAll", "all scalafmtSbt scalafmt Test/scalafmt")
