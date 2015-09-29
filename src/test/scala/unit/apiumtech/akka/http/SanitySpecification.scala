package unit.apiumtech.akka.http

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author kevin 
 * @since 9/28/15.
 */
case class SanitySpecification() extends FlatSpec with Matchers {
  "The System" should "pass always this test" in {
    true shouldBe true
  }
}
