package data

import models.*
import renders.Render

object Data {

  def listImg(s: String) = s"${Render.imgPath}/functions/list/$s"

  def infoT(s: String) = Tag(s, "info")

  def dangerT(s: String) = Tag(s, "danger")

  val functions: List[FunctionInfo] = {
    
    val F = FunctionInfo.apply
    // case class FunctionInfo(group: String,
    //                         name: String,
    //                         typArgs: List[String],
    //                         args: List[TypeInfo],
    //                         rtn: String,
    //                         image: String,
    //                         tags: List[Tag],
    //                         description: String)
    
    val T = TypeInfo.apply
    List(
      F("List", "apply", Nil, List(T("n", "Int")), "A", listImg("apply.svg"), List(dangerT("例外"), infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合は例外."),
      F("List", "lift", Nil, List(T("n", "Int")), "Option[A]", listImg("lift.svg"), List(infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合はNone."),
      F("List", "head", Nil, Nil, "A", listImg("head.svg"), List(dangerT("例外"), infoT("1つ取得")), "先頭要素の取得.<br>Nilの場合は例外."),
      F("List", "headOption", Nil, Nil, "Option[A]", listImg("headOption.svg"), List(infoT("1つ取得")), "先頭要素の取得.<br>Nilの場合はNone."),
      F("List", "last", Nil, Nil, "A", listImg("last.svg"), List(dangerT("例外"), infoT("1つ取得")), "末尾要素の取得.<br>Nilの場合は例外."),
      F("List", "lastOption", Nil, Nil, "Option[A]", listImg("lastOption.svg"), List(infoT("1つ取得")), "末尾要素の取得.<br>Niの場合はNone."),

      F("List", "take", Nil, List(T("n", "Int")), "List[A]", listImg("take.svg"), List(infoT("部分取得")), "先頭からn件取得.<br>不足時は存在するところまで取得."),
      F("List", "takeRight", Nil, List(T("n", "Int")), "List[A]", listImg("takeRight.svg"), List(infoT("部分取得")), "末尾からn件取得.<br>不足時は存在するところまで取得.<br>新Listの要素は逆順に'ならない'."),
      F("List", "takeWhile", Nil, List(T("p", "A => Boolean")), "List[A]", listImg("takeWhile.svg"), List(infoT("部分取得")), "先頭からpの結果がfalseになるまで取得."),
      F("List", "drop", Nil, List(T("n", "Int")), "List[A]", listImg("drop.svg"), List(infoT("部分取得")), "先頭からn件削除したListを取得.<br>不足時はNil."),
      F("List", "dropRight", Nil, List(T("n", "Int")), "List[A]", listImg("dropRight.svg"), List(infoT("部分取得")), "末尾からn件削除したListを取得.<br>不足時はNil."),
      F("List", "dropWhile", Nil, List(T("p", "A => Boolean")), "List[A]", listImg("dropWhile.svg"), List(infoT("部分取得")), "先頭からpの結果がfalseになるところまで削除したListを取得."),

      F("List", "slice", Nil, List(T("from", "Int"), T("until", "Int")), "List[A]", listImg("slice.svg"), List(infoT("部分取得")), "from ~ (until - 1)番目を取得.<br>範囲外は無視.<br>from >= untilの場合はNil."),
      F("List", "splitAt", Nil, List(T("n", "Int")), "(List[A], List[A])", listImg("splitAt.svg"), List(infoT("分割")), "n番目以降で分割.<br>不足時は存在するところまでとNilで分割."),
      F("List", "span", Nil, List(T("p", "A => Boolean")), "(List[A], List[A])", listImg("span.svg"), List(infoT("分割")), "pの結果がfalseになる要素以降で分割.<br>全てtrueの場合は全てとNilで分割"),
      
      F("List", "map", List("B"), List(T("f", "A => B")), "List[B]", listImg("map.svg"), List(infoT("高階維持"), infoT("変換")), "全ての値をfで変換."),
    )
  }

}
