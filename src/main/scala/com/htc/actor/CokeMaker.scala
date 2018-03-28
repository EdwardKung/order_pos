package com.htc.actor

import akka.actor.Props
import com.htc.actor.ActorConfigImplicit._
import com.htc.actor.OrderReceiver.Yes
import com.htc.actor.common.CommonActor
object CokeMaker extends ActorProps{

  override def props() = Props(new CokeMaker)
}

class CokeMaker extends CommonActor[MakerConfig]{

  override def receive = {
    case coke:Coke=>
      println("I am doing coke.")
      Thread.sleep(coke.spendSeconds*1000)
      println("I have done coke.")
      sender ! Yes(coke)
  }
}
