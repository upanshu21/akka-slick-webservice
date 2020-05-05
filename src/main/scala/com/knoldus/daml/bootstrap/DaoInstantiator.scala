package com.knoldus.daml.bootstrap

import com.knoldus.daml.dao.PartyDao
import slick.jdbc.H2Profile.api._

class DaoInstantiator(database:Database) {
val partyDao = new PartyDao(database)
}
