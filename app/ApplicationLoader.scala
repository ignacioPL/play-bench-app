import controllers.HomeController
import play.api._
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import router.Routes
import com.softwaremill.macwire._
import play.api.db.slick.{DbName, SlickComponents}
import services.{EventService, SlowService}
import slick.jdbc.JdbcProfile

class MyApplicationLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    new MyComponents(context).application
  }
}

class MyComponents(context: Context) extends BuiltInComponentsFromContext(context)
  with HttpFiltersComponents
  with SlickComponents{

  lazy val db = this.slickApi.dbConfig[JdbcProfile](DbName("default"))

  lazy val slowService: SlowService = wire[SlowService]
  lazy val eventService: EventService = wire[EventService]
  lazy val homeController: HomeController = wire[HomeController]

  lazy val prefix: String = "/"
  lazy val router: Router = wire[Routes]
}

