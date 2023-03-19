package io.github.openai4s.client.v1

import cats.effect.kernel.Resource
import cats.effect.{Async, IO}
import io.github.openai4s.model.{ApiKey, OpenAIHost}
import org.http4s.{AuthScheme, Credentials}
import org.http4s.client.{Client => Http4sClient}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.headers.Authorization

/**
 * A client for accessing the OpenAI API.
 *
 * @param apiKey the API key used to authenticate requests to the OpenAI API
 * @param host the OpenAI API host to use
 * @param F an asynchronous effect type, such as `IO` or `Async[F]`
 * @param httpClient an HTTP client instance
 * @tparam F the effect type used by this client
 */
class OpenAI[F[_]](apiKey: ApiKey, host: OpenAIHost = OpenAIHost.default)(implicit F: Async[F], httpClient: Http4sClient[F]) {

  private val auth: Authorization = Authorization.apply(Credentials.Token.apply(AuthScheme.Bearer, apiKey.toString))

  /**
   * Creates a new chat client for interacting with the OpenAI chat endpoint.
   *
   * @return a new `ChatClient` instance
   */
  val chatClient: ChatClient[F] = new ChatClient(auth, host)(F, httpClient)

}


/**
 * Companion object for creating `OpenAI` instances.
 */
object OpenAI {

  /**
   * Creates a new `OpenAI` instance using the provided HTTP client instance.
   *
   * @param apiKey the API key used to authenticate requests to the OpenAI API
   * @param host the OpenAI API host to use
   * @param httpClient an HTTP client instance
   * @tparam F the effect type used by this client
   * @return a new `OpenAI` instance
   */
  def fromHttpClient[F[_]](apiKey: ApiKey, host: OpenAIHost = OpenAIHost.default)(implicit F: Async[F], httpClient: Http4sClient[F]): OpenAI[F] = {
    new OpenAI[F](apiKey, host)
  }


  /**
   * Creates a resource for managing the `OpenAI` instance and its dependencies.
   *
   * @param apiKey the API key used to authenticate requests to the OpenAI API
   * @param host the OpenAI API host to use
   * @tparam F the effect type used by this client
   * @return a `Resource` containing a new `OpenAI` instance
   */
  def resource[F[_]](apiKey: ApiKey, host: OpenAIHost = OpenAIHost.default)(implicit  F: Async[F]): Resource[F, OpenAI[F]] = {
    val client = EmberClientBuilder
      .default[F]
      .build
    client.map(implicit cli => fromHttpClient(apiKey, host))
  }
}
