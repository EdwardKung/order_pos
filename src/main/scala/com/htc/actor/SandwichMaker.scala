package com.htc.actor

import akka.actor.Props
import com.htc.actor.ActorConfigImplicit.MakerConfig
import com.htc.actor.OrderReceiver.Yes
import com.htc.actor.common.CommonActor
object SandwichMaker extends ActorProps{
  override def props() = Props(new SandwichMaker)
}
class SandwichMaker extends CommonActor[MakerConfig]{
  override def receive = {
    case sandwich:Sandwich=>
      println("I am doing sandwich.")
      Thread.sleep(sandwich.spendSeconds*1000)
      println("I have done sandwich.")
      sender ! Yes(sandwich)
  }
}
