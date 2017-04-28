package controllers

import javax.inject._

import form.GovForm._
import play.api.mvc._

@Singleton
class ApplicationController @Inject() extends Controller {

  /**
   * Home page
   */
  def index = Action {
    Ok(views.html.index(govForm))
  }

  def submit = Action { implicit request =>
    govForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.index(formWithErrors)),
      value => Ok(views.html.thankYou(value.name)))
  }
}
