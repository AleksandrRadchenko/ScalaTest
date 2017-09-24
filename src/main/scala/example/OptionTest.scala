package example

class OptionTest {
  def toInt(in: String): Option[Int] = {
    try {
      Some(Integer.parseInt(in.trim))
    } catch {
      case e:
        NumberFormatException => None
    }
  }
}

object OptionTest extends App {
  val optionTest = new OptionTest
  println(
    printResult(
      optionTest.
        toInt("123")))
  println(printResult(optionTest.toInt("asd")))

  def printResult(result : Option[Int]) : String = {
    result match {
      case Some(i) => i.toString
      case None => "Can't parse for integer"
    }
  }
}


