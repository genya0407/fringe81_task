object Fringe {
  def main(args: Array[String]): Unit = {
    show(Some("hoge"))
    show(None)
  }

  def show(value: Option[String]): Unit = {
  	value.map(v => println(v))
  }
}