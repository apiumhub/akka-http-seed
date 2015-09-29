package com.apiumtech.akka.http.domain.echo

import java.util.Date

import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

/**
 * @author kevin 
 * @since 9/28/15.
 */
case class EchoBSONReader() extends BSONDocumentReader[Echo] {
  def read(bson: BSONDocument) = Echo (
    bson.getAs[String]("message").get,
    bson.getAs[Long]("date").map(e => new Date(e)).get
  )
}

case class EchoBSONWriter() extends BSONDocumentWriter[Echo] {
  def write(t: Echo) = {
    lazy val dto = t.toDTO
    BSONDocument (
      "message" -> dto.message,
      "date" -> dto.date.getTime
    )
  }
}

object EchoBSON {
  implicit val reader = EchoBSONReader()
  implicit val writer = EchoBSONWriter()
}
