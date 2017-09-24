case class Album(
                  artist   : String,
                  title    : String,
                  year     : Int = 1990,
                  id       : Long = 0L
                )
val album1 = Album("Artist", "Gogogo title", 1)
val a = Album.tupled
println(a)

