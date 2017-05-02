package models

import java.time.LocalDate


case class UserDataModel(name: String, sex: String, age: String, country: String, dateCreated: String = UserDataModel.dateCreated)

object UserDataModel {

  import play.api.libs.json.Json

  val dateCreated = LocalDate.now.toString

  implicit val userDataFormat = Json.format[UserDataModel]

}



