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
}

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

