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

    val FNonA = FunctionInfo.noArg
    val F1A = FunctionInfo.oneArg
    val FNN = FunctionInfo.nArg
    val FI1A = FunctionInfo.impArg
    val FINN = FunctionInfo.impNArg
    val T = TypeInfo.apply

    List(
      F1A("List", "apply", Nil, T("n", "Int"), "A", listImg("apply.svg"), List(dangerT("例外"), infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合は例外."),
      F1A("List", "lift", Nil, T("n", "Int"), "Option[A]", listImg("lift.svg"), List(infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合はNone."),

      FNonA("List", "head", Nil, "A", listImg("head.svg"), List(dangerT("例外"), infoT("1つ取得")), "先頭要素の取得.<br>Nilの場合は例外."),
      FNonA("List", "headOption", Nil, "Option[A]", listImg("headOption.svg"), List(infoT("1つ取得")), "先頭要素の取得.<br>Nilの場合はNone."),
      FNonA("List", "last", Nil, "A", listImg("last.svg"), List(dangerT("例外"), infoT("1つ取得")), "末尾要素の取得.<br>Nilの場合は例外."),
      FNonA("List", "lastOption", Nil, "Option[A]", listImg("lastOption.svg"), List(infoT("1つ取得")), "末尾要素の取得.<br>Niの場合はNone."),

      FNonA("List", "tail", Nil, "List[A]", listImg("tail.svg"), List(dangerT("例外"), infoT("部分取得")), "先頭以外を取得.<br>Nilの場合は例外."),
      FNonA("List", "tails", Nil, "Iterator[List[A]]", listImg("tails.svg"), List(infoT("部分取得")), "元のListからNilになるまで先頭要素を0~n個削る過程のList群の取得."),

      F1A("List", "take", Nil, T("n", "Int"), "List[A]", listImg("take.svg"), List(infoT("部分取得")), "先頭からn件取得.<br>不足時は存在するところまで取得."),
      F1A("List", "takeRight", Nil, T("n", "Int"), "List[A]", listImg("takeRight.svg"), List(infoT("部分取得")), "末尾からn件取得.<br>不足時は存在するところまで取得.<br>新Listの要素は逆順に'ならない'."),
      F1A("List", "takeWhile", Nil, T("p", "A => Boolean"), "List[A]", listImg("takeWhile.svg"), List(infoT("部分取得")), "先頭からpの結果がfalseになるまで取得."),
      F1A("List", "drop", Nil, T("n", "Int"), "List[A]", listImg("drop.svg"), List(infoT("部分取得")), "先頭からn件削除したListを取得.<br>不足時はNil."),
      F1A("List", "dropRight", Nil, T("n", "Int"), "List[A]", listImg("dropRight.svg"), List(infoT("部分取得")), "末尾からn件削除したListを取得.<br>不足時はNil."),
      F1A("List", "dropWhile", Nil, T("p", "A => Boolean"), "List[A]", listImg("dropWhile.svg"), List(infoT("部分取得")), "先頭からpの結果がfalseになるところまで削除したListを取得."),

      FNN("List", "slice", Nil, List(T("from", "Int"), T("until", "Int")), "List[A]", listImg("slice.svg"), List(infoT("部分取得")), "from ~ (until - 1)番目を取得.<br>範囲外は無視.<br>from >= untilの場合はNil."),
      F1A("List", "splitAt", Nil, T("n", "Int"), "(List[A], List[A])", listImg("splitAt.svg"), List(infoT("分割")), "n番目以降で分割.<br>不足時は存在するところまでとNilで分割."),
      F1A("List", "span", Nil, T("p", "A => Boolean"), "(List[A], List[A])", listImg("span.svg"), List(infoT("分割")), "pの結果がfalseになる要素以降で分割.<br>全てtrueの場合は全てとNilで分割"),

      F1A("List", "filter", Nil, T("p", "A => Boolean"), "List[A]", listImg("filter.svg"), List(infoT("絞り込み")), "pがtrueになる要素を取得."),
      F1A("List", "filterNot", Nil, T("p", "A => Boolean"), "List[A]", listImg("filterNot.svg"), List(infoT("絞り込み")), "pがfalseになる要素を取得."),

      FNonA("List", "distinct", Nil, "List[A]", listImg("distinct.svg"), List(infoT("絞り込み"), infoT("重複排除")), "重複を排除したListを取得.<br>重複分は先頭に近い方を維持."),
      F1A("List", "distinctBy", List("B"), T("f", "A => B"), "List[A]", listImg("distinctBy.svg"), List(infoT("絞り込み"), infoT("重複排除")), "fで変換した結果の重複を排除したListを取得.<br>重複分は先頭に近い方を維持."),

      F1A("List", "find", Nil, T("p", "A => Boolean"), "Option[A]", listImg("find.svg"), List(infoT("1つ取得"), infoT("検索")), "先頭からpがtrueになった最初の要素を取得.<br>見つからなかった場合はNone."),
      F1A("List", "findLast", Nil, T("p", "A => Boolean"), "Option[A]", listImg("findLast.svg"), List(infoT("1つ取得"), infoT("検索")), "末尾からpがtrueになった最初の要素を取得.<br>見つからなかった場合はNone."),

      F1A("List", "::", List("B >: A"), T("elem", "B"), "List[B]", listImg("cc.svg"), List(warnT("右結合"), infoT("連結")), "先頭に要素を追加したものを取得."),
      F1A("List", ":::", List("B >: A"), T("prefix", "List[B]"), "List[B]", listImg("ccc.svg"), List(warnT("右結合"), infoT("連結")), "List同士を連結したものを取得."),

      FNonA("List", "isEmpty", Nil, "Boolean", listImg("isEmpty.svg"), List(infoT("状況取得")), "要素があればfalse, Nilならtrue."),
      FNonA("List", "nonEmpty", Nil, "Boolean", listImg("nonEmpty.svg"), List(infoT("状況取得")), "要素があればtrue, Nilならfalse."),
      FNonA("List", "length", Nil, "Int", listImg("length.svg"), List(infoT("状況取得")), "要素数."),

      F1A("List", "foreach", List("U"), T("f", "A => U"), "Unit", listImg("foreach.svg"), List(infoT("作用")), "全ての値で順にfを実行."),

      F1A("List", "map", List("B"), T("f", "A => B"), "List[B]", listImg("map.svg"), List(infoT("高階維持"), infoT("変換")), "全ての値をfで変換."),
      FI1A("List", "flatten", List("B"), T("toIterableOnce", "A => IterableOnce[B]"), "List[B]", listImg("flatten.svg"), List(infoT("変換")), "List[IterableOnce[B]]をList[B]に変換.<br>図はList[List[B]]のイメージ.<br>IterableOnceは大まかにコレクション型やOption型等と捉えると良さそうです。"),
      F1A("List", "flatMap", List("B"), T("f", "A => IterableOnce[B]"), "List[B]", listImg("flatMap.svg"), List(infoT("変換"), infoT("モナド")), "全ての値をfで変換しflattenまで行います.<br>fはIterableOnce[B]を返す関数である必要があります.<br>図はList[List[B]]に変換されてflattenされるイメージ.<br>IterableOnceは大まかにコレクション型やOption型等と捉えると良さそうです"),


      FNonA("List", "reverse", Nil, "List[A]", listImg("reverse.svg"), List(infoT("並び替え")), "順番を反転する."),
      FI1A("List", "sorted", List("[B >: A]"), T("ord", "Ordering[B]"), "List[B]", listImg("sorted.svg"), List(infoT("並び替え")), "昇順に並び替える."),
      FunctionInfo("List", "sortBy", List("[B >: A]"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), true)),
        "List[B]", listImg("sortBy.svg"), List(infoT("並び替え")), "fで変換した値で昇順に並び替える."),
      F1A("List", "sortWith", Nil, T("lt", "(A, A) => Boolean"), "List[A]", listImg("sortWith.svg"), List(infoT("並び替え")), "隣り合う要素でfを実行した時に常にtrueになるように並び替える."),
    )
  }

}
