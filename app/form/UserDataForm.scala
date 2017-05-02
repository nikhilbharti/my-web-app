package form

import models.UserDataModel
import play.api.data.Form
import play.api.data.Forms._

object UserDataForm {
  val userDataForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "gender" -> nonEmptyText,
      "age" -> nonEmptyText,
      "country" -> nonEmptyText,
      "dateCreated" -> ignored[String](UserDataModel.dateCreated)
    )(UserDataModel.apply)(UserDataModel.unapply)
  )
}
