package model

/**
  * Created by NEBHA on 27/04/2017.
  */
import play.api.libs.json._

case class GovServiceModel(name: String,sex : String,age: String , country: String)
object GovServiceModel {
  implicit val formats: Format[GovService] = Json.format[GovService]
}


