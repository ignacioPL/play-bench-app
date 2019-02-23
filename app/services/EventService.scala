package services

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future

case class UserEvent(userId: String, datetime: Long, countryCode: String, data: String)

class EventService(dbConfig: DatabaseConfig[JdbcProfile]) {

  private val db = dbConfig.db
  import dbConfig.profile.api._

  private val events = TableQuery[Events]

  private def findOneUser(userId: Rep[String]) = {
    events.filter( _.userId === userId )
  }

  private val findOneQuery = Compiled(findOneUser _)

  private val allQuery = Compiled( events.take(100) )//.map(_.userId) )

  def allEvents: Future[Seq[UserEvent]] = {
    db.run(allQuery.result)
  }

  def selectOneUser(userId: String):  Future[Seq[UserEvent]] = {
    db.run( findOneQuery(userId).result )
  }

  private class Events(tag: Tag) extends Table[UserEvent](tag, Some("bench"),"events"){
    def userId: Rep[String] = column[String]("user_id",O.PrimaryKey)
    def datetime: Rep[Long] =column[Long]("datetime",O.PrimaryKey)
    def countryCode: Rep[String] = column[String]("country_code")
    def data: Rep[String] = column[String]("data")
    def * : ProvenShape[UserEvent] = (userId,datetime,countryCode,data) <> (UserEvent.tupled, UserEvent.unapply)
  }

}
