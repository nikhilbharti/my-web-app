package connector

import javax.inject.Inject

import models.UserDataModel
import play.api.db
import play.api.libs.ws.{WSClient, WSRequest}
import play.api.mvc._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}
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

class UserDataConnector @Inject()(ws: WSClient, val reactiveMongoApi: ReactiveMongoApi)(implicit context: ExecutionContext) extends MongoController with ReactiveMongoComponents {
  val url = "https://restcountries.eu/rest/v1/region/Europe"

  def collection: JSONCollection = db.collection[JSONCollection]("userdata")

  def countryList: Future[List[String]] = {
    val request: WSRequest = ws.url(url)
    request.get().map {
      response => {
        // List("Åland Islands", "Albania", "Andorra", "Austria", "Belarus", "Belgium", "Bosnia and Herzegovina", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Faroe Islands", "Finland", "France", "Germany", "Gibraltar", "Greece", "Guernsey", "Holy See", "Hungary", "Iceland", "Republic of Ireland", "Isle of Man", "Italy", "Jersey", "Latvia", "Liechtenstein", "Lithuania", "Luxembourg", "Republic of Macedonia", "Malta", "Moldova", "Monaco", "Montenegro", "Netherlands", "Norway", "Poland", "Portugal", "Republic of Kosovo", "Romania", "Russia", "San Marino", "Serbia", "Slovakia", "Slovenia", "Spain", "Svalbard and Jan Mayen", "Sweden", "Switzerland", "Ukraine", "United Kingdom")
        (response.json \\ "name").toList.map {
          x => x.toString().replace("\"","")
        }
      }
    }
  }

  def insertUserData(userData: UserDataModel): Future[Result] = {
    val futureResult = collection.insert(userData)
    // when the insert is performed, send a OK 200 result
    futureResult.map(_ => Ok)
  }
}


