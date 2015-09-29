package com.apiumtech.akka.http.infr

import reactivemongo.api.{DefaultDB, MongoDriver}
import reactivemongo.api.collections.bson.BSONCollection

import scala.concurrent.ExecutionContext
import scala.util.Try

/**
 * @author kevin 
 * @since 9/28/15.
 */
trait Repository {
  protected final def collection(name: String): BSONCollection = {
    require(Repository.database.nonEmpty, "The MongoDB connection is closed, probably it hasn't been initialized with Repository.connect")
    Repository.database.get(name)
  }
}

object Repository {
  private[infr] var database: Option[DefaultDB] = None

  def connect(hosts: Seq[String], database: String)(implicit ec: ExecutionContext): Try[Unit] = Try {
    if (database.nonEmpty) {
      val driver = new MongoDriver
      val connection = driver.connection(hosts)

      Repository.database = Some(connection(database))
    }
  }
}
