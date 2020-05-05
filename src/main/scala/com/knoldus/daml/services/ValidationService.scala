package com.knoldus.daml.services

import com.knoldus.daml.dao.PartyDao
import com.knoldus.daml.domain.Party

import scala.concurrent.{ExecutionContext, Future}

class ValidationService(partyDao: PartyDao)(implicit val ec: ExecutionContext) {


  def insertPartyName: Future[Int] = {
    partyDao.insert(Party("ledger-party-4d573ad8-60a1-4f30-bad0-aa99993631df", "Axis"))
    partyDao.insert(Party("", "HDFC"))
    partyDao.insert(Party("","SBI"))
  }

  def getPartyId(borrowerId: String): Future[String] = {
    partyDao.findById(borrowerId).map {
      case None => "No record Found"
      case Some(party) => party.party
    }
  }

  /**
   * This function takes party name as parameter and returns the id
   * @param party is the name of the stakeholder
   * @return borrowerId of the party
   */
  def getPartyIdByName(party: String): Future[String] = {
    partyDao.findByName(party).map {
      case None => "No record Found"
      case Some(borrowerId) => borrowerId.borrowerId
    }
  }

}




//  def partyBorrower: Future[Int] = {
//    partyDao.insert(Party("2", "Axis")).map { result =>
//      println(result)
//      result
//    }
//  }
//
//  def partyAxis: Future[Int] = {
//    partyDao.insert(Party("2", "HDFC")).map { result =>
//      println(result)
//      result
//    }
//  }
//
//  def partySbi: Future[Int] = {
//    partyDao.insert(Party("2", "SBI")).map { result =>
//      println(result)
//      result
//    }
//  }

