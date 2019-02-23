package controllers

import com.softwaremill.tagging.@@
import models.CpuIntensiveThreadPool
import play.api.libs.json.Json
import play.api.mvc._
import services.{EventService, SlowService}

import scala.concurrent.{ExecutionContext, Future}

class HomeController(cc: ControllerComponents,
                     slowService: SlowService,
                     cpuThreadPool: ExecutionContext @@ CpuIntensiveThreadPool,
                     eventService: EventService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def slowEndpoint(count: Int) = Action{
    slowService.loopIterator(count)
    Ok("finish")
  }

  def slowSafeEndpoint(count: Int): Action[AnyContent] = Action.async{
    Future{slowService.loopIterator(count)}(cpuThreadPool).map { _ =>
      Ok("finish")
    }
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
