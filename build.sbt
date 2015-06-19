name := "latentia"

version := "1.0"

scalaVersion := "2.11.6"


libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "com.datastax.cassandra"  % "cassandra-driver-core" % "2.1.1"  exclude("org.xerial.snappy", "snappy-java"),
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV
  )
}
    