package com.apiumtech.akka.http

import com.apiumtech.akka.http.infr.Repository

/**
 * @author kevin 
 * @since 9/28/15.
 */
object Bootstrap {
  import scala.concurrent.ExecutionContext.Implicits.global

  def main(args: Array[String]) {
    Repository.connect(Seq("127.0.0.1"), "akkaHttp")
    Server(ServerRouter(), 8080, "0.0.0.0").listen()
  }
}
