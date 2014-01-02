package models.ui

import play.api.mvc.{WrappedRequest, AnyContent, Request}

case class CommonContext(
  meta: Meta,
  request: Request[AnyContent]) extends WrappedRequest(request)

case class Meta(
  title: String,
  description: String)
