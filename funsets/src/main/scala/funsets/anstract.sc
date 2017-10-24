abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def incl(x: Int) = new NonEmpty(x, Empty, Empty)
  def contains(x: Int) = false
  def union(other: IntSet) = other
  override def toString = "."
}

class NonEmpty(x: Int, left: IntSet, right: IntSet) extends IntSet {
  def incl(that: Int) =
    if (that == x) this
    else if (that < x) new NonEmpty(x, left incl that, right)
    else new NonEmpty(x, left, right incl that)
  def contains(that: Int) =
    if (that == x) true
    else (left contains that) || (right contains that)
  def union(other: IntSet) = ((left union right) union other) incl x
  override def toString = "{" +
    left.toString + "" + x + "" + right.toString + "}"
}

val fEmpty = Empty
val first = fEmpty incl 5
val second = first incl 3
val third = second incl 6
val fourth = third incl 7
