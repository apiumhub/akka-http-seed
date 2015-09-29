package monadic

import scala.concurrent.ExecutionContext

/**
 * @author kevin 
 * @since 9/28/15.
 */
case class SingleThreadExecutionContext() extends ExecutionContext {
  def execute(runnable: Runnable) = runnable.run()
  def reportFailure(cause: Throwable) = throw cause
}
