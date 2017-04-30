package controllers

import javax.inject._

import form.UserDataForm._
import models.UserDataModel
import play.api.{Logger, db}
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

// Reactive Mongo imports
import reactivemongo.api.Cursor

import play.modules.reactivemongo.{ // ReactiveMongo Play2 plugin
MongoController,
ReactiveMongoApi,
ReactiveMongoComponents
}
// BSON-JSON conversions/collection
import reactivemongo.play.json._
import play.modules.reactivemongo.json.collection._


@Singleton
class ApplicationController @Inject() (ws: WSClient,val reactiveMongoApi: ReactiveMongoApi) (implicit context: ExecutionContext) extends Controller
  with MongoController with ReactiveMongoComponents{
  val url = "https://restcountries.eu/rest/v1/region/Europe"

  def collection: JSONCollection = db.collection[JSONCollection]("userdata")

  def index = Action.async {
    //getListOfCountry
    Future.successful(Ok(views.html.index(userDataForm)))
  }

  def getListOfCountry = {
    val request: WSRequest = ws.url(url)
    val complexRequest: WSRequest =
      request.withHeaders("Accept" -> "application/json")
        .withRequestTimeout(10000.millis)
     request.get().map {
     response => println(response)
   }
  }

def submit = Action.async  {
    implicit request =>
      userDataForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.index(formWithErrors))),
        userData => {
          collection.insert(userData).map(_ => Ok)
         Future.successful(Ok(views.html.thankYou(userData.name)))
        }
      )
  }



}
