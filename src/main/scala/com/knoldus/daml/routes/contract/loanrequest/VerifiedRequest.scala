package com.knoldus.daml.routes.contract.loanrequest

import play.api.libs.json.{Json, OFormat, Reads, Writes}
import spray.json.RootJsonFormat
import scala.language.implicitConversions
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._


case class VerifiedRequest(
                            validation_id : Int,
                            personalDetails: PersonalDetails,
                            loanRequirementDetails: LoanRequirementDetails,
                            professionalDetails : ProfessionalDetails,
                            borrower: String,
                            listOfLenders: List[String],
                            validationAuthority: String
                          )

object VerifiedRequest {

  implicit val VerifiedRequestFormat : OFormat[VerifiedRequest] = Json.format[VerifiedRequest]
//   implicit val VerifiedRequestFormat : Reads[VerifiedRequest] = Json.reads[VerifiedRequest]
//   implicit val VerifiedRequest : Writes[VerifiedRequest] = Json.writes[VerifiedRequest]
//    implicit val verified: RootJsonFormat[VerifiedRequest] = jsonFormat7(VerifiedRequest.apply)


}
