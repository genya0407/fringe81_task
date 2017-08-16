object Fringe {
  def main(args: Array[String]): Unit = {
    show(Some("hello"))
    show(None)
  }

  def show(value: Option[String]): Unit = {
  	value.map(v => println(v))
  }
}