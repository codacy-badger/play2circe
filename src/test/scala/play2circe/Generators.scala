package play2circe

import org.scalacheck.{Arbitrary, Gen}

trait Generators {

  implicit protected final val noControlStrings: Arbitrary[String] = Arbitrary(
    Arbitrary.arbString.arbitrary.filter(!_.exists(Character.isISOControl))
  )

  implicit protected final val lists: Arbitrary[List[String]] = Arbitrary(Gen.listOfN(5, noControlStrings.arbitrary))

  implicit protected final val maps: Arbitrary[Map[String, String]] = Arbitrary(
    Gen.listOfN(5, noControlStrings.arbitrary.map(string => string -> string)).map(_.toMap)
  )
}
