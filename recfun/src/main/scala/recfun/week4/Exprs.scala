package recfun.week4

object Exprs extends App {

  val num3 = Number(3)
  val sum1and2 = Sum(Number(1), Number(2))
  val varX = Var("x")
  val varY = Var("y")
  val sum3andX = Sum(num3, varX)
  val prod3andX = Prod(num3, varX)
  val prodSumAndY = Prod(sum3andX, varY)
  val sumProdAndY = Sum(prod3andX, varY)

  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
//    case Var(x) => x
    case Prod(e1, e2) => eval(e1) * eval(e2)
  }

  def show(e: Expr): String = e match {
    case Number(n) => n.toString
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Var(x) => x
    case Prod(e1, e2) => (e1, e2) match {
      case (Sum(x1, x2), Sum(y1, y2)) => "(" + show (e1)  + ")" + " * " + show(e2)
      case (Sum(_, _), _) => "(" + show (e1)  + ")" + " * " + show(e2)
      case (_, Sum(_, _)) => show (e1) + " * " + "(" + show(e2) + ")"
      case (_, _) => show (e1) + " * " + show(e2)
    }
  }

  println(show(num3))
  println(show(sum1and2))

  println(show(prodSumAndY))
  println(show(sumProdAndY))

}


trait Expr {
}

case class Number(n: Int) extends Expr

case class Sum(e1: Expr, e2: Expr) extends Expr

case class Var(n: String) extends Expr

case class Prod(e1: Expr, e2: Expr) extends Expr
