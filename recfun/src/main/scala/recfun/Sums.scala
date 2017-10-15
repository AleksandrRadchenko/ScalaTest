package recfun

import scala.annotation.tailrec

object Sums extends App {
    def sums(f: Int => Int, a: Int, b: Int): Int = {
      if (a > b) 0 else f(a) + sums(f, a + 1, b)
    }
    println(sums(x => x*x, 2, 4))

  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)
  println(fact(4))
}
