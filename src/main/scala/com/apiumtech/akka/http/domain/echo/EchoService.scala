package com.apiumtech.akka.http.domain.echo

import scala.concurrent.{Future, ExecutionContext}
import com.apiumtech.akka.http.monads.ImplicitFutureConversions._

/**
 * @author kevin 
 * @since 9/28/15.
 */
trait EchoService {
  def yell(message: String): Future[EchoDTO]
  def echoes(): Future[Seq[EchoDTO]]
}

case class DefaultEchoService(repository: EchoRepository)(implicit ec: ExecutionContext) extends EchoService {
  def yell(message: String) = for (
    echo <- Echo(message).yell() ;
    saved <- repository.insert(echo)
  ) yield saved.toDTO

  def echoes() = for (
    echoes <- repository.all()
  ) yield echoes.map(_.toDTO)
}

object EchoService {
  def apply(repository: EchoRepository)(implicit ec: ExecutionContext): DefaultEchoService = DefaultEchoService(repository)
  def apply()(implicit ec: ExecutionContext): DefaultEchoService = DefaultEchoService(EchoRepository())
}
