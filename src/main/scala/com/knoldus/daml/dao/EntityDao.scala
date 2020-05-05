package com.knoldus.daml.dao

import scala.concurrent.Future

trait EntityDao[T] {
  def insert(entity: T): Future[Int]
  def update(id: String, entity: T): Future[Int]
  def delete(id: String): Future[Int]
  def list: Future[Seq[T]]
  def findById(id: String): Future[Option[T]]
  def count: Future[Int]
  def findByName(party: String) : Future[Option[T]] //added
}
