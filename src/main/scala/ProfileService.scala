package in.viands

import akka.actor.Actor
import spray.routing.{HttpService, HttpServiceActor}

import com.datastax.driver.core._
import com.datastax.driver.core.querybuilder.QueryBuilder

/**
 * Created by akash on 15/6/15.
 */
class ProfileService(session: Session) extends HttpServiceActor  {
  val profileRoutes = {
    path("login") {
      get {
      	val resultFuture = QueryData.getUsers(session)
      	val str = List(resultFuture.getUninterruptibly.all()).mkString(",")
      	complete(str)
      }
    }
  }

  def receive = runRoute(profileRoutes)
}


object QueryData {
	def getUsers(session: Session): ResultSetFuture = {
		val st:Statement = QueryBuilder.select().all().from(CassandraConfig.keyspace, CassandraConfig.table1).limit(10)
		session.executeAsync(st)
	}
}