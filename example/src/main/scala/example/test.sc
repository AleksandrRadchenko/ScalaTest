import example.Lists.sum

val xs = List(3, 1, 5)
val emptyList = List()

def max(xs: List[Int]): Int = {
  max0(xs)
  //  if (xs.isEmpty) throw new NoSuchElementException
  def max0(xs: List[Int]): Int = {
    if (xs.tail.isEmpty)
      xs.head
    else if (xs.head > max0(xs.tail))
      xs.head
    else
      max0(xs.tail)
  }

}

//max(emptyList)
max(xs)

