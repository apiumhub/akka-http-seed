package com.apiumtech.akka.http.application

import java.util.Date

import com.apiumtech.akka.http.application.MessageApplicationService.{EchoedMessageList, EchoedMessage, YelledMessage}
import com.apiumtech.akka.http.domain.echo.EchoService
import com.apiumtech.akka.http.infr.DefaultJsonConversions
import spray.json

import scala.concurrent.{ExecutionContext, Future}

/**
 * @author kevin 
 * @since 9/28/15.
 */
trait MessageApplicationService {
  def yellMessage(message: String): Future[YelledMessage]
  def allEchoedMessages(): Future[EchoedMessageList]
}

case class DefaultMessageApplicationService(echoService: EchoService)(implicit ec: ExecutionContext) extends MessageApplicationService {
  def yellMessage(message: String) = for (
    echo <- echoService.yell(message)
  ) yield YelledMessage(echo.message)

  def allEchoedMessages() = for (
    echoes <- echoService.echoes()
  ) yield EchoedMessageList(echoes.map(e => EchoedMessage(e.message, e.date)))
}

object MessageApplicationService extends json.DefaultJsonProtocol with DefaultJsonConversions {
  case class YelledMessage(message: String)
  implicit val yelledFormat = jsonFormat1(YelledMessage)

  case class EchoedMessage(message: String, date: Date)
  implicit val echoedFormat = jsonFormat2(EchoedMessage)

  case class EchoedMessageList(messages: Seq[EchoedMessage])
  implicit val messageListFormat = jsonFormat1(EchoedMessageList)

  def apply(echoService: EchoService)(implicit ec: ExecutionContext): DefaultMessageApplicationService = {
    DefaultMessageApplicationService(echoService)
  }

  def apply()(implicit ec: ExecutionContext): DefaultMessageApplicationService = {
    DefaultMessageApplicationService(EchoService())
  }
}
