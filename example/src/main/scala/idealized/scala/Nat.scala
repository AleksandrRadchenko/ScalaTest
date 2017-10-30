package idealized.scala

object main extends App {
  val nat0 = Zero
  val nat1 = new Succ(nat0)
  val nat2 = new Succ(nat1)
  val nat3 = new Succ(nat2)
  val nat2AddNat3 = nat2 + nat3

  println("nat2 + nat3 = " + nat2AddNat3)
  println("nat2 = " + nat2)
  println("nat3 = " + nat3)
  println("nat0 = " + nat0)
  println("nat3 - nat2 = nat1 = " + (nat3 - nat2))
  println("nat3 - nat1 = nat2 = " + (nat3 - nat1))
  println("nat3 - (nat1 + nat2) = zero = " + (nat3 - (nat1 + nat2)))
//  println("exception = " + nat0.predecessor)

}

abstract class Nat {
  def isZero: scala.Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  override def isZero = true
  override def predecessor = throw new NoSuchElementException("Predecessor on zero")
  override def +(that: Nat): Nat = that
  override def -(that: Nat): Nat = if (that.isZero) this else throw new NoSuchElementException("Negative result")
  override def toString: String = "Zero"
}

class Succ(n: Nat) extends Nat {
  override def isZero = false
  override def predecessor: Nat = n
  override def +(that: Nat): Nat = n + that.successor
  override def -(that: Nat): Nat = if (that.isZero) this else n - that.predecessor

  override def toString: String = {
    def loop(x: Nat, acc: Int): Int = {
      if (x.isZero) acc else loop(x.predecessor, acc + 1)
    }
    loop(this, 0).toString
  }
}
