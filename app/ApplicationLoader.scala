import controllers.HomeController
import play.api._
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import router.Routes
import com.softwaremill.macwire._

class MyApplicationLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    new MyComponents(context).application
  }
}

class MyComponents(context: Context) extends BuiltInComponentsFromContext(context) with HttpFiltersComponents {

  lazy val homeController: HomeController = wire[HomeController]

  lazy val router: Router = wire[Routes]
}

