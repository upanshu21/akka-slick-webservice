package com.knoldus.daml.dao

import com.knoldus.daml.domain.{Party, PartyTable}
import slick.jdbc.H2Profile.api._
import slick.lifted.TableQuery
import scala.concurrent.Future

class PartyDao(database: Database) extends EntityDao[Party] {

  val parties = TableQuery[PartyTable]
  database.run(parties.schema.create)

  private def filterQuery(id: String): Query[PartyTable, Party, Seq] =
    parties.filter(_.borrowerId === id)

  override def insert(party: Party): Future[Int] = {
    database.run(parties += party)
  }

  override def update(id: String, entity: Party): Future[Int] =
    database.run(filterQuery(id).update(entity))

  override def delete(id: String): Future[Int] =
    database.run(filterQuery(id).delete)

  override def list: Future[Seq[Party]] = {
    val query=(for {
          party <- parties
        } yield (party))
      database.run(query.result)
  }

  override def findById(id: String): Future[Option[Party]] =
    database.run(
      parties
        .filter(_.borrowerId === id)
        .take(1)
        .result
        .headOption)

  override def findByName(party: String): Future[Option[Party]] =
    database.run(
      parties
        .filter(_.party === party)
        .take(1)
        .result
        .headOption)     //added to find by name

  override def count: Future[Int] =
     database.run(parties.length.result)

}
