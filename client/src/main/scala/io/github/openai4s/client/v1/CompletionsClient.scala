package io.github.openai4s.client.v1

import cats.effect.Async
import io.github.openai4s.model.OpenAIHost
//import io.github.openai4s.v1.completions.definitions.CreateCompletionRequest
//import io.github.openai4s.v1.completions.{Client, CreateCompletionResponse}
import org.http4s.client.{Client => Http4sClient}
import org.http4s.headers.Authorization

class CompletionsClient[F[_]](auth: Authorization, host: OpenAIHost = OpenAIHost.default)(implicit F: Async[F], httpClient: Http4sClient[F]) {

  //private val underlying = Client.httpClient[F](httpClient, host.url)

  //def createCompletion(body: CreateCompletionRequest): F[CreateCompletionResponse] = underlying.createCompletion(body, List(auth))

}
