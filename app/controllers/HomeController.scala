package controllers

import play.api.libs.json.Json
import play.api.mvc._
import services.{EventService, SlowService}

import scala.concurrent.ExecutionContext

class HomeController(cc: ControllerComponents,
                     slowService: SlowService,
                     eventService: EventService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def slowEndpoint(count: Int) = Action{
    slowService.loopIterator(count)
    Ok("finish")
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("hello" -> "world"))
  }

  def all(): Action[AnyContent] = Action.async{
    eventService.allEvents.map{ events =>
      events.foreach(println)
      Ok("termine")
    }
  }

  def one(userId: String): Action[AnyContent] = Action.async{
    eventService.selectOneUser(userId).map{ events =>
      Ok(Json.obj("size" -> events.length))
    }
  }

}
