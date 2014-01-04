package controllers

import play.api._
import play.api.mvc._
import play.api.i18n.Lang
import models.ui.{CommonContext, Meta}
import java.net.URI
import common.SupportedLang

object Application extends Controller {

  def index = withCommonContext { context => lang =>
    Ok(views.html.index(context, lang))
  }

  def langIndex(lang: String) = withCommonContext { context => lang =>
    Ok(views.html.langIndex(context, lang))
  }

  def gallery(lang: String) = withCommonContext { context => lang =>
    Ok(views.html.langIndex(context, lang))
  }

  def untrail(path: String) = Action {
    MovedPermanently("/" + path)
  }

  def withCommonContext(f: CommonContext => Lang => Result) = Action { request =>
    f(CommonContext(Meta("x", "x"), request))(getLangFromUri(new URI(request.uri)))
  }

  private def getLangFromUri(uri: URI): Lang = {
    val isoLang = pathWithoutFirstSlash(uri.getPath).takeWhile(c => c != '/').toLowerCase
    SupportedLang.values.find(value => value.toString == isoLang) match {
      case Some(lang) => lang.toLang
      case None => Lang.defaultLang
    }
  }

  private def pathWithoutFirstSlash(path: String) = {
    if (path(0) == '/')
      path.takeRight(path.length - 1)
    else
      path
  }
}