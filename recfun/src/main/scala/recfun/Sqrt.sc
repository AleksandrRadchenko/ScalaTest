object Sqrt {

  val proximity = 0.1
  val estimate = 1
  def isCloseEnough(a: Double, b: Double) =
    if (a < b) b - a < proximity else a - b < proximity

  def sqrt(x: Double): Double = {
    def loop(guess: Double): Double = {
      val next = (x / guess + guess) / 2
      if (isCloseEnough(next, guess)) next else loop(next)
    }
    loop(estimate)
  }

  val x = 4
  sqrt(x)
}

