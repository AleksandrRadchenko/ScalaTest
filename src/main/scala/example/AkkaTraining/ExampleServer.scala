package example.AkkaTraining

import akka.actor.ActorSystem
import akka.actor.Status.Success
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpEntity, HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.Future


object ExampleServer extends App {
  implicit val system = ActorSystem()
  import system.dispatcher
  implicit val materializer = ActorMaterializer()

  def handler(request: HttpRequest): Future[HttpResponse] =
    Future.successful(HttpResponse(entity = "Hello world!"))

  Http().bindAndHandleAsync(handler, "localhost", 8080)
    .onComplete {
      case Success(_) 
        println("Server started, port: 8080. Type Enter to terminate")
        StdIn.readLine()
      system.terminate()
    }
}
