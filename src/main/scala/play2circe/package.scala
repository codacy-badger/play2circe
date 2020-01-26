import io.circe.syntax._
import io.circe._
import play.api.libs.json.{Json => PlayJson, _}

import scala.util.Try

package object play2circe {

  private def convert(value: JsValue): Json = {
    import io.circe.Encoder._

    value match {
      case JsNull           => None.asJson
      case JsBoolean(value) => value.asJson
      case JsNumber(value)  => value.asJson
      case JsString(value)  => value.asJson
      case JsArray(value)   => value.map(convert).asJson
      case JsObject(value)  => Json.obj(value.map { case (k, v) => k -> convert(v) }.toSeq: _*)
    }
  }

  def decoderFromReads[A: Reads]: Decoder[A] = Decoder.decodeJson.emap { json =>
    Try(PlayJson.parse(json.noSpaces).validate[A]).toEither.left.map(_.getMessage).flatMap {
      case JsSuccess(value, _) => Right(value)
      case JsError(errors)     => Left(errors.flatMap(_._2).map(_.message).mkString(", "))
    }
  }

  def encoderFromWrites[A](implicit writes: Writes[A]): Encoder[A] = Encoder.instance { value =>
    convert(writes.writes(value))
  }
}
