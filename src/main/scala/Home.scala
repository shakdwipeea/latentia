import spray.routing.HttpService

/**
 * Created by akash on 16/6/15.
 */
trait Home extends HttpService {
  val homeRoutes =
    path("") {
      get {
        complete("hello Worlds")
      }
    }
}
