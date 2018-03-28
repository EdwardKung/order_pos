package com.htc.actor

object ActorConfigImplicit {
  trait Config
  trait ConfigSetting[T]{
    def getConfig():T
  }
  case class OrderReceiverConfig(taskName:String) extends Config
  case class MakerConfig(taskName:String) extends Config
  case class DefaultConfig(message:String="Default Config.") extends Config

  implicit object MakerConfigImplicit extends ConfigSetting[MakerConfig]{
    override def getConfig(): MakerConfig = MakerConfig("Maker")
  }
  implicit object OrderReceiverConfigImplicit extends ConfigSetting[OrderReceiverConfig]{
    override def getConfig(): OrderReceiverConfig = OrderReceiverConfig("KFC-Order")
  }
}
