package models

import java.time.LocalDate


case class UserDataModel(name: String, sex : String, age: String, country: String)

object UserDataModel {
  import play.api.libs.json.Json

  implicit val userDataFormat = Json.format[UserDataModel]

}



