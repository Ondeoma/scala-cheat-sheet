package data

import models.*
import renders.Render

object Data {

  def listImg(s: String) = s"${Render.imgPath}/functions/list/$s"

  def infoT(s: String) = Tag(s, "info")

  def dangerT(s: String) = Tag(s, "danger")

  val functions: List[FunctionInfo] = {
    val F = FunctionInfo.apply
    val T = TypeInfo.apply
    List(
      F("List", "head", Nil, Nil, "A", listImg("head.svg"), List(dangerT("危険"), infoT("取得")), "先頭要素の取得."),
      F("List", "headOption", Nil, Nil, "Option[A]", listImg("headOption.svg"), List(infoT("取得")), "先頭要素の取得."),
      F("List", "map", List("B"), List(T("f", "A => B")), "List[B]", listImg("map.svg"), List(infoT("高階維持"), infoT("変換")), "全ての値をfで変換."),
    )
  }

}
