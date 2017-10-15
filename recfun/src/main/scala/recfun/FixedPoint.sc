object lecture2_3 {
  def modulus(a: Double, b: Double) = if (a > b) a - b else b - a
  val tolerance = 0.01


  def fixedPoint(f: Double => Double, initialGuess: Double): Double = {
    def isCloseEnough(x: Double) = modulus(f(x), x) < tolerance
    def loop(x: Double): Double = {
      if (isCloseEnough(x)) x else loop((f(x) + x) / 2)
    }
    loop(initialGuess)
  }

  def func(x: Double) = -2 + x*x

  fixedPoint(func, 0.9)
}