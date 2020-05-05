package com.knoldus.daml.domain

import slick.lifted.{ProvenShape, Tag}
import slick.jdbc.H2Profile.api._

case class Party(borrowerId:String,party:String)

class PartyTable(tag: Tag) extends Table[Party](tag, "PARTY") {
  def borrowerId: Rep[String] = column[String]("BORROWER_ID")

  def party: Rep[String] = column[String]("PARTY")

  override def * : ProvenShape[Party] = (borrowerId, party) <> (Party.tupled, Party.unapply)
}
