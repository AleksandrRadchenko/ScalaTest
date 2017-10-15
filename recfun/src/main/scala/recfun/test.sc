def factorial(n: Long, accum: Long): Long = {
  if (n == 1) accum else factorial(n - 1, accum * n)
}

def factorial2(n: Long, accum: Long): Long = {
  def loop(i: Int, accum: Long): Long = {
    if (accum < 0) println(i)
    if (i == n) accum
    else loop(i + 1, accum * i)
  }
  loop(1, accum)
}

factorial2(22, 1)
