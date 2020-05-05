package com.knoldus.daml.bootstrap

import com.knoldus.daml.routes.{LoanRequestRoutes, MetaData}
import com.typesafe.config.Config
import akka.http.scaladsl.server.Directives.{concat, ignoreTrailingSlash}
import akka.http.scaladsl.server.Route

class RouteInstantiator(conf:Config,services:ServiceInstantiator) {

  private val loanRequestRoutes = new LoanRequestRoutes(services.loanRequestService).routes

  val routes: Route =
    ignoreTrailingSlash {
      concat(
        loanRequestRoutes,
      )
  }
}
