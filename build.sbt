name := "akka-http-seed"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

testOptions in Test += Tests.Argument("-oD")
resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= {
  val akkaVersion = "2.3.14"
  val akkaHttpVersion = "1.0"
  val scalaTestVersion = "2.2.4"
  val reactiveMongoVersion = "0.11.7"

  Seq(
    "com.typesafe.akka" %% "akka-http-experimental"               % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit-experimental"       % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor"                           % akkaVersion,
    "org.reactivemongo" %% "reactivemongo"                        % reactiveMongoVersion,

    "org.scalatest"     %% "scalatest"                            % scalaTestVersion              % "test"
  )
}
