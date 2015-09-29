package com.apiumtech.akka.http

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.Segment
import com.apiumtech.akka.http.application.MessageApplicationService

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import scala.concurrent.ExecutionContext

/**
 * @author kevin 
 * @since 9/28/15.
 */
trait ServerRouter {
  def route: server.Route
}

case class DefaultServerRouter(messageApplicationService: MessageApplicationService)(implicit ec: ExecutionContext) extends ServerRouter {
  def route = {
    logRequestResult("akka-http-seed") {
      pathPrefix("echoes") {
        (get & path(Segment)) { value =>
          complete {
            OK -> messageApplicationService.yellMessage(value)
          }
        } ~
        get {
          complete {
            OK -> messageApplicationService.allEchoedMessages()
          }
        }
      }
    }
  }
}

object ServerRouter {
  def apply(messageApplicationService: MessageApplicationService)(implicit ec: ExecutionContext): DefaultServerRouter = {
    DefaultServerRouter(messageApplicationService)
  }

  def apply()(implicit ec: ExecutionContext): DefaultServerRouter = DefaultServerRouter(MessageApplicationService())
}
