package play2circe

import org.scalacheck.Prop.forAll
import org.scalacheck.{Prop, Properties}
import play.api.libs.json.{Json, Reads, Writes}
import io.circe.parser.parse

object DecoderSpec extends Properties("Decoder") with Generators {

  property("decode nulls")    = Prop(check(Option.empty[Int])(Reads.optionWithNull, implicitly))
  property("decode booleans") = Prop(check(true) && check(false))
  property("decode integers") = forAll(check(_: Long))
  property("decode strings")  = forAll(check(_: String))
  property("decode arrays")   = forAll(check(_: List[String]))
  property("decode objects")  = forAll(check(_: Map[String, String]))

  private def check[A](value: A)(implicit reads: Reads[A], writes: Writes[A]): Boolean = {
    val json        = writes.writes(value).toString
    val circeResult = parse(json).flatMap(decoderFromReads(reads).decodeJson)
    val playResult  = Json.parse(json).validate[A].asEither

    circeResult == playResult
  }
}
