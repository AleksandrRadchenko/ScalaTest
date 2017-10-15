package recfun

import scala.annotation.tailrec

object Fact extends App {
  override def main(args: Array[String]): Unit = {
    val lim = 30
    @tailrec
    def factorial(n: Long, accum: Long, prevAccum: Long): Long = {
        if (accum < 0) {
         println("Last positive n! for Long is for n = " + (n - 1).toString)
          prevAccum
        }
        else if (n == lim) accum else factorial(n + 1, accum * n, accum)
    }
    println(factorial(1, 1, 1))
  }
}
