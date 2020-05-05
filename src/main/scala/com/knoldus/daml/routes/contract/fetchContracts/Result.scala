package com.knoldus.daml.routes.contract.fetchContracts

import play.api.libs.json.{Json, OFormat, Reads}

case class Result(response : List[Contract], status : Int)

object Result {

  implicit val result : Reads[Result] = Json.reads[Result]

}

