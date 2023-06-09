package io.github.openai4s

import io.circe.{Decoder, Encoder, Json}
import io.circe.Decoder.{decodeInt, decodeOption, decodeString, decodeVector}
import io.circe.Encoder.{encodeEither, encodeOption, encodeString, encodeVector}

package object codecs {

    type Prompt = Either[String, Either[Vector[String], Either[Vector[Int], Vector[Vector[Int]]]]]
    type Stop = Either[String, Vector[String]]
    private type PromptR =  Either[Vector[String], Either[Vector[Int], Vector[Vector[Int]]]]
    private type PromptRR =  Either[Vector[Int], Vector[Vector[Int]]]

  private implicit val promptRRDecoder: Decoder[PromptRR] =  {
    val left:  Decoder[PromptRR]= decodeVector(decodeInt).map(Left.apply)
    val right: Decoder[PromptRR]= decodeVector(decodeVector(decodeInt)).map(Right.apply)
    left or right
  }

  private implicit val promptRDecoder: Decoder[PromptR] =  {
    val left:  Decoder[PromptR]= decodeVector(decodeString).map(Left.apply)
    val right: Decoder[PromptR]= promptRRDecoder.map(Right.apply)
    left or right
  }

    implicit val promptDecoder: Decoder[Prompt] =  {
      val left:  Decoder[Prompt]= decodeString.map(Left.apply)
      val right: Decoder[Prompt]= promptRDecoder.map(Right.apply)
      left or right
    }
  implicit val promptDecoderOpt: Decoder[Option[Prompt]] = decodeOption(promptDecoder)

  implicit val promptEncoder: Encoder[Prompt] = ???

  implicit val stopDecoder: Decoder[Stop] = {
    val left:  Decoder[Stop]= decodeString.map(Left.apply)
    val right: Decoder[Stop]= decodeVector(decodeString).map(Right.apply)
    left or right
  }
  implicit val stopDecoderOpt: Decoder[Option[Stop]] = decodeOption(stopDecoder)


  implicit val stopEncoderOpt: Encoder[Option[Either[String, Vector[String]]]] = {
    encodeOption(stopEncoder)
  }

  implicit val stopEncoder: Encoder[Either[String, Vector[String]]] = {
    encodeEither("stop", "stop")(encodeString, encodeVector(encodeString))
  }
  }
