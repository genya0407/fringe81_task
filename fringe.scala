object Fringe {
	def main(args: Array[String]): Unit = {
		// Q1
		show(Some("hello"))
		show(None)

		// Q2
		assert_eq(getFirst(Some("hello")), "h")
		assert_eq(getFirst(Some("")), "")
		assert_eq(getFirst(None), "")

		// Q3
		assert_eq(NumEnglishDictionary.translate(Some(1)), Some("one"))
		assert_eq(NumEnglishDictionary.translate(Some(3)), None)
		assert_eq(NumEnglishDictionary.translate(None), None)

		// Q5
		val scores = Map(
			"ichiro" -> Map("math"->82, "english"->99),
			"jiro" -> Map("japanese"->100, "social"->88),
			"saburo" -> Map("math"->76, "english"->80),
			"shiro" -> Map("math" -> 99, "social" -> 81),
			"hanako" -> Map("math"->84, "english"->78, "social"->66))
		val expected = Map("ichiro" -> 90, "hanako" -> 81)
		assert_eq(passStudents(scores), expected)
	}

	def show(value: Option[String]): Unit = {
		value.map(println(_))
	}

	def getFirst(value: Option[String]): String = {
		value.flatMap(_.headOption).mkString
	}

	def passStudents(scores: Map[String, Map[String, Int]]): Map[String, Int] = {
		scores.mapValues { subjects =>
			val mEnglish = subjects.get("english")
			val mMath = subjects.get("math")
			mEnglish.flatMap(english => mMath.map(math => (english + math)/2))
		}.collect {
			case (student, Some(score)) if score >= 80 => student -> score
		}
	}

	def assert_eq[Eq](a: Eq, b: Eq): Unit = {
		if (a == b) {
			print(".")
		} else {
			throw new Exception(s"`$a` is not `$b`")
		}
	}
}

object NumEnglishDictionary {
	private val dictionary = Map(1->"one", 2->"two")
	def translate(num: Option[Int]): Option[String] = {
		num.flatMap(dictionary.get(_))
	}
}