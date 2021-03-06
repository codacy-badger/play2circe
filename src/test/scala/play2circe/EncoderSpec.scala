package play2circe

import org.scalacheck.Prop.forAll
import org.scalacheck.{Prop, Properties}
import play.api.libs.json.Writes
import io.circe.parser.parse

object EncoderSpec extends Properties("Encoder") with Generators {

  property("encode nulls")    = Prop(check(Option.empty[Int]))
  property("encode booleans") = Prop(check(true) && check(false))
  property("encode integers") = forAll(check(_: Long))
  property("encode strings")  = forAll(check(_: String))
  property("encode arrays")   = forAll(check(_: List[String]))
  property("encode objects")  = forAll(check(_: Map[String, String]))

  private def check[A](value: A)(implicit writes: Writes[A]): Boolean = {
    val circeResult = encoderFromWrites[A](writes)(value).noSpaces
    val playResult  = writes.writes(value).toString

    parse(circeResult) == parse(playResult)
  }
}
