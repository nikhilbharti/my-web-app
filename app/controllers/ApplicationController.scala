package controllers

import javax.inject._

import form.UserDataForm._
import play.api.mvc._
import service.UserDataService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ApplicationController @Inject()(userDataService: UserDataService)(implicit ec: ExecutionContext) extends Controller {


  def index = Action.async {
    for {
      countries <- userDataService.countryList()
    } yield {
      Ok(views.html.index(userDataForm, countries.map(x => x -> x)))
    }
  }

  def submit = Action.async {
    implicit request =>
      userDataForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.index(formWithErrors, Seq("" -> "")))),
        userData => {
          userDataService.insertUserData(userData).map(_ => OK)
          Future.successful(Ok(views.html.thankYou(userData.name)))
        }
      )
  }
}
