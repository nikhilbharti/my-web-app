import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationControllerSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "ApplicationController" should {
    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Some Useful Government Service")
    }

    "submit the index page" in {
      val thankYou = route(app, FakeRequest(POST, "/thankYou")).get
      status(thankYou) mustBe OK
      contentType(thankYou) mustBe Some("text/html")
      contentAsString(thankYou) must include ("thank you")
    }
  }
}
