package com.knoldus.daml.routes.contract.loanrequest

import play.api.libs.json.{Json, OFormat, Reads, Writes}
import spray.json.RootJsonFormat
import scala.language.implicitConversions
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

case class LoanRequirementDetails(loan_purpose : String, amount_requirement : String, cibil_score : Int)

object LoanRequirementDetails {

  implicit val LoanRequirementDetailsFormats: OFormat[LoanRequirementDetails] = Json.format[LoanRequirementDetails]
//  implicit val LoanRequirementDetailsFormats: Reads[LoanRequirementDetails] = Json.reads[LoanRequirementDetails]
//  implicit val LoanRequirementDetails: Writes[LoanRequirementDetails] = Json.writes[LoanRequirementDetails]
//implicit val verifieda: RootJsonFormat[LoanRequirementDetails] = jsonFormat3(LoanRequirementDetails.apply)
}
