package io.github.openai4s.client.v1

import cats.effect.Async
import io.github.openai4s.model.OpenAIHost
import io.github.openai4s.v1.chat.{Client, CreateChatCompletionResponse}
import io.github.openai4s.v1.chat.definitions.CreateChatCompletionRequest
import org.http4s.client.{Client => Http4sClient}
import org.http4s.headers.Authorization

class ChatClient[F[_]](auth: Authorization, host: OpenAIHost = OpenAIHost.default)(implicit F: Async[F], httpClient: Http4sClient[F]) {

  private val underlying = Client.httpClient[F](httpClient, host.url)


  def createChatCompletion(body: CreateChatCompletionRequest): F[CreateChatCompletionResponse] = underlying.createChatCompletion(body, List(auth))

}
