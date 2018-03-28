package com.htc

import Main.getClass
import akka.actor.ActorSystem
import com.htc.actor._
import com.typesafe.config.ConfigFactory

object Main  extends App{
  val system= ActorSystem("order-post",ConfigFactory.load("application.conf"),getClass.getClassLoader)

  val receiver=system.actorOf(OrderReceiver.props(),"order-receiver")
  println(receiver.path.toString)
//  receiver ! Order(content = MealSet(Sandwich(20),Fries(10),Coke(3)))
//  receiver! Order(content=MealSingle(Coke(4)))
//  receiver ! Order(content = MealSet(Sandwich(20),Fries(10),Coke(3)))
//  receiver ! Order(content=MealSingle(Coke(4)))
//  receiver ! Order(content=MealSingle(Sandwich(25)))
//  receiver ! Order(content = MealSet(Sandwich(20),Fries(10),Coke(3)))
}
