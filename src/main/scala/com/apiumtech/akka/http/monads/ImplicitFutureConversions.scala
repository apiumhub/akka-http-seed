package com.apiumtech.akka.http.monads

import scala.concurrent.{ExecutionContext, Future}

/**
 * @author kevin 
 * @since 9/28/15.
 */

object ImplicitFutureConversions {
  import scala.language.implicitConversions
  import scala.language.reflectiveCalls

  type HasDTO[T] = { def toDTO: T }

  implicit def T2FutureT[T](a: T)(implicit ec: ExecutionContext): Future[T] = Future { a }
  implicit def T2FutureDTO[T](a: Future[HasDTO[T]])(implicit ec: ExecutionContext): Future[T] = a.map(_.toDTO)
}
