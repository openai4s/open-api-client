
val CatsVersion             = "2.6.1"
val ScalaCheckVersion       = "1.15.4"
val ScalaTestVersion        = "3.2.9"
val ScalaTestPlusVersion    = "3.2.9.0"
val http4sV = "0.23.18"
val circeV = "0.14.4"
val logbackClassicV = "1.2.3"

inThisBuild(List(
  organization := "io.github.openai4s",
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "paualarco",
      "Pau Alarcón Cerdan",
      "pau.alarcon@gmail.com",
      url("https://github.com/paualarco")
    )
  ),
))

lazy val client = project
  .in(file("open-api-client"))
  .settings(
    name:= "open-api-client",
    scalaVersion := "2.12.14",
    crossScalaVersions := Seq("2.12.14", "2.13.6", "3.0.2"),
    Global / onChangedBuildSource := ReloadOnSourceChanges,
    scalacOptions += "-Wconf:any:s",
    Compile / guardrailTasks := List(
     // ScalaClient(file("client/src/main/resources/v1/completions.yaml"), encodeOptionalAs = codingOptional,
     //   decodeOptionalAs = codingRequiredNullable, pkg="io.github.openai4s.v1.completions", framework="http4s", tracing = false, imports = List("_root_.io.github.openai4s.codecs._")),
      ScalaClient(file("client/src/main/resources/v1/chat.yaml"), encodeOptionalAs = codingOptional,
        decodeOptionalAs = codingRequiredNullable, pkg="io.github.openai4s.v1.chat", framework="http4s", tracing = false, imports = List("_root_.io.github.openai4s.codecs._")),
    ),
    // Adding dependencies in order to force Scala Steward to help us
    // update the g8 template as well
    libraryDependencies ++= Seq(
      "org.http4s"                  %% "http4s-dsl"                 % http4sV,
      "org.http4s"                  %% "http4s-ember-server"        % http4sV,
      "org.http4s"                  %% "http4s-ember-client"        % http4sV,
      "org.http4s"                  %% "http4s-circe"               % http4sV,
      "io.circe"                    %% "circe-core"                 % circeV,
      "io.circe"                    %% "circe-generic"              % circeV,
      "io.circe"                    %% "circe-parser"               % circeV,
      "ch.qos.logback"              % "logback-classic"             % logbackClassicV,

      "org.scalatest"     %% "scalatest"        % ScalaTestVersion     % Test,
      "org.scalatestplus" %% "scalacheck-1-15"  % ScalaTestPlusVersion % Test,
      "org.scalacheck"    %% "scalacheck"       % ScalaCheckVersion    % Test,
    )
  )


lazy val root = (project in file(".")).aggregate(client)