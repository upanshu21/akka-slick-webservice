package com.knoldus.daml.routes.contract.fetchContracts

import com.knoldus.daml.routes.contract.loanrequest.InitialLoanRequest
import play.api.libs.json.{Json, OFormat, Reads}

case class Contract(agreement : String, contractId : String, observers : List[String], payload: InitialLoanRequest )

object Contract {

  implicit val contract : Reads[Contract] = Json.reads[Contract]

}
