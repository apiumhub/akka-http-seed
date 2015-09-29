package com.apiumtech.akka.http.domain.echo

import infr.TestRepository
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author kevin 
 * @since 9/29/15.
 */
case class EchoRepositorySpecification() extends FlatSpec with Matchers with TestRepository {
  import monadic._

  def repository() = EchoRepository()

  "A EchoRepository" should "find an already inserted element" in {
    val echo = Echo("hi")
    monadic {
      for (
        saved <- repository().insert(echo) ;
        all <- repository().all()
      ) yield all
    }.head.toDTO.message shouldBe "hi"
  }
}
