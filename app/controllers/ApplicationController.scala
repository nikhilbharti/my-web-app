package controllers

import javax.inject._

import play.api._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import form.GovForm
import model.GovServiceModel

@Singleton
class ApplicationController @Inject() extends Controller {

  /**
   * Home page
   */
  def index = Action {
    Ok(views.html.index(govForm))
  }

  def Apply = Action(parse.form(govForm)) { implicit request =>
    val govServiceModel = request.body.as[GovServiceModel]
    Ok(views.html.thankYou(govServiceModel.name))
  }
}
