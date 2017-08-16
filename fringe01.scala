object Fringe {
	def main(args: Array[String]): Unit = {
		show(Some("hello"))
		show(None)
	}
	def show(value: Option[String]): Unit = {
		value.map(println(_))
	}
	def assert_eq[Eq](a: Eq, b: Eq): Unit = {
		if (a == b) {
			print(".")
		} else {
			throw new Exception(s"`$a` is not `$b`")
		}
	}
}