package recfun

object Currying extends App {
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0 else f(a) + sumF(a+1, b)
    sumF
  }
}

object CurryingProduct extends App {
  def product(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, prod, prod_zero)(a,b)
  product(x => x*x)(3, 4)

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
      if (a > b) zero else combine(f(a), mapReduce( f, combine, zero)(a+1, b))

  def prod(a: Int, b: Int) = a * b
  def prod_zero = 1
  def sum(a: Int, b: Int) = a + b
  def sum_zero = 0
  def f(a: Int) = a
  def fact(x: Int): Int = if (x == 1) 1 else x * fact(x - 1)
  mapReduce(fact, prod, prod_zero)(2, 4)
}
