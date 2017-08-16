object Fringe {
	def main(args: Array[String]): Unit = {
		show(Some("hello"))
		show(None)

		assert_eq(getFirst(Some("hello")), "h")
		assert_eq(getFirst(Some("")), "")
		assert_eq(getFirst(None), "")
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