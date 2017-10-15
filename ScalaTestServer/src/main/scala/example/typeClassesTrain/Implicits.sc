//def add(x: Int)(y: Int): Int = x + y
//
//val curr: Int => Int = add(1)
//curr(2)

def add(x: Int)(implicit y: Int): Int = x + y

implicit val a: Int = 3

add(2)