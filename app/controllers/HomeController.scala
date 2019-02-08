package controllers

import play.api.libs.json.Json
import play.api.mvc._

class HomeController(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("hello" -> "world"))
  }
  
}
