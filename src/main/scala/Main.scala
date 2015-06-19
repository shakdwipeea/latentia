package in.viands

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.util.Timeout
import com.datastax.driver.core._
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

  val node = "127.0.0.1"
  val cluster = Cluster.builder().addContactPoint(node).build
  val session = cluster.connect()
  CassandraConfig.createSchema(session)

  println("Connected to cluster " + cluster.getClusterName)

  val profileService = system.actorOf(Props(new ProfileService(session)), "profileService")

  IO(Http) ? Http.Bind(profileService, interface = "localhost", port = 3000)
}

object CassandraConfig {
  val keyspace = "demo_keyspace"
  val table1 = "user_details"

  def createSchema(session: Session) = {
    val query = s"CREATE KEYSPACE IF NOT EXISTS $keyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};"
    println(query)
    session.execute(query)

    

    val query1 = s"CREATE TABLE IF NOT EXISTS $keyspace.$table1 (" +
                    "id uuid PRIMARY KEY," +
                    "name text," +
                    "phone text" +
                    ");"
    session.execute(query1) 
  }
}
