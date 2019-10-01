package controllers.api.v4

import javax.inject._
import models.Backend
import models.Entities.JSONImplicits._
import models.Entities.TargetsBody
import models.entities.APIErrorMessage
import models.entities.SearchResult.JSONImplicits._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SearchController @Inject()(implicit ec: ExecutionContext, backend: Backend, cc: ControllerComponents)
  extends AbstractController(cc) {

  def search(q: String) = Action.async { req =>
    val indices = Seq("search_target", "search_disease", "search_drug")
    for {
      res <- backend.search(indices, q, None, None)
    } yield Ok(Json.toJson(res))
  }
}
