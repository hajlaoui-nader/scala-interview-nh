import akka.actor._
import akka.pattern._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util._

/**
  * Do you see anything that could lead to potential problems ?
  * What would you do to fix it ?
  * Do not mind about the not implemented code
  */
class WhatsWrong3 extends Actor {
  import WhatsWrong3._

  /**
    * avoid using var to define actors state, prefer context.become(fn(mutable state): Receive)
    */
  var internalState = "internal state"

  /**
    * When using future callbacks, inside actors you need to carefully avoid closing over the containing
    * actor’s reference, i.e. do not call methods or access mutable state on the enclosing actor from within
    * the callback.
    * This breaks the actor encapsulation and may introduce synchronization bugs and race conditions because
    * the callback will be scheduled concurrently to the enclosing actor.
    */
  def receive: Receive = {
    case "a query" => {
      val requestF: Future[String] = queryAsyncServer()
      requestF.onComplete {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
    }
  }

  /**
    * prefered implementation
    */
  def receiveRefactored: Receive = handle("")

  def handle(internalState: String): Receive = {
    case "a query" =>
      queryAsyncServer()
        .map(Update)
        .pipeTo(self)
    case update: Update => context.become(handle(handleResponseRefactored(update.r, internalState)))
  }

  def handleResponse(r: String) = ??? // mutate internal state

  def handleResponseRefactored(r: String, state: String) = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???

}
object WhatsWrong3 {
  case class Update(r: String) extends AnyVal
}
