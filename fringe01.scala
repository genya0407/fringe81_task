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
	}

	def show(value: Option[String]): Unit = {
		value.map(println(_))
	}

	def getFirst(value: Option[String]): String = {
		value.flatMap(_.headOption).mkString
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