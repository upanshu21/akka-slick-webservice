package com.knoldus.daml.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.daml.services.ValidationService

import scala.concurrent.{ExecutionContext, Future}

class MetaData(validationService:ValidationService) (implicit val ec: ExecutionContext) {

  validationService.insertPartyName
  val axisBank = validationService.getPartyIdByName("Axis")
  val hdfcBank = validationService.getPartyIdByName("HDFC")

  def lenders(party: String): Future[String] = {
    party match {
      case "Axis" => axisBank
      case "HDFC" => hdfcBank
    }
  }

}


//  val routes: Route =
//  path("getParty") {
//    (get & parameters(("borrowerId".as[String], "loanAmount".as[Double]))) { (borrowerId, loanAmount) =>
//      onSuccess(validationService.getPartyId(borrowerId)) { result =>
//        complete(result)
//      }
//    }
//  } ~
//    path("insert-party") {
//      get {
//        onSuccess(validationService.insertPartyName) { result =>
//          complete("Inserted")
//        }
//      }
//    }
