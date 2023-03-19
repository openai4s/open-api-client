package io.github.openai4s.client.v1

import cats.effect.{Async, IO}
import org.http4s.client.{Client => Http4sClient}
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}
import org.scalatest.matchers.should.Matchers
import io.github.openai4s.client.v1.ChatClient
import cats.effect._
import cats.effect.unsafe.implicits.global
import io.github.openai4s.v1.chat.definitions.{ChatCompletionRequestMessage, CreateChatCompletionRequest}
import org.http4s.client._
import org.http4s.ember.client.EmberClientBuilder
import io.github.openai4s.model.ApiKey
import io.github.openai4s.v1.chat.CreateChatCompletionResponse

class ChatClientTest extends AsyncFlatSpec with Matchers {

  val client = EmberClientBuilder
    .default[IO]
    .build

  val apiKey = ApiKey("your-api-key")
  "ChatClient" should "implement createChatCompletion" in {

    client.use{ implicit cli =>
      val chatCli = new OpenAI[IO](apiKey)(IO.asyncForIO, cli).chatClient
      val message: ChatCompletionRequestMessage = ChatCompletionRequestMessage(ChatCompletionRequestMessage.Role.User, "how many countries does Europe have?")
      val messageAssistant: ChatCompletionRequestMessage = ChatCompletionRequestMessage(ChatCompletionRequestMessage.Role.Assistant, "do not add linebreaks to the response")

      val req = CreateChatCompletionRequest("gpt-3.5-turbo", Vector(message, messageAssistant))
      val resp: CreateChatCompletionResponse = chatCli.createChatCompletion(req).unsafeRunSync()
      resp.fold(a => IO(a.choices.flatMap(_.message.map(_.content)).mkString), badRequest => IO(badRequest.message))

    }.unsafeToFuture().map(_ should include("44"))
  }
}
