// Rational numbers (week3 in Odersky's code)

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x - y - z
y + y
x + y
x - y
x * y
x / y
x.less(y)
x.max(y)
new Rational(5,5)

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must not be zero")
  def this(x: Int) = this(x, 1)
  val numer = x
  val denum = y

  def neg = new Rational(-numer, denum)

  def +(a:Rational) =
    new Rational(numer * a.denum + a.numer * denum, denum * a.denum)

  def -(a: Rational) = this.+(a.neg)

  def *(a: Rational) =
    new Rational(numer * a.numer, denum * a.denum)

  def /(a: Rational) =
    new Rational(numer * a.denum, denum * a.numer)

  override def toString() = numer/gcd(x, y) + "/" + denum/gcd(x, y)

  def less(a: Rational) = numer * a.denum < a.numer * denum

  def max(a: Rational) = if (this.less(a)) a else this

  def equals(a: Rational) = numer == a.numer && denum == a.denum
  private def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)
  private def abs(a: Int) = if (a > 0) a else -a
}

