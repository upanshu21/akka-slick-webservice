package com.knoldus.daml.routes.contract.loanrequest

import play.api.libs.json.{Json, OFormat, Reads, Writes}
import spray.json.RootJsonFormat

import scala.language.implicitConversions
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.knoldus.daml.routes.MetaData
import spray.json.DefaultJsonProtocol._
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.global
import com.knoldus.daml.services.ValidationService


case class InitialLoanRequest(
    personalDetails : PersonalDetails ,
    loanRequirementDetails : LoanRequirementDetails,
    professionalDetails : ProfessionalDetails,
    validationAuthority : String,
    borrower : String,
    listOfLenders :List[String]
                               )

object InitialLoanRequest {

  implicit val InitialLoanRequestFormats: OFormat[InitialLoanRequest] = Json.format[InitialLoanRequest]
//   implicit val InitialLoanRequestFormats: Reads[InitialLoanRequest] = Json.reads[InitialLoanRequest]
//   implicit val InitialLoanRequest: Writes[InitialLoanRequest] = Json.writes[InitialLoanRequest]
//  implicit val verifiedq: RootJsonFormat[InitialLoanRequest] = jsonFormat6(InitialLoanRequest.apply)

}