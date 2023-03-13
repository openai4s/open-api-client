
val CatsVersion             = "2.6.1"
val ScalaCheckVersion       = "1.15.4"
val BetterMonadicForVersion = "0.3.1"
val ScalaTestVersion        = "3.2.9"
val ScalaTestPlusVersion    = "3.2.9.0"
val http4sV = "0.23.18"
val circeV = "0.14.4"
val logbackClassicV = "1.2.3"
val AkkaActor = "2.6.20"
val AkkaHttp = "10.2.10"

lazy val client = project
  .in(file("client"))
  .settings(
    scalaVersion := "2.12.14",
    crossScalaVersions := Seq("2.12.14", "2.13.6", "3.0.2"),
    Global / onChangedBuildSource := ReloadOnSourceChanges,
    scalacOptions += "-Wconf:any:s",
    Compile / guardrailTasks := List(
      ScalaClient(file("client/src/main/resources/completions.yaml"), pkg="io.github.openai4s.completions",  framework="http4s", tracing = false, imports = List("_root_.io.github.openai4s.client.completions.Codecs._")),
    ),
    // Adding dependencies in order to force Scala Steward to help us
    // update the g8 template as well
    libraryDependencies ++= Seq(

      "org.http4s"                  %% "http4s-dsl"                 % http4sV,
      "org.http4s"                  %% "http4s-ember-server"        % http4sV,
      "org.http4s"                  %% "http4s-ember-client"        % http4sV,
      "org.http4s"                  %% "http4s-circe"               % http4sV,
      //"com.typesafe.akka" %% "akka-actor"    % AkkaActor,
      //"com.typesafe.akka" %% "akka-http"     % AkkaHttp,
      //"com.typesafe.akka" %% "akka-stream"   % AkkaActor,
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


