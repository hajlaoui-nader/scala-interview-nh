package akka

import akka.actor._

/**
  * Question about Akka framework http://akka.io
  *
  * When receiving a message that says "Hello", BasicActor must print "Hello there."
  * It must print "What?" when receiving any other message
  */
class BasicActor extends Actor {
  override def receive: Receive = {
    case "hello" => println("Hello there")
    case _       => println("What?")
  }
}
object BasicActor {
  def props(): Props = Props(new BasicActor)
}

object FireActor extends App {

  /**
    * Create an instance of BasicActor
    *
    * Make it print "Hello there." and "What?"
    */
  def fireActor(): Unit = {
    val system      = ActorSystem("Actor_System")
    val basic_actor = system.actorOf(BasicActor.props())

    basic_actor ! "hello"
    basic_actor ! "say what"
  }

  fireActor()
}
