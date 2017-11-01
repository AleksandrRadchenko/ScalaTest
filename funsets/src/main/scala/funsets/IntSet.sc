import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
  //  def assertAllPos: Option[IntSet]
  def forEach(f: Int => Unit): Unit
}

object Empty extends IntSet {
  def incl(x: Int) = new NonEmpty(x, Empty, Empty)
  def contains(x: Int) = false
  def union(other: IntSet) = other
  //  override def assertAllPos: Option[IntSet] = Some(this)
  def forEach(f: Int => Unit): Unit = Unit
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
  def forEach(f: Int => Unit): Unit = {
    f(x)
    left.forEach(f)
    right.forEach(f)
  }
  //  override def assertAllPos: Option[IntSet] = {
  ////    def loop(set: IntSet): Boolean = {
  ////
  ////    }
  ////    val b = (x >= 0) && left.assertAllPos.get
  //  }
  override def toString = "{" +
    left.toString + "" + x + "" + right.toString + "}"
}

val fEmpty = Empty
val first = fEmpty incl 5
val second = first incl 3
val third = second incl 6
val fourth = third incl 4
fourth.forEach(println)

//val a: Array[NonEmpty] = Array(first)
//val b: Array[IntSet] = a

val a = Seq(1,2,3)
def f(x: Int): Future[Int] = Future.successful(x)
def f2(x: Int): List[Int] = List(x, x+1, x+2)
val b = a.map(f)
val c = a.flatMap(f2)
b(0).flatMap(f)


//Function2 (T2 => U2) <: Function1 (T1 => U1)
//F2 является наследником F1 (это значит, что везде, где мы используем F1, мы можем подставить F2)
//тогда тип U2 должен быть наследником U1 (везде, где используется результат ф-ии F2, результат
//ф-ии F1 тоже можно использовать)