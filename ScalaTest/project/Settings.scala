import sbt.Keys._

object Settings {
  val projectName = "ScalaTest"
  val baseVersion = "0.1.0"

  val common = Seq(
    scalaVersion := "2.12.3",
    isSnapshot := true,
    version := baseVersion
  )
}

object dependencies {

  object version {
    //  val scalatest = "3.0.1"
    //  val scalamock = "3.5.0"
    val akkaHttp = "10.0.5"
    val akkaHttpSessionCore = "0.4.0"
    //  val typesafeConfig = "1.3.1"
    //  val mockito = "2.7.22"
    //  val upickle = "0.4.4"
    //  val slick = "3.2.0"
    //  val logback = "1.2.3"
    //  val slf4j = "1.7.25"
    //  val postgresql = "9.4-1201-jdbc41"
    //  val postgresqlEmbedded = "1.23"
  }

}