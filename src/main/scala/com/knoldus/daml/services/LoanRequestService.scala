package com.knoldus.daml.services

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.headers.{Authorization, OAuth2BearerToken}
import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, HttpResponse}
import akka.util.ByteString
import com.knoldus.daml.routes.contract.fetchContracts.Result
import com.knoldus.daml.routes.contract.loanrequest.CreateLoanRequest
import com.typesafe.config.Config
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}
import com.knoldus.daml.HttpServerApp
import com.knoldus.daml.routes.MetaData
import play.api.libs.json.Json


class LoanRequestService(conf: Config)(implicit val system: ActorSystem) {


  def sendLoanRequest(loanRequest: CreateLoanRequest, ledgerId: String) = {

//    val a =
//      s"""{
//         |"templateId" : "${loanRequest.templateId}",
//         |"payload" : "${loanRequest.payload}"
//         |}""".stripMargin
//    println("hello")
//    println()
    val body =
        s"""{
    "templateId": "${loanRequest.templateId}",
    "payload": {
      "personalDetails" : { "name" : "${loanRequest.payload.personalDetails.name}","age" : "${loanRequest.payload.personalDetails.age}" , "phone_no" : "${loanRequest.payload.personalDetails.phone_no}", "address" : "${loanRequest.payload.personalDetails.address}", "e_mail" : "${loanRequest.payload.personalDetails.e_mail}", "pan_card_no" : "${loanRequest.payload.personalDetails.pan_card_no}"},
      "loanRequirementDetails" : { "loan_purpose" : "${loanRequest.payload.loanRequirementDetails.loan_purpose}", "amount_requirement" : "${loanRequest.payload.loanRequirementDetails.amount_requirement}", "cibil_score" : "${loanRequest.payload.loanRequirementDetails.cibil_score}"},
      "professionalDetails" : { "current_company_name" : "${loanRequest.payload.professionalDetails.current_company_name}", "monthly_in_hand_salary" : "${loanRequest.payload.professionalDetails.monthly_in_hand_salary}", "job_designation" : "${loanRequest.payload.professionalDetails.job_designation}", "work_experience_in_years" : "${loanRequest.payload.professionalDetails.work_experience_in_years}"},
      "validationAuthority" : "${loanRequest.payload.validationAuthority}",
      "borrower" : "${loanRequest.payload.borrower}",
      "listOfLenders" : ["${loanRequest.payload.listOfLenders.mkString}"] }
    }"""
    val data = ByteString(body)
    Http().singleRequest(HttpRequest(
    method = HttpMethods.POST,
    uri = s"${conf.getConfig("dabl").getString("url")}/$ledgerId/v1/create",
    headers = List(Authorization(OAuth2BearerToken(conf.getConfig("jwt").getString("token")))),
    entity = HttpEntity(data)))
  }

  def fetchContractOfParty(ledgerId: String) : Future[HttpResponse] = {

   val httpResponse =  Http().singleRequest(HttpRequest(
      method = HttpMethods.GET,
      uri = s"${conf.getConfig("dabl").getString("url")}/$ledgerId/v1/query",
      headers = List(Authorization(OAuth2BearerToken(conf.getConfig("jwt").getString("token"))))
    ))
    httpResponse
//    val responseEntity = httpResponse.flatMap(response => response.entity.toStrict(15.seconds))
//    println(responseEntity)
//    httpResponse
  }
}
