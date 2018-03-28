package com.htc.actor

import java.util.UUID

import akka.actor.Props
import akka.pattern.{ask, pipe}
import akka.util.Timeout
import com.htc.actor.ActorConfigImplicit.{ConfigSetting, OrderReceiverConfig}
import com.htc.actor.OrderReceiver.{ Yes}
import com.htc.actor.common.CommonActor

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
object OrderReceiver extends ActorProps {

  override def props() = Props(new OrderReceiver)

  sealed trait Response
  case class Yes(item: Item) extends Response
}

class OrderReceiver extends CommonActor[OrderReceiverConfig] {
  implicit val timeout: Timeout = Timeout(50 seconds)
  val remoteClient=context.actorSelection("akka.tcp://order-client@127.0.0.1:2553/user/client-actor")
  override def preStart(): Unit = {
    println(s"${config().taskName} will start.")
    Vector(SandwichMaker, FriesMaker, CokeMaker).foreach(actorRef =>
      context.actorOf(actorRef.props()))
    super.preStart()
  }
  override def receive = {
    case Order(id: UUID, mealSet: MealSet) =>
      println(s"I got a set order, $id")
      (for {
        result <- Future.sequence(mealSet.productIterator.map(item =>
          (context.actorSelection("*") ? item).mapTo[Yes]))
      } yield Finish(id, result.map(_.item).toVector))
        .pipeTo(self)
    case Order(id: UUID, mealSingle: MealSingle) =>
      println(s"I got a single order, $id")
      (for {
        result <- (context.actorSelection("*") ? mealSingle.item).mapTo[Yes]
      } yield Finish(id, Vector(result.item)))
        .pipeTo(self)
    case finish: Finish =>
      remoteClient ! finish
      println(finish)

  }

}
