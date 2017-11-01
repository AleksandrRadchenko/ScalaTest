package recfun.week4

import java.util.NoSuchElementException

object IntList extends App {

  def nth[T](n: Int, list: List[T]): T = {
    def loop(acc: Int, list: List[T]): T = {
      if (list.isEmpty || n < 0) throw new IndexOutOfBoundsException("n should be positive and less then list.size")
      else if (acc == n) list.head
      else loop(acc + 1, list.tail)
    }
    loop(0, list)
  }

  val newList: List[String] = new Cons("a", new Cons("b", new Cons("c", new Nil)))
  println(nth(1, newList))

  val a = List(4,1)
  println(a)
}

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def toString: String
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false

  override def toString: String = "{" + head + "," + tail + "}"
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")

  override def toString: String = "Nil"
}

object List {
  def apply[T]() = new Nil
  def apply[T](x: T) = new Cons(x, new Nil)
  def apply[T](x: T, y: T): List[T] = new Cons(x, List(y))
}
