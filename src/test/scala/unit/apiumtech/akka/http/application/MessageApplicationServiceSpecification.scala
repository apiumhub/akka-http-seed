package unit.apiumtech.akka.http.application

import com.apiumtech.akka.http.application.MessageApplicationService
import com.apiumtech.akka.http.application.MessageApplicationService.{EchoedMessageList, YelledMessage}
import com.apiumtech.akka.http.domain.echo.{EchoDTO, Echo, EchoService}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Future

/**
 * @author kevin 
 * @since 9/28/15.
 */
case class MessageApplicationServiceSpecification() extends FlatSpec with Matchers {
  import monadic._

  def service() = MessageApplicationService(new EchoService {
    def yell(message: String) = Future { Echo(message).toDTO }
    def echoes() = Future { Seq() }
  })

  "A MessageApplicationService" should "return a yelled message in a DPO" in {
    monadic {
      service().yellMessage("hi!")
    } shouldBe YelledMessage("hi!")
  }

  it should "return a list of all yelled messages" in {
    monadic {
      service().allEchoedMessages()
    } shouldBe EchoedMessageList(Seq())
  }
}
