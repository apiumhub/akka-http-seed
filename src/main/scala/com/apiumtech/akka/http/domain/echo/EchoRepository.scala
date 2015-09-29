package com.apiumtech.akka.http.domain.echo

import com.apiumtech.akka.http.infr.Repository
import reactivemongo.bson.BSONDocument

import scala.concurrent.{ExecutionContext, Future}

/**
 * @author kevin 
 * @since 9/28/15.
 */
trait EchoRepository {
  def insert(echo: Echo): Future[Echo]
  def all(): Future[Seq[Echo]]
}

case class MongoEchoRepository()(implicit ec: ExecutionContext) extends EchoRepository with Repository {
  lazy val echoes = this.collection("echoes")
  import EchoBSON._

  def insert(echo: Echo) = echoes.insert(echo).map(_ => echo)
  def all() = echoes.find(BSONDocument()).cursor[Echo]().collect[Seq]()
}

object EchoRepository {
  def apply()(implicit ec: ExecutionContext): MongoEchoRepository = MongoEchoRepository()
}
