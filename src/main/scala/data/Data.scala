package data

import models.*
import renders.Render

object Data {

  def listImg(s: String) = s"${Render.imgPath}/functions/list/$s"

  def infoT(s: String) = Tag(s, "info")

  def warnT(s: String) = Tag(s, "warn")

  def dangerT(s: String) = Tag(s, "danger")

  val functions: List[FunctionInfo] = {
    
    // case class FunctionInfo(group: String,
    //                         name: String,
    //                         typArgs: List[String],
    //                         args: Option[List[TypeInfo]],
    //                         rtn: String,
    //                         image: String,
    //                         tags: List[Tag],
    //                         description: String)
    
    val T = TypeInfo.apply
    List(
      FunctionInfo("List", "apply", Nil, List(T("n", "Int")), "A", listImg("apply.svg"), List(dangerT("例外"), infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合は例外."),
      FunctionInfo("List", "lift", Nil, List(T("n", "Int")), "Option[A]", listImg("lift.svg"), List(infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合はNone."),
      
      FunctionInfo("List", "head", Nil, None, "A", listImg("head.svg"), List(dangerT("例外"), infoT("1つ取得")), "先頭要素の取得.<br>Nilの場合は例外."),
      FunctionInfo("List", "headOption", Nil, None, "Option[A]", listImg("headOption.svg"), List(infoT("1つ取得")), "先頭要素の取得.<br>Nilの場合はNone."),
      FunctionInfo("List", "last", Nil, None, "A", listImg("last.svg"), List(dangerT("例外"), infoT("1つ取得")), "末尾要素の取得.<br>Nilの場合は例外."),
      FunctionInfo("List", "lastOption", Nil, None, "Option[A]", listImg("lastOption.svg"), List(infoT("1つ取得")), "末尾要素の取得.<br>Niの場合はNone."),
      
      FunctionInfo("List", "tail", Nil, None, "List[A]", listImg("tail.svg"), List(dangerT("例外"), infoT("部分取得")), "先頭以外を取得.<br>Nilの場合は例外."),
      FunctionInfo("List", "tails", Nil, None, "Iterator[List[A]]", listImg("tails.svg"), List(infoT("部分取得")), "元のListからNilになるまで先頭要素を0~n個削る過程のList群の取得."),

      FunctionInfo("List", "take", Nil, List(T("n", "Int")), "List[A]", listImg("take.svg"), List(infoT("部分取得")), "先頭からn件取得.<br>不足時は存在するところまで取得."),
      FunctionInfo("List", "takeRight", Nil, List(T("n", "Int")), "List[A]", listImg("takeRight.svg"), List(infoT("部分取得")), "末尾からn件取得.<br>不足時は存在するところまで取得.<br>新Listの要素は逆順に'ならない'."),
      FunctionInfo("List", "takeWhile", Nil, List(T("p", "A => Boolean")), "List[A]", listImg("takeWhile.svg"), List(infoT("部分取得")), "先頭からpの結果がfalseになるまで取得."),
      FunctionInfo("List", "drop", Nil, List(T("n", "Int")), "List[A]", listImg("drop.svg"), List(infoT("部分取得")), "先頭からn件削除したListを取得.<br>不足時はNil."),
      FunctionInfo("List", "dropRight", Nil, List(T("n", "Int")), "List[A]", listImg("dropRight.svg"), List(infoT("部分取得")), "末尾からn件削除したListを取得.<br>不足時はNil."),
      FunctionInfo("List", "dropWhile", Nil, List(T("p", "A => Boolean")), "List[A]", listImg("dropWhile.svg"), List(infoT("部分取得")), "先頭からpの結果がfalseになるところまで削除したListを取得."),

      FunctionInfo("List", "slice", Nil, List(T("from", "Int"), T("until", "Int")), "List[A]", listImg("slice.svg"), List(infoT("部分取得")), "from ~ (until - 1)番目を取得.<br>範囲外は無視.<br>from >= untilの場合はNil."),
      FunctionInfo("List", "splitAt", Nil, List(T("n", "Int")), "(List[A], List[A])", listImg("splitAt.svg"), List(infoT("分割")), "n番目以降で分割.<br>不足時は存在するところまでとNilで分割."),
      FunctionInfo("List", "span", Nil, List(T("p", "A => Boolean")), "(List[A], List[A])", listImg("span.svg"), List(infoT("分割")), "pの結果がfalseになる要素以降で分割.<br>全てtrueの場合は全てとNilで分割"),

      FunctionInfo("List", "filter", Nil, List(T("p", "A => Boolean")), "List[A]", listImg("filter.svg"), List(infoT("絞り込み")), "pがtrueになる要素を取得."),
      FunctionInfo("List", "filterNot", Nil, List(T("p", "A => Boolean")), "List[A]", listImg("filterNot.svg"), List(infoT("絞り込み")), "pがfalseになる要素を取得."),

      FunctionInfo("List", "distinct", Nil, None, "List[A]", listImg("distinct.svg"), List(infoT("絞り込み"), infoT("重複排除")), "重複を排除したListを取得.<br>重複分は先頭に近い方を維持."),
      FunctionInfo("List", "distinctBy", List("B"), List(T("f", "A => B")), "List[A]", listImg("distinctBy.svg"), List(infoT("絞り込み"), infoT("重複排除")), "fで変換した結果の重複を排除したListを取得.<br>重複分は先頭に近い方を維持."),

      FunctionInfo("List", "find", Nil, List(T("p", "A => Boolean")), "Option[A]", listImg("find.svg"), List(infoT("1つ取得"), infoT("検索")), "先頭からpがtrueになった最初の要素を取得.<br>見つからなかった場合はNone."),
      FunctionInfo("List", "findLast", Nil, List(T("p", "A => Boolean")), "Option[A]", listImg("findLast.svg"), List(infoT("1つ取得"), infoT("検索")), "末尾からpがtrueになった最初の要素を取得.<br>見つからなかった場合はNone."),

      FunctionInfo("List", "::", List("B >: A"), List(T("elem", "B")), "List[B]", listImg("cc.svg"), List(warnT("右結合"), infoT("連結")), "先頭に要素を追加したものを取得."),
      FunctionInfo("List", ":::", List("B >: A"), List(T("prefix", "List[B]")), "List[B]", listImg("ccc.svg"), List(warnT("右結合"), infoT("連結")), "List同士を連結したものを取得."),

      FunctionInfo("List", "isEmpty", Nil, None, "Boolean", listImg("isEmpty.svg"), List(infoT("状況取得")), "要素があればfalse, Nilならtrue."),
      FunctionInfo("List", "nonEmpty", Nil, None, "Boolean", listImg("nonEmpty.svg"), List(infoT("状況取得")), "要素があればtrue, Nilならfalse."),
      FunctionInfo("List", "length", Nil, None, "Int", listImg("length.svg"), List(infoT("状況取得")), "要素数."),
      
      FunctionInfo("List", "map", List("B"), List(T("f", "A => B")), "List[B]", listImg("map.svg"), List(infoT("高階維持"), infoT("変換")), "全ての値をfで変換."),
    )
  }

}
