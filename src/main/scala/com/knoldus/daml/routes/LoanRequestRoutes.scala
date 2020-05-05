package com.knoldus.daml.routes

import akka.http.scaladsl.model.StatusCodes.Success
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.daml.routes.contract.fetchContracts.Result
import com.knoldus.daml.routes.contract.loanrequest.CreateLoanRequest
import com.knoldus.daml.services.LoanRequestService

class LoanRequestRoutes(loanService: LoanRequestService) extends BaseRoutes {
  val routes: Route =
    path(Segment / "v1" / "create") {
      ledgerId =>
        (post & entity(as[CreateLoanRequest])) { loanRequest =>
          onSuccess(loanService.sendLoanRequest(loanRequest, ledgerId)) {
            result =>
              complete(result)
          }
        }
    } ~
      path(Segment / "v1" / "query") {
        ledgerId => get{
          complete(loanService.fetchContractOfParty(ledgerId))
        }
      }


}




