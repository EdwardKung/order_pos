package com.htc.actor

import akka.actor.Props
import com.htc.actor.ActorConfigImplicit.MakerConfig
import com.htc.actor.OrderReceiver.Yes
import com.htc.actor.common.CommonActor

object FriesMaker extends ActorProps{

  override def props() = Props(new FriesMaker)
}
class FriesMaker extends CommonActor[MakerConfig]{
  override def receive = {
    case fries:Fries=>
      println("I am doing fries.")
      Thread.sleep(fries.spendSeconds*1000)
      println("I have done fries.")
      sender ! Yes(fries)
  }
}
