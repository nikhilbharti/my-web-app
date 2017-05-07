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
          formWithErrors => {
            for {
              countries <- userDataService.countryList()
            } yield {
              BadRequest(views.html.index(formWithErrors, countries.map(x => x -> x)))
            }
          },
          userData => {
          userDataService.insertUserData(userData).map(_ => OK)
          Future.successful(Redirect(routes.ApplicationController.thankYou(userData.name)))
        }
      )
  }

  def thankYou(name :String) = Action {
    Ok(views.html.thankYou(name))
  }
}
