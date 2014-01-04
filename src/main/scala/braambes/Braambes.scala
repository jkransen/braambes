package braambes

import akka.actor._
import framboos.actor._
import framboos.actor.CommonMessages._
import framboos.actor.SerialPortActor._
import BraambesService.Message

class Braambes extends Actor with ActorLogging {

  val inPin8 = context.actorOf(InPinActor.props(pinNumber = 8), name = "inPin8")
  inPin8 ! AddListener(self)

  val outPin0 = context.actorOf(OutPinActor.props(pinNumber = 0), name = "outPin0")

  val serialPort = context.actorOf(SerialPortActor.props(portName = "ttyAMA0"), name = "serialPort")
  serialPort ! AddListener(self)

  var currentValue = false

  def receive: Receive = {
    case Message(user, message) => {
      log.debug(s"Gabbler $user sent message: $message")
      serialPort ! SendMessage(s"$user: $message")
      currentValue = !currentValue
      outPin0 ! NewValue(currentValue)
    }
    case NewValue(value: Boolean) => {
      log.debug(s"New value from inPin8: $value")
      context.parent ! Message("braambes", s"New value: $value")
    }
    case ReceiveMessage(message: String) => {
      log.debug(s"New message from serialPort: $message")
      context.parent ! Message("braambes", message)
    }
    case other => {
      log.debug(s"other message of type: ${other.getClass()} value ${other.toString()}")
    }
  }
}