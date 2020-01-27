package play2circe

import org.scalacheck.{Arbitrary, Gen}

trait Generators {

  implicit protected final val chars: Arbitrary[Char] = Arbitrary(
    Gen.oneOf((Char.MinValue to Char.MaxValue).filter(Character.isLetterOrDigit))
  )

  implicit protected final val strings: Arbitrary[String] = Arbitrary(
    Gen.nonEmptyListOf(chars.arbitrary).map(_.mkString(""))
  )

  implicit protected final val lists: Arbitrary[List[String]] = Arbitrary(Gen.nonEmptyListOf(strings.arbitrary))

  implicit protected final val maps: Arbitrary[Map[String, String]] = Arbitrary(
    Gen.nonEmptyListOf(strings.arbitrary.map(string => string -> string)).map(_.toMap)
  )
}
