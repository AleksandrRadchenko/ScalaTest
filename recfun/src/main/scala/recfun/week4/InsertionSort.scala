package recfun.week4


object InsertionSort extends App {
  type aList[T] = scala.List[T]
  val list = List(4,9,3,7,5)
  println(isort(list))

  def isort(xs: aList[Int]): aList[Int] = xs match {
    case Nil => Nil
    case y :: ys => insert(isort(ys), y)
  }

  def insert(ints: aList[Int], i: Int): aList[Int] = ints match {
    case Nil => List(i)
    case y :: ys => if (i >= y) i :: ints
    else y :: insert(ys, i)
  }

}
