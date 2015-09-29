import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
 * @author kevin 
 * @since 9/28/15.
 */
package object monadic {
  import scala.language.postfixOps
  import scala.language.implicitConversions

  implicit val executionContext = SingleThreadExecutionContext()
  implicit def futureToT[T](a: Future[T]): T = Await.result(a, 2 seconds)

  def monadic[T](a: => Future[T]): T = futureToT(a)
}
