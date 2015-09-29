package unit.apiumtech.akka.http.domain.echo

import com.apiumtech.akka.http.domain.echo.{Echo, EchoRepository, EchoService}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Future

/**
 * @author kevin 
 * @since 9/28/15.
 */
case class EchoServiceSpecification() extends FlatSpec with Matchers {
  import monadic._

  def service() = EchoService(new EchoRepository {
    def insert(echo: Echo) = Future { echo }
    def all() = Future { Seq() }
  })

  "An EchoService" should "return a DTO with the yelled message" in {
    monadic {
      service().yell("message")
    }.message shouldBe "MESSAGE"
  }

  it should "return a DTO with all the echoed messages" in {
    monadic {
      service().echoes()
    } shouldBe Seq()
  }
}
