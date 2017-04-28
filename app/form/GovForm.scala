package form

import model.{GovServiceModel}
import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by NEBHA on 27/04/2017.
  */
object GovForm {
  val govForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "gender" -> nonEmptyText,
      "age" -> nonEmptyText,
      "country" -> nonEmptyText
    )(GovServiceModel.apply)(GovServiceModel.unapply)
  )
}
