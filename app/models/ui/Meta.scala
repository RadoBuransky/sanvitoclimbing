package models.ui

import play.api.mvc.{WrappedRequest, AnyContent, Request}
import common.SupportedLang
import play.api.Logger

case class CommonContext(
  meta: Meta,
  request: Request[AnyContent]) extends WrappedRequest(request) {

  private val langRegex = SupportedLang.values.map(v => "/" + v).mkString("|")

  def isLangUri: Boolean = langRegex.r.findFirstIn(request.uri).isDefined

  def langlessUri: String = request.uri.replaceAll(langRegex, "")
}

case class Meta(
  title: String,
  description: String)
