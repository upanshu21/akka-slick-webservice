package com.knoldus.daml.routes.contract.loanrequest

import play.api.libs.json.{Json, OFormat, Reads, Writes}
import spray.json.RootJsonFormat
import scala.language.implicitConversions
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

case class ProfessionalDetails(current_company_name : String, monthly_in_hand_salary : Int, job_designation : String, work_experience_in_years : Int)

object ProfessionalDetails {

  implicit val CollateralDetailsFormats: OFormat[ProfessionalDetails] = Json.format[ProfessionalDetails]
//  implicit val CollateralDetailsFormats: Reads[ProfessionalDetails] = Json.reads[ProfessionalDetails]
//  implicit val CollateralDetails: Writes[ProfessionalDetails] = Json.writes[ProfessionalDetails]
//  implicit val professional: RootJsonFormat[ProfessionalDetails] = jsonFormat4(ProfessionalDetails.apply)

}
