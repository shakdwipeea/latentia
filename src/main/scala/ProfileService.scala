import akka.actor.Actor
import spray.routing.{HttpService, HttpServiceActor}

/**
 * Created by akash on 15/6/15.
 */
class ProfileService extends HttpServiceActor with ProfileRoutes with Home{
  def receive = runRoute(profileRoutes ~ homeRoutes)
}

trait ProfileRoutes extends HttpService {
  val profileRoutes = {
    path("login") {
      get {
        complete("hello")
      }
    }
  }
}
