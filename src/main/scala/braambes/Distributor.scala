package braambes

import akka.actor._
import Gabbler._
import BraambesService._
import scala.concurrent.duration.FiniteDuration

object Distributor {
  case class CompleteToGabbler(name: String, completer: Completer)

  def props(timeout: FiniteDuration): Props = Props(new Distributor(timeout))
}

class Distributor(timeout: FiniteDuration) extends Actor {

  import Distributor._

  val braambes = context.actorOf(Props[Braambes], "braambes")

  def receive: Receive = {
    case CompleteToGabbler(username: String, completer: Completer) => {
      gabblerFor(username) ! completer
    }
    case m @ Message(user: String, message: String) => {
      context.children foreach (_ ! m)
    }
  }

  def gabblerFor(username: String): ActorRef =
    context.child(username) getOrElse context.actorOf(Gabbler.props(timeout), username)

}