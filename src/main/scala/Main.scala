import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.util.Timeout
import spray.can.Http
import akka.pattern.ask
import scala.concurrent.duration._

/**
 * Created by akash on 15/6/15.
 */
object Main extends App  {
  println("Booting up the system")

  implicit val system = ActorSystem("latentia")
  implicit val timeout = Timeout(5.seconds)

  val profileService = system.actorOf(Props[ProfileService], "profileService")
//  val homeService = system.actorOf(Props[Home], "homeService")

  IO(Http) ? Http.Bind(profileService, interface = "localhost", port = 3001)
  //IO(Http) ? Http.Bind(homeService, interface = "localhost", port = 3000)
}
