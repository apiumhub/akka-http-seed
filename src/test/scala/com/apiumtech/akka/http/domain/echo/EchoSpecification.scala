package com.apiumtech.akka.http.domain.echo

import org.scalatest.{FlatSpec, Matchers}

/**
 * @author kevin 
 * @since 9/28/15.
 */
case class EchoSpecification() extends FlatSpec with Matchers {
  def echo(a: String = "message") = Echo(a)

  "An Echo" should "a uppercase message on yell" in {
    echo().yell().toDTO.message should be("MESSAGE")
  }

  Seq(
    "not accept empty strings" -> "",
    "not accept whitespace strings" -> " "
  ).foreach { case (test, testValue) =>
    it should test in {
      intercept[IllegalArgumentException] {
        echo(testValue)
      }
    }
  }
}
