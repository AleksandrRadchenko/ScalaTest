object sessoin {
  def abs(x:Double) = if (x < 0) -x else x

def sqrtIter(guess: Double, x: Double): Double =
  if (isGoodEnough(guess, x)) guess
  else sqrtIter(improve(guess, x), x)

def isGoodEnough(guess: Double, x: Double) =
//  abs(guess - x / guess) < 0.01
  abs(guess * guess - x) / x < 0.01
//  abs(guess/x * guess - 1) < 0.01

def improve(guess: Double, x: Double) =(guess + x / guess) / 2

def sqrt(x: Double) = sqrtIter(1.0, x)

  sqrt(2)
  sqrt(0.001)
  sqrt(0.1e-20)
  sqrt(1.0e20)
  sqrt(1.0e50)
}
