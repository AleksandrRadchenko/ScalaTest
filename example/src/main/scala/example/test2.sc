val xs = List(1,2)
val ys = List(4,5,6,7)
val b: Boolean = false
xs.map(_ * 2)
xs.flatMap(_ => "C")

//for comprehension: destructuring bind
for ((x,y) <- xs zip ys) yield x*y
//same as
val tuples = xs zip ys
tuples map { case (x:Int,y:Int) => x*y }

//for comprehension: cross product
for (x <- xs; y <- ys) yield x*y
//same as
xs flatMap {x => ys map {y => x*y}}

for (x <- xs; y <- ys) {
  println("%d/%d = %.1f".format(x, y, x/y.toFloat))
}


def methodA(s: String) = ???
def methodB(f: () => String) = ???
def f = "foo"
def f2() = "foo"
methodA(f)
methodB(f) // error!
methodA(f()) // error!
methodB(f()) // error!

methodA(f2())
methodB(f2()) // error!
methodA(f2)
methodB(f2)