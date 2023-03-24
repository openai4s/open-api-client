package io.github.openai4s.model

case class OpenAIHost(url: String)

object OpenAIHost {
  val default = OpenAIHost("https://api.openai.com")
}
