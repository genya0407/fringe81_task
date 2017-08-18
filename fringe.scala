import scala.concurrent.{Future, Promise, Await}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

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

    // Q6
    val r1 = firstOf(Future{ Thread.sleep(9000); 1 }, Future{ Thread.sleep(3000); 2 })
	println(Await.result(r1, Duration.Inf))
  }

  def show(value: Option[String]): Unit = {
  	value.foreach(println(_))
  }

  def getFirst(value: Option[String]): String = {
    value.flatMap(_.headOption).mkString
  }

  def passStudents(scores: Map[String, Map[String, Int]]): Map[String, Int] = {
    scores.mapValues { subjects: Map[String, Int] =>
      for {
        english_score <- subjects.get("english")
        math_score <- subjects.get("math")
      } yield { (english_score + math_score) / 2 }
    }
  } collect {
    case (key, Some(score)) if score > 80 => key -> score
  }

  def firstOf[A](v1: Future[A], v2: Future[A]): Future[A] = {
  	val promise = Promise[A]
  	v1.onSuccess { case value => if (!promise.isCompleted) promise.success(value) }
  	v2.onSuccess { case value => if (!promise.isCompleted) promise.success(value) }
  	promise.future
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