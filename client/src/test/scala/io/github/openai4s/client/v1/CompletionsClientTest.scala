package io.github.openai4s.client.v1

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import io.github.openai4s.codecs
import io.github.openai4s.model.ApiKey
//import io.github.openai4s.v1.completions.definitions.CreateCompletionRequest
import org.http4s.ember.client.EmberClientBuilder
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

class CompletionsClientTest extends AsyncFlatSpec with Matchers {

  val client = EmberClientBuilder
    .default[IO]
    .build

  val apiKey = ApiKey("")

  /*"Completions" should "implement createCompletion" in {

    client.use{ implicit cli =>
      val completions =  OpenAI.fromHttpClient(apiKey)(IO.asyncForIO, cli).completions
      val prompt: codecs.Prompt = Left("write a function in python multiplies two arguments")
      val messageAssistant: ChatCompletionRequestMessage = ChatCompletionRequestMessage(ChatCompletionRequestMessage.Role.Assistant, "do not add linebreaks to the response")

      val req = CreateCompletionRequest("gpt-3.5-turbo", Some(prompt))
      val resp = completions.createCompletion(req).unsafeRunSync()
      resp.fold(a => IO(a.choices.flatMap(_.text.getOrElse(".")).mkString))

    }.unsafeToFuture().map(_ should include("testing"))
  }*/
}
