package example

object ImplicitIntegralConstsNPE extends App {

  trait IntegralConsts[N] {
    val tc: Integral[N]
    val two = tc.plus(tc.one, tc.one)
    val four = tc.plus(two, two)
  }

  object IntegralConsts {
    implicit def consts[N: Integral] = new IntegralConsts[N] {
      override val tc = implicitly[Integral[N]]
    }
  }

  def binRangeSearch[N: IntegralConsts](/* irrelevant args */) = {
    val consts = implicitly[IntegralConsts[N]]
    val math = consts.tc

    val halfRange = consts.two
  }

  binRangeSearch[Int]()
}


