val CatsVersion             = "2.6.1"
val CatsEffectVersion       = "3.2.7"
val ScalaCheckVersion       = "1.15.4"
val BetterMonadicForVersion = "0.3.1"
val ScalaTestVersion        = "3.2.9"
val ScalaTestPlusVersion    = "3.2.9.0"

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.12.14",
    crossScalaVersions := Seq("2.12.14", "2.13.6", "3.0.2"),
    resolvers += Resolver.url("typesafe", url("https://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns),
    Global / onChangedBuildSource := ReloadOnSourceChanges,
     Compile / guardrailTasks := List(
      ScalaClient(file("src/main/resources/openapi.yaml"), pkg="io.github.paualarco.openai4s.client"),
      ScalaModels(file("src/main/resources/openapi.yaml"), pkg="io.github.paualarco.openai4s.models"),
     ),
    // Adding dependencies in order to force Scala Steward to help us
    // update the g8 template as well
    libraryDependencies ++= Seq(
      "dev.guardrail" %% "guardrail-core" % "0.75.3",
      "org.scalatest"     %% "scalatest"        % ScalaTestVersion     % Test,
      "org.scalatestplus" %% "scalacheck-1-15"  % ScalaTestPlusVersion % Test,
      "org.scalacheck"    %% "scalacheck"       % ScalaCheckVersion    % Test,
      "org.typelevel"     %% "cats-core"        % CatsVersion          % Test,
      "org.typelevel"     %% "cats-effect"      % CatsEffectVersion    % Test,
      "org.typelevel"     %% "cats-effect-laws" % CatsEffectVersion    % Test,
      "org.typelevel"     %% "cats-laws"        % CatsVersion          % Test,
    )
  )

