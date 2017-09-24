import upickle.default._

write(1)

write(Seq(1, 2, 3))

read[Seq[Int]]("[1, 2, 3]")

write((1, "omg", true))

type Tup = (Int, String, Boolean)

read[Tup]("[1,\"omg\",true]")