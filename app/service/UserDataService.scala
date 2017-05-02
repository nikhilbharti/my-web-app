package service


import javax.inject._

import scala.concurrent.Future
import connector.UserDataConnector
import models.UserDataModel
import play.api.mvc._

class UserDataService @Inject()(userDataConnector: UserDataConnector) {
  def countryList(): Future[List[String]] = userDataConnector.countryList

  def insertUserData(userData: UserDataModel): Future[Result] = userDataConnector.insertUserData(userData)
}


