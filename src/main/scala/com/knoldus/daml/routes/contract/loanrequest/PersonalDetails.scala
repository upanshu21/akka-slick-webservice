package com.knoldus.daml.routes.contract.loanrequest

import play.api.libs.json.{Json, OFormat, Reads, Writes}
import spray.json.RootJsonFormat
import scala.language.implicitConversions
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

case class PersonalDetails(name : String, age : String, phone_no : String, address : String, e_mail : String, pan_card_no : Int)

object PersonalDetails {

  implicit val PersonalDetailsFormat: OFormat[PersonalDetails] = Json.format[PersonalDetails]
//  implicit val PersonalDetailsFormat: Reads[PersonalDetails] = Json.reads[PersonalDetails]
//  implicit val PersonalDetails: Writes[PersonalDetails] = Json.writes[PersonalDetails]
//  implicit val verifiedz: RootJsonFormat[PersonalDetails] = jsonFormat6(PersonalDetails.apply)

}
