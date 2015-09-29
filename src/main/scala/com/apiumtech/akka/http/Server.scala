package com.apiumtech.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

/**
 * @author kevin 
 * @since 9/28/15.
 */
trait Server {
  def listen(): Unit
}

case class DefaultServer(router: ServerRouter, port: Int, interface: String) extends Server {
  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  def listen() = Http().bindAndHandle(router.route, interface, port)
}

object Server {
  def apply(router: ServerRouter, port: Int, interface: String): DefaultServer = DefaultServer(router, port, interface)
}
