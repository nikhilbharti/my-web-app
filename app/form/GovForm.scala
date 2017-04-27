package form

import model.{GovService, GovServiceModel}

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
