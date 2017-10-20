package example.SlickConnection

import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object TestPostgresProduction extends App {
  override def main(args: Array[String]): Unit = {
    val db = Database.forConfig("rbk")

    val userDAO = new UserDAOImpl
    val validUser = User(None, "ValidUserName111", "Valid@localhost")
    try {
      Await.result(
        db.run(userDAO.create(validUser)),
        Duration.Inf)
    } finally db.close()
  }
}
