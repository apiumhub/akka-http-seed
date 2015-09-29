package com.apiumtech.akka.http.domain.echo

import java.util.Date

/**
 * @author kevin 
 * @since 9/28/15.
 */
trait Echo {
  def yell(): Echo

  def toDTO: EchoDTO
}

trait EchoDTO {
  def message: String
  def date: Date
}

case class DefaultEcho(message: String, date: Date) extends Echo with EchoDTO {
  require(message.trim != "")
  require(date != null)

  def yell() = copy(message = message.toUpperCase)
  def toDTO: EchoDTO = this
}

object Echo {
  def apply(message: String): DefaultEcho = DefaultEcho(message, new Date)
  def apply(message: String, date: Date): DefaultEcho = DefaultEcho(message, date)
}
