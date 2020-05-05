package com.knoldus.daml.bootstrap

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.knoldus.daml.services.{LoanRequestService, ValidationService}
import com.typesafe.config.Config

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

class ServiceInstantiator(conf: Config, daoInstantiator: DaoInstantiator)(implicit system: ActorSystem) {
  implicit val ec: ExecutionContext = system.dispatcher

  val loanRequestService = new LoanRequestService(conf)
  val validationService = new ValidationService(daoInstantiator.partyDao)
}
