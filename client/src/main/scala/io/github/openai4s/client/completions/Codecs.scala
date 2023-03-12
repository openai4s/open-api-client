package io.github.openai4s.client.completions

object Codecs {

  implicit val encOpt: io.circe.Encoder[Either[String, Int]] = ???

}
