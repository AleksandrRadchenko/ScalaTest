package idealized.scala

abstract class Boolean {
  def ifThenElse(t: => Boolean, e: => Boolean): Boolean

  def &&(other: => Boolean) = ifThenElse(other, _false)
  def ||(other: => Boolean) = ifThenElse(_true, other)
  def unary_! = ifThenElse(_false, _true)
  def == (other: Boolean) = ifThenElse(other, other.unary_!)
  def != (other: Boolean) = ifThenElse(other.unary_!, other)
  def < (other: Boolean) = ifThenElse(_false, other)
  def > (other: Boolean) = ifThenElse(other.unary_!, _false)
}

object _false extends Boolean {
  override def ifThenElse(t: => Boolean, e: => Boolean) = e
}
object _true extends Boolean {
  override def ifThenElse(t: => Boolean, e: => Boolean) = t
}
