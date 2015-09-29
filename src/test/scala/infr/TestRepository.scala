package infr

import com.apiumtech.akka.http.infr.Repository

/**
 * @author kevin 
 * @since 9/29/15.
 */
trait TestRepository {
  import monadic._

  Repository.connect(List("localhost"), "akkaHttpTest")
}
