package common

import play.api.i18n.Lang

object SupportedLang extends Enumeration {
  type SupportedLang = Value
  val en, de, sk, it = Value

  class SupportedLangValue(supportedLang: Value) {
    def toLang(): Lang = {
      Lang(supportedLang.toString)
    }
  }

  def apply(iso: String): SupportedLang = {
    SupportedLang.withName(iso.toLowerCase)
  }

  implicit def supportedLang2SupportedLangValue(supportedLang: Value) = new SupportedLangValue(supportedLang)
}
