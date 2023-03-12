val CatsVersion             = "2.6.1"
val ScalaCheckVersion       = "1.15.4"
val BetterMonadicForVersion = "0.3.1"
val ScalaTestVersion        = "3.2.9"
val ScalaTestPlusVersion    = "3.2.9.0"
val http4sV = "0.23.18"
val circeV = "0.14.3"
val logbackClassicV = "1.2.3"

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.13.6",
    crossScalaVersions := Seq("2.12.14", "2.13.6", "3.0.2"),
    Global / onChangedBuildSource := ReloadOnSourceChanges,
     Compile / guardrailTasks := List(
      ScalaClient(file("src/main/resources/openapi.yaml"), pkg="io.github.paualarco.openai4s.client", framework="http4s",
        encodeOptionalAs = codingOptional,
        decodeOptionalAs = codingRequiredNullable),
     // ScalaModels(file("src/main/resources/openapi.yaml"), pkg="io.github.paualarco.openai4s.models"),
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

      "dev.guardrail" %% "guardrail-core" % "0.75.3",
      "org.scalatest"     %% "scalatest"        % ScalaTestVersion     % Test,
      "org.scalatestplus" %% "scalacheck-1-15"  % ScalaTestPlusVersion % Test,
      "org.scalacheck"    %% "scalacheck"       % ScalaCheckVersion    % Test,
    )
  )

