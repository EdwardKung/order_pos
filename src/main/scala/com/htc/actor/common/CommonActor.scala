package com.htc.actor.common

import akka.actor.{Actor, ActorLogging}
import com.htc.actor.ActorConfigImplicit.{Config, ConfigSetting}


trait CommonActor[T<:Config] extends Actor with ActorLogging{
  def config()(implicit config:ConfigSetting[T]):T=config.getConfig()
}