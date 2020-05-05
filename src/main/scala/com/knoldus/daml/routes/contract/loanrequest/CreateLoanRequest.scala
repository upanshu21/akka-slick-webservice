package com.knoldus.daml.routes.contract.loanrequest

import play.api.libs.json.{Json, OFormat, Reads, Writes}
import spray.json.RootJsonFormat
import scala.language.implicitConversions
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

case class CreateLoanRequest(templateId: String,payload: InitialLoanRequest)

object CreateLoanRequest {

  implicit val CreateLoanRequestReads: OFormat[CreateLoanRequest] = Json.format[CreateLoanRequest]
//  implicit val CreateLoanRequest: Reads[CreateLoanRequest] = Json.reads[CreateLoanRequest]
//  implicit val CreateLoanRequestReads: Writes[CreateLoanRequest] = Json.writes[CreateLoanRequest]
//  implicit val verifiedk: RootJsonFormat[CreateLoanRequest] = jsonFormat2(CreateLoanRequest.apply)


}