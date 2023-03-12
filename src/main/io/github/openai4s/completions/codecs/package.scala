package io.github.openai4s.completions

package object codecs {

  implicit val enc: io.circe.Encoder[Either[String, Int]] = ???
  implicit val encOptt: io.circe.Encoder[Either[String, Int]] = ???

}
