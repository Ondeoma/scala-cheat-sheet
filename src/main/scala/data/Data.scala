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
    val FNA = FunctionInfo.nArg
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
      FNonA("List", "init", Nil, "List[A]", listImg("init.svg"), List(dangerT("例外"), infoT("部分取得")), "末尾以外を取得.<br>Nilの場合は例外."),
      FNonA("List", "inits", Nil, "Iterator[List[A]]", listImg("inits.svg"), List(infoT("部分取得")), "元のListからNilになるまで末尾要素を0~n個削る過程のList群の取得."),

      F1A("List", "take", Nil, T("n", "Int"), "List[A]", listImg("take.svg"), List(infoT("部分取得")), "先頭からn件取得.<br>不足時は存在するところまで取得."),
      F1A("List", "takeRight", Nil, T("n", "Int"), "List[A]", listImg("takeRight.svg"), List(infoT("部分取得")), "末尾からn件取得.<br>不足時は存在するところまで取得.<br>新Listの要素は逆順に'ならない'."),
      F1A("List", "takeWhile", Nil, T("p", "A => Boolean"), "List[A]", listImg("takeWhile.svg"), List(infoT("部分取得")), "先頭からpの結果がfalseになるまで取得."),
      F1A("List", "drop", Nil, T("n", "Int"), "List[A]", listImg("drop.svg"), List(infoT("部分取得")), "先頭からn件削除したListを取得.<br>不足時はNil."),
      F1A("List", "dropRight", Nil, T("n", "Int"), "List[A]", listImg("dropRight.svg"), List(infoT("部分取得")), "末尾からn件削除したListを取得.<br>不足時はNil."),
      F1A("List", "dropWhile", Nil, T("p", "A => Boolean"), "List[A]", listImg("dropWhile.svg"), List(infoT("部分取得")), "先頭からpの結果がfalseになるところまで削除したListを取得."),

      FNA("List", "slice", Nil, List(T("from", "Int"), T("until", "Int")), "List[A]", listImg("slice.svg"), List(infoT("部分取得")), "from ~ (until - 1)番目を取得.<br>範囲外は無視.<br>from >= untilの場合はNil."),
      F1A("List", "splitAt", Nil, T("n", "Int"), "(List[A], List[A])", listImg("splitAt.svg"), List(infoT("分割")), "n番目以降で分割.<br>不足時は存在するところまでとNilで分割."),
      F1A("List", "span", Nil, T("p", "A => Boolean"), "(List[A], List[A])", listImg("span.svg"), List(infoT("分割")), "pの結果がfalseになる要素以降で分割.<br>全てtrueの場合は全てとNilで分割"),
      F1A("List", "partition", Nil, T("p", "A => Boolean"), "(List[A], List[A])", listImg("partition.svg"), List(infoT("分割")), "pの結果によって分割(true = 左)."),
      F1A("List", "partitionMap", List("A1", "A2"), T("p", "A => Either[A1, A2]"), "(List[A1], List[A2])", listImg("partitionMap.svg"), List(infoT("分割")), "pを適用したEither型の状態によって分割(Left = 左).<br>結果のList内の要素はUnwrapされる."),
      
      F1A("List", "filter", Nil, T("p", "A => Boolean"), "List[A]", listImg("filter.svg"), List(infoT("絞り込み")), "pがtrueになる要素を取得."),
      F1A("List", "filterNot", Nil, T("p", "A => Boolean"), "List[A]", listImg("filterNot.svg"), List(infoT("絞り込み")), "pがfalseになる要素を取得."),

      F1A("List", "diff", List("B >: A"), T("that", "Seq[B]"), "List[A]", listImg("diff.svg"), List(infoT("絞り込み")), "引数の要素分を削った部分.<br>回数も考慮."),
      F1A("List", "intersect", List("B >: A"), T("that", "Seq[B]"), "List[A]", listImg("intersect.svg"), List(infoT("絞り込み")), "引数の要素と共通している部分.<br>回数も考慮."),
      
      FNonA("List", "distinct", Nil, "List[A]", listImg("distinct.svg"), List(infoT("絞り込み"), infoT("重複排除")), "重複を排除したListを取得.<br>重複分は先頭に近い方を維持."),
      F1A("List", "distinctBy", List("B"), T("f", "A => B"), "List[A]", listImg("distinctBy.svg"), List(infoT("絞り込み"), infoT("重複排除")), "fで変換した結果の重複を排除したListを取得.<br>重複分は先頭に近い方を維持."),

      F1A("List", "find", Nil, T("p", "A => Boolean"), "Option[A]", listImg("find.svg"), List(infoT("1つ取得"), infoT("検索")), "先頭からpがtrueになった最初の要素を取得.<br>見つからなかった場合はNone."),
      F1A("List", "findLast", Nil, T("p", "A => Boolean"), "Option[A]", listImg("findLast.svg"), List(infoT("1つ取得"), infoT("検索")), "末尾からpがtrueになった最初の要素を取得.<br>見つからなかった場合はNone."),

      F1A("List", "indexOf", List("B >: A"), T("elem", "B"), "Int", listImg("indexOf.svg"), List(infoT("Index取得"), infoT("検索")), "elemと等価な最初の要素のIndexを取得.<br>見つからなかった場合は-1."),
      FNA("List", "indexOf", List("B >: A"), List(T("elem", "B"), T("from", "Int")), "Int", listImg("indexOf(from).svg"), List(infoT("Index取得"), infoT("検索")), "from番目以降のelemと等価な最初の要素のIndexを取得.<br>見つからなかった場合は-1."),
      F1A("List", "indexWhere", Nil, T("p", "A => Boolean"), "Int", listImg("indexWhere.svg"), List(infoT("Index取得"), infoT("検索")), "pがtrueになった最初の要素ｎIndexを取得.<br>見つからなかった場合は-1."),
      FNA("List", "indexWhere", Nil, List(T("p", "A => Boolean"), T("from", "Int")), "Int", listImg("indexWhere(from).svg"), List(infoT("Index取得"), infoT("検索")), "from番目以降のpがtrueになった最初の要素ｎIndexを取得.<br>見つからなかった場合は-1."),
      F1A("List", "indexOfSlice", List("B >: A"), T("that", "Seq[B]"), "Int", listImg("indexOfSlice.svg"), List(infoT("Index取得"), infoT("検索")), "thatと等価になる要素群の開始Indexを取得.<br>見つからなかった場合は-1."),
      FNA("List", "indexOfSlice", List("B >: A"), List(T("that", "Seq[B]"), T("from", "Int")), "Int", listImg("indexOfSlice(from).svg"), List(infoT("Index取得"), infoT("検索")), "from番目以降のthatと等価になる要素群の開始Indexを取得.<br>見つからなかった場合は-1."),
      
      F1A("List", "lastIndexOf", List("B >: A"), T("elem", "B"), "Int", listImg("lastIndexOf.svg"), List(infoT("Index取得"), infoT("検索")), "末尾方向から探してelemと等価な最初の要素のIndexを取得.<br>見つからなかった場合は-1."),
      FNA("List", "lastIndexOf", List("B >: A"), List(T("elem", "B"), T("end", "Int")), "Int", listImg("lastIndexOf(end).svg"), List(infoT("Index取得"), infoT("検索")), "end番目以前の末尾方向から探してelemと等価な最初の要素のIndexを取得.<br>見つからなかった場合は-1."),
      F1A("List", "lastIndexWhere", Nil, T("p", "A => Boolean"), "Int", listImg("lastIndexWhere.svg"), List(infoT("Index取得"), infoT("検索")), "末尾方向から探してpがtrueになった最初の要素Indexを取得.<br>見つからなかった場合は-1."),
      FNA("List", "lastIndexWhere", Nil, List(T("p", "A => Boolean"), T("end", "Int")), "Int", listImg("lastIndexWhere(end).svg"), List(infoT("Index取得"), infoT("検索")), "end番目以前の末尾方向から探してpがtrueになった最初の要素Indexを取得.<br>見つからなかった場合は-1."),
      F1A("List", "lastIndexOfSlice", List("B >: A"), T("that", "Seq[B]"), "Int", listImg("lastIndexOfSlice.svg"), List(infoT("Index取得"), infoT("検索")), "末尾方向から探してthatと等価になる要素群の開始Indexを取得.<br>見つからなかった場合は-1."),
      FNA("List", "lastIndexOfSlice", List("B >: A"), List(T("that", "Seq[B]"), T("end", "Int")), "Int", listImg("lastIndexOfSlice(end).svg"), List(infoT("Index取得"), infoT("検索")), "end番目以前の末尾方向から探してthatと等価になる要素群の開始Indexを取得.<br>見つからなかった場合は-1."),
      
      F1A("List", "::", List("B >: A"), T("elem", "B"), "List[B]", listImg("cc.svg"), List(warnT("右結合"), infoT("連結")), "先頭に要素を追加したものを取得."),
      F1A("List", ":::", List("B >: A"), T("prefix", "List[B]"), "List[B]", listImg("ccc.svg"), List(warnT("右結合"), infoT("連結")), "List同士を連結したものを取得."),
      
      FNonA("List", "isEmpty", Nil, "Boolean", listImg("isEmpty.svg"), List(infoT("状況取得")), "要素があればfalse, Nilならtrue."),
      FNonA("List", "nonEmpty", Nil, "Boolean", listImg("nonEmpty.svg"), List(infoT("状況取得")), "要素があればtrue, Nilならfalse."),
      FNonA("List", "length<br>(size)", Nil, "Int", listImg("length.svg"), List(infoT("状況取得")), "要素数."),
      F1A("List", "count", Nil, T("p", "A => Boolean"), "Boolean", listImg("count.svg"), List(infoT("件数取得")), "pの結果がtrueになる要素数."),
      
      F1A("List", "foreach", List("U"), T("f", "A => U"), "Unit", listImg("foreach.svg"), List(infoT("作用")), "全ての値で順にfを実行."),

      F1A("List", "map", List("B"), T("that", "A => B"), "List[B]", listImg("map.svg"), List(infoT("変換")), "全ての値をfで変換."),
      FI1A("List", "flatten", List("B"), T("toIterableOnce", "A => IterableOnce[B]"), "List[B]", listImg("flatten.svg"), List(infoT("変換")), "List[IterableOnce[B]]をList[B]に変換.<br>図はList[List[B]]のイメージ.<br>IterableOnceは大まかにコレクション型やOption型等と捉えると良さそうです。"),
      F1A("List", "flatMap", List("B"), T("f", "A => IterableOnce[B]"), "List[B]", listImg("flatMap.svg"), List(infoT("変換"), infoT("モナド")), "全ての値をfで変換しflattenまで行います.<br>fはIterableOnce[B]を返す関数である必要があります.<br>図はList[List[B]]に変換されてflattenされるイメージ.<br>IterableOnceは大まかにコレクション型やOption型等と捉えると良さそうです"),

      F1A("List", "zip", List("B"), T("that", "IterableOnce[B]"), "List[(A, B)]", listImg("zip.svg"), List(infoT("結合")), "thatコレクションと同順に1つずつタプル化.<br>少ない側の要素数に揃う."),
      FNonA("List", "zipWithIndex", Nil, "List[(A, Int)]", listImg("zipWithIndex.svg"), List(infoT("結合")), "Indexとタプル化."),
      FNonA("List", "indices", Nil, "List[Int]", listImg("indices.svg"), List(infoT("変換"), infoT("添字")), "全要素のIndexをListとして取得."),
      FI1A("List", "unzip", List("A1","A2"), T("asPair", "A = (A1, A2)"), "(List[A1], List[A2])", listImg("unzip.svg"), List(infoT("分解")), "主に2要素タプルのListを分割した2つのListを取得."),
      
      FNonA("List", "reverse", Nil, "List[A]", listImg("reverse.svg"), List(infoT("並び替え")), "順番を反転する."),
      FI1A("List", "sorted", List("[B >: A]"), T("ord", "Ordering[B]"), "List[B]", listImg("sorted.svg"), List(infoT("並び替え")), "昇順に並び替える."),
      FunctionInfo("List", "sortBy", List("[B >: A]"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), true)),
        "List[B]", listImg("sortBy.svg"), List(infoT("並び替え")), "fで変換した値で昇順に並び替える."),
      F1A("List", "sortWith", Nil, T("lt", "(A, A) => Boolean"), "List[A]", listImg("sortWith.svg"), List(infoT("並び替え")), "隣り合う要素でfを実行した時に常にtrueになるように並び替える."),
      
      F1A("List", "reduce<br>(reduceLeft)", List("B >: A"), T("op", "(B, A) => B"), "B", listImg("reduce.svg"), List(dangerT("例外"), infoT("畳み込み")), "先頭から末尾までを順次、<br>opで演算した結果とその次の要素をopで演算する.<br>Nilの場合は例外."),
      F1A("List", "reduceOption<br>(reduceLeftOption)", List("B >: A"), T("op", "(B, A) => B"), "Option[B]", listImg("reduceOption.svg"), List(infoT("畳み込み")), "先頭から末尾までを順次、<br>opで演算した結果とその次の要素をopで演算する.<br>Nilの場合はNone."),
      F1A("List", "reduceRight", List("B >: A"), T("op", "(B, A) => B"), "B", listImg("reduceRight.svg"), List(dangerT("例外"), infoT("畳み込み")), "末尾から先頭までを順次、<br>opで演算した結果とその前の要素をopで演算する.<br>Nilの場合は例外."),
      F1A("List", "reduceRightOption", List("B >: A"), T("op", "(B, A) => B"), "Option[B]", listImg("reduceRightOption.svg"), List(infoT("畳み込み")), "末尾から先頭までを順次、<br>opで演算した結果とその前の要素をopで演算する.<br>Nilの場合はNone."),
      FunctionInfo("List", "fold<br>(foldLeft)", List("B >: A"),
        List(ArgsInfo(List(T("z", "B")), false),
          ArgsInfo(List(T("op", "(B, A) => B")), false)),
        "B", listImg("fold.svg"), List(infoT("畳み込み")), "先頭から末尾までを順次、<br>opで演算した結果とその次の要素をopで演算する.<br>ただし先頭値にはzを利用."),
      FunctionInfo("List", "foldRight", List("B >: A"),
        List(ArgsInfo(List(T("z", "B")), false),
          ArgsInfo(List(T("op", "(B, A) => B")), false)),
        "B", listImg("foldRight.svg"), List(infoT("畳み込み")), "末尾から先頭までを順次、<br>opで演算した結果とその次の要素をopで演算する.<br>ただし末尾値にはzを利用."),
      FI1A("List", "sum", List("B >: A"), T("num", "Numeric[B]"), "B", listImg("sum.svg"), List(infoT("畳み込み")), "合計値.<br>Nilの場合は0."),

      FunctionInfo("List", "scan<br>(scanLeft)", List("B >: A"),
        List(ArgsInfo(List(T("z", "B")), false),
          ArgsInfo(List(T("op", "(B, A) => B")), false)),
        "List[B]", listImg("scan.svg"), List(infoT("畳み込み")), "fold(foldLeft)の計算過程をListとして取得."),
      FunctionInfo("List", "scan", List("B >: A"),
        List(ArgsInfo(List(T("z", "B")), false),
          ArgsInfo(List(T("op", "(B, A) => B")), false)),
        "List[B]", listImg("scanRight.svg"), List(infoT("畳み込み")), "foldRightの計算過程をListとして取得."),
      
      FNonA("List", "mkString", Nil, "String", listImg("mkString.svg"), List(infoT("文字列作成")), "全要素を(toStringした結果を)結合した文字列."),
      F1A("List", "mkString", Nil, T("sep", "String"), "String", listImg("mkString(1).svg"), List(infoT("文字列作成")), "全要素を(toStringした結果を)seqで区切り結合した文字列."),
      FNA("List", "mkString", Nil, List(T("start", "String"), T("sep", "String"), T("end", "String")), "String", listImg("mkString(3).svg"), List(infoT("文字列作成")), "全要素を(toStringした結果を)seqで区切り結合した文字列から、先頭にstart 末尾にendを付与したもの."),
      
      F1A("List", "isDefinedAt", Nil, T("x", "Int"), "Boolean", listImg("isDefinedAt.svg"), List(infoT("存在確認")), "n番目の要素があればtrue."),
      F1A("List", "contains", List("A1 >: A"), T("elem", "A1"), "Boolean", listImg("contains.svg"), List(infoT("存在確認")), "elemが存在すればtrue."),
      F1A("List", "exists", Nil, T("p", "A => Boolean"), "Boolean", listImg("exists.svg"), List(infoT("存在確認")), "pがtrueになる要素が存在すればtrue."),
      F1A("List", "forall", Nil, T("p", "A => Boolean"), "Boolean", listImg("forall.svg"), List(infoT("全要素確認")), "pがtrueになる要素のみであればtrue.<br>Nilの場合はtrue."),

      FI1A("List", "min", List("B >: A"), T("ord", "Ordering[B]"), "B", listImg("min.svg"), List(dangerT("例外"), infoT("最小値"), infoT("1つ取得")), "最小値.<br>Nilの場合は例外."),
      FI1A("List", "minOption", List("B >: A"), T("ord", "Ordering[B]"), "Option[B]", listImg("minOption.svg"), List(infoT("最小値"), infoT("1つ取得")), "最小値.<br>Nilの場合はNone."),
      FunctionInfo("List", "minBy", List("B"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), false)),
        "B", listImg("minBy.svg"), List(dangerT("例外"), infoT("最小値"), infoT("1つ取得")), "最小値.<br>Nilの場合は例外."),
      FunctionInfo("List", "minByOption", List("B"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), false)),
        "Option[B]", listImg("minByOption.svg"), List(infoT("最小値"), infoT("1つ取得")), "最小値.<br>Nilの場合はNone."),

      FI1A("List", "max", List("B >: A"), T("ord", "Ordering[B]"), "B", listImg("max.svg"), List(dangerT("例外"), infoT("最大値"), infoT("1つ取得")), "最大値.<br>Nilの場合は例外."),
      FI1A("List", "maxOption", List("B >: A"), T("ord", "Ordering[B]"), "Option[B]", listImg("maxOption.svg"), List(infoT("最大値"), infoT("1つ取得")), "最大値.<br>Nilの場合はNone."),
      FunctionInfo("List", "maxBy", List("B"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), false)),
        "B", listImg("maxBy.svg"), List(dangerT("例外"), infoT("最大値"), infoT("1つ取得")), "最大値.<br>Nilの場合は例外."),
      FunctionInfo("List", "maxByOption", List("B"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), false)),
        "Option[B]", listImg("maxByOption.svg"), List(infoT("最大値"), infoT("1つ取得")), "最大値.<br>Nilの場合はNone."),

      F1A("List", "grouped", Nil, T("size", "Int"), "Iterator[A]", listImg("grouped.svg"), List(infoT("グループ化")), "size個ずつに分ける.<br>最後は端数になりえる."),
      F1A("List", "groupBy", List("K"), T("f", "A => K"), "Map[K, List[A]]", listImg("groupBy.svg"), List(infoT("グループ化")), "fを適用した結果をキーとしたMapを作成.<br>同じキーになる要素を集めたList値を保持."),
      FunctionInfo("List", "groupMap", List("K", "B"),
        List(ArgsInfo(List(T("key", "A => K")), false),
          ArgsInfo(List(T("f", "A => B")), false)), 
        "Map[K, List[B]]", listImg("groupMap.svg"), List(infoT("グループ化")), "groupByの結果の値(Mapのvalue)にfを適用したものを返す."),
      FunctionInfo("List", "groupMapReduce", List("K", "B"),
        List(ArgsInfo(List(T("key", "A => K")), false),
          ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("reduce", "(B, B) => B")), false)
        ),
        "Map[K, B]", listImg("groupMapReduce.svg"), List(infoT("グループ化")), "groupMapの結果の値(Mapのvalue)をreduceしたものを返す."),

    )
  }
}
