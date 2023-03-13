package io.github.openai4s.client.completions

object Codecs {

  implicit val promptEncoder: io.circe.Encoder[Option[Either[String, Either[Vector[String], Either[Vector[String], Vector[Vector[Int]]]]]]] = ???
  implicit val promptDecoder: io.circe.Decoder[Option[Either[String, Either[Vector[String], Either[Vector[String], Vector[Vector[Int]]]]]]] = ???
  implicit val stopEncoder: io.circe.Encoder[Option[Either[String, Vector[String]]]] = ???
  implicit val stopDecoder: io.circe.Decoder[Option[Either[String, Vector[String]]]] = ???

}
