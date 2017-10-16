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

class Rational(x: Int, y: Int) {
  def numer = x
  def denum = y

  def neg = new Rational(-numer, denum)

  def +(a:Rational) =
    new Rational(numer * a.denum + a.numer * denum, denum * a.denum).simplify

  def -(a: Rational) = this.+(a.neg)

  def *(a: Rational) =
    new Rational(numer * a.numer, denum * a.denum).simplify

  def /(a: Rational) =
    new Rational(numer * a.denum, denum * a.numer).simplify

  def simplify =
    new Rational(numer/gcd(numer, denum), denum/gcd(numer, denum))

  override def toString() = numer + "/" + denum

  def equals(a: Rational) = numer == a.numer && denum == a.denum
}

def gcd(a: Int, b: Int): Int = {
  if (b == 0) abs(a) else gcd(b, a % b)
}

def abs(a: Int) = if (a > 0) a else -a
