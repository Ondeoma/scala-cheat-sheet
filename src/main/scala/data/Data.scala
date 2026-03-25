package data

import models.*
import renders.Render

object Data {

  def listImg(s: String) = s"${Render.imgPath}/functions/list/$s"

  def infoT(s: String) = Tag(s, "info")

  def warnT(s: String) = Tag(s, "warn")

  def dangerT(s: String) = Tag(s, "danger")

  val functions: List[FunctionInfo] = {

    import enums.FunctionGroup as G

    val FNonA = FunctionInfo.noArg
    val F1A = FunctionInfo.oneArg
    val FNA = FunctionInfo.nArg
    val FI1A = FunctionInfo.impArg
    val FINN = FunctionInfo.impNArg
    val T = TypeInfo.apply

    val forList = List(
      F1A(G.List, "apply", Nil, T("n", "Int"), "A", listImg("apply.svg"), List(dangerT("例外"), infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合は例外."),
      FNA(G.List, "applyOrElse", List("B >: A"), List(T("x", "Int"), T("default", "Int => B")), "B", listImg("applyOrElse.svg"), List(infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合はdefault.<br>defaultの引数はn."),
      F1A(G.List, "lift<br>(unapply)", Nil, T("n", "Int"), "Option[A]", listImg("lift.svg"), List(infoT("1つ取得")), "n番目の要素の取得.<br>存在しない場合はNone."),

      FNonA(G.List, "head", Nil, "A", listImg("head.svg"), List(dangerT("例外"), infoT("1つ取得")), "先頭要素の取得.<br>Nilの場合は例外."),
      FNonA(G.List, "headOption", Nil, "Option[A]", listImg("headOption.svg"), List(infoT("1つ取得")), "先頭要素の取得.<br>Nilの場合はNone."),
      FNonA(G.List, "last", Nil, "A", listImg("last.svg"), List(dangerT("例外"), infoT("1つ取得"), warnT("List苦手")), "末尾要素の取得.<br>Nilの場合は例外."),
      FNonA(G.List, "lastOption", Nil, "Option[A]", listImg("lastOption.svg"), List(infoT("1つ取得"), warnT("List苦手")), "末尾要素の取得.<br>Niの場合はNone."),

      FNonA(G.List, "tail", Nil, "List[A]", listImg("tail.svg"), List(dangerT("例外"), infoT("部分")), "先頭以外を取得.<br>Nilの場合は例外."),
      FNonA(G.List, "tails", Nil, "Iterator[List[A]]", listImg("tails.svg"), List(infoT("部分")), "元のListからNilになるまで先頭要素を0~n個削る過程のList群の取得."),
      FNonA(G.List, "init", Nil, "List[A]", listImg("init.svg"), List(dangerT("例外"), infoT("部分"), warnT("List苦手")), "末尾以外を取得.<br>Nilの場合は例外."),
      FNonA(G.List, "inits", Nil, "Iterator[List[A]]", listImg("inits.svg"), List(infoT("部分"), warnT("List苦手")), "元のListからNilになるまで末尾要素を0~n個削る過程のList群の取得."),

      F1A(G.List, "take", Nil, T("n", "Int"), "List[A]", listImg("take.svg"), List(infoT("部分")), "先頭からn件取得.<br>不足時は存在するところまで取得."),
      F1A(G.List, "takeRight", Nil, T("n", "Int"), "List[A]", listImg("takeRight.svg"), List(infoT("部分")), "末尾からn件取得.<br>不足時は存在するところまで取得.<br>新Listの要素は逆順に'ならない'."),
      F1A(G.List, "takeWhile", Nil, T("p", "A => Boolean"), "List[A]", listImg("takeWhile.svg"), List(infoT("部分")), "先頭からpの結果がfalseになるまで取得."),
      F1A(G.List, "drop", Nil, T("n", "Int"), "List[A]", listImg("drop.svg"), List(infoT("部分")), "先頭からn件削除したListを取得.<br>不足時はNil."),
      F1A(G.List, "dropRight", Nil, T("n", "Int"), "List[A]", listImg("dropRight.svg"), List(infoT("部分")), "末尾からn件削除したListを取得.<br>不足時はNil."),
      F1A(G.List, "dropWhile", Nil, T("p", "A => Boolean"), "List[A]", listImg("dropWhile.svg"), List(infoT("部分")), "先頭からpの結果がfalseになるところまで削除したListを取得."),

      FNA(G.List, "slice", Nil, List(T("from", "Int"), T("until", "Int")), "List[A]", listImg("slice.svg"), List(infoT("部分")), "from ~ (until - 1)番目を取得.<br>範囲外は無視.<br>from >= untilの場合はNil."),
      F1A(G.List, "splitAt", Nil, T("n", "Int"), "(List[A], List[A])", listImg("splitAt.svg"), List(infoT("分割")), "n番目以降で分割.<br>不足時は存在するところまでとNilで分割."),
      F1A(G.List, "span", Nil, T("p", "A => Boolean"), "(List[A], List[A])", listImg("span.svg"), List(infoT("分割")), "pの結果がfalseになる要素以降で分割.<br>全てtrueの場合は全てとNilで分割"),
      F1A(G.List, "sliding", Nil, T("size", "Int"), "Iterator(List[A])", listImg("sliding.svg"), List(infoT("部分集合")), "1つずつ移動しながらsize個取得したList群."),
      FNA(G.List, "sliding", Nil, List(T("size", "Int"), T("step", "Int")), "Iterator(List[A])", listImg("sliding(2).svg"), List(infoT("部分集合")), "stepずつ移動しながらsize個取得したList群.."),

      F1A(G.List, "combinations", Nil, T("n", "Int"), "Iterator[List[A]]", listImg("combinations.svg"), List(infoT("組合せ")), "n個ずつ分けた場合の全組合せ.<br>順番違いは含めない.<br>同値は区別しない."),
      FNonA(G.List, "permutations", Nil, "Iterator[List[A]]", listImg("permutations.svg"), List(infoT("組合せ")), "全並び順.<br>同値は区別しない"),

      F1A(G.List, "partition", Nil, T("p", "A => Boolean"), "(List[A], List[A])", listImg("partition.svg"), List(infoT("分割")), "pの結果によって分割(true = 左)."),
      F1A(G.List, "partitionMap", List("A1", "A2"), T("p", "A => Either[A1, A2]"), "(List[A1], List[A2])", listImg("partitionMap.svg"), List(infoT("分割")), "pを適用したEither型の状態によって分割(Left = 左).<br>結果のList内の要素はUnwrapされる."),

      F1A(G.List, "filter", Nil, T("p", "A => Boolean"), "List[A]", listImg("filter.svg"), List(infoT("絞り込み")), "pがtrueになる要素を取得."),
      F1A(G.List, "filterNot", Nil, T("p", "A => Boolean"), "List[A]", listImg("filterNot.svg"), List(infoT("絞り込み")), "pがfalseになる要素を取得."),

      F1A(G.List, "collect", List("B"), T("pf", "PartialFunction[A, B]"), "List[B]", listImg("collect.svg"), List(infoT("絞り込み"), infoT("変換")), "pfでマッチした値を変換したリスト.マッチしなかった要素は除外する."),
      F1A(G.List, "collectFirst", List("B"), T("pf", "PartialFunction[A, B]"), "Option[B]", listImg("collectFirst.svg"), List(infoT("検索"), infoT("変換")), "pfで最初にマッチした値を変換した値.マッチしなかった要素はNone."),

      F1A(G.List, "diff", List("B >: A"), T("that", "Seq[B]"), "List[A]", listImg("diff.svg"), List(infoT("絞り込み")), "引数の要素分を削った部分.<br>回数も考慮."),
      F1A(G.List, "intersect", List("B >: A"), T("that", "Seq[B]"), "List[A]", listImg("intersect.svg"), List(infoT("絞り込み")), "引数の要素と共通している部分.<br>回数も考慮."),

      FNonA(G.List, "distinct", Nil, "List[A]", listImg("distinct.svg"), List(infoT("絞り込み"), infoT("重複排除")), "重複を排除したListを取得.<br>重複分は先頭に近い方を維持."),
      F1A(G.List, "distinctBy", List("B"), T("f", "A => B"), "List[A]", listImg("distinctBy.svg"), List(infoT("絞り込み"), infoT("重複排除")), "fで変換した結果の重複を排除したListを取得.<br>重複分は先頭に近い方を維持."),

      F1A(G.List, "find", Nil, T("p", "A => Boolean"), "Option[A]", listImg("find.svg"), List(infoT("1つ取得"), infoT("検索")), "先頭からpがtrueになった最初の要素を取得.<br>見つからなかった場合はNone."),
      F1A(G.List, "findLast", Nil, T("p", "A => Boolean"), "Option[A]", listImg("findLast.svg"), List(infoT("1つ取得"), infoT("検索"), warnT("List苦手")), "末尾からpがtrueになった最初の要素を取得.<br>見つからなかった場合はNone."),

      F1A(G.List, "indexOf", List("B >: A"), T("elem", "B"), "Int", listImg("indexOf.svg"), List(infoT("Index"), infoT("検索")), "elemと等価な最初の要素のIndexを取得.<br>見つからなかった場合は-1."),
      FNA(G.List, "indexOf", List("B >: A"), List(T("elem", "B"), T("from", "Int")), "Int", listImg("indexOf(from).svg"), List(infoT("Index"), infoT("検索")), "from番目以降のelemと等価な最初の要素のIndexを取得.<br>見つからなかった場合は-1."),
      F1A(G.List, "indexWhere", Nil, T("p", "A => Boolean"), "Int", listImg("indexWhere.svg"), List(infoT("Index"), infoT("検索")), "pがtrueになった最初の要素ｎIndexを取得.<br>見つからなかった場合は-1."),
      FNA(G.List, "indexWhere", Nil, List(T("p", "A => Boolean"), T("from", "Int")), "Int", listImg("indexWhere(from).svg"), List(infoT("Index"), infoT("検索")), "from番目以降のpがtrueになった最初の要素ｎIndexを取得.<br>見つからなかった場合は-1."),
      F1A(G.List, "indexOfSlice", List("B >: A"), T("that", "Seq[B]"), "Int", listImg("indexOfSlice.svg"), List(infoT("Index"), infoT("検索")), "thatと等価になる要素群の開始Indexを取得.<br>見つからなかった場合は-1."),
      FNA(G.List, "indexOfSlice", List("B >: A"), List(T("that", "Seq[B]"), T("from", "Int")), "Int", listImg("indexOfSlice(from).svg"), List(infoT("Index"), infoT("検索")), "from番目以降のthatと等価になる要素群の開始Indexを取得.<br>見つからなかった場合は-1."),

      F1A(G.List, "lastIndexOf", List("B >: A"), T("elem", "B"), "Int", listImg("lastIndexOf.svg"), List(infoT("Index"), infoT("検索"), warnT("List苦手")), "末尾方向から探してelemと等価な最初の要素のIndexを取得.<br>見つからなかった場合は-1."),
      FNA(G.List, "lastIndexOf", List("B >: A"), List(T("elem", "B"), T("end", "Int")), "Int", listImg("lastIndexOf(end).svg"), List(infoT("Index"), infoT("検索"), warnT("List苦手")), "end番目以前の末尾方向から探してelemと等価な最初の要素のIndexを取得.<br>見つからなかった場合は-1."),
      F1A(G.List, "lastIndexWhere", Nil, T("p", "A => Boolean"), "Int", listImg("lastIndexWhere.svg"), List(infoT("Index"), infoT("検索"), warnT("List苦手")), "末尾方向から探してpがtrueになった最初の要素Indexを取得.<br>見つからなかった場合は-1."),
      FNA(G.List, "lastIndexWhere", Nil, List(T("p", "A => Boolean"), T("end", "Int")), "Int", listImg("lastIndexWhere(end).svg"), List(infoT("Index"), infoT("検索"), warnT("List苦手")), "end番目以前の末尾方向から探してpがtrueになった最初の要素Indexを取得.<br>見つからなかった場合は-1."),
      F1A(G.List, "lastIndexOfSlice", List("B >: A"), T("that", "Seq[B]"), "Int", listImg("lastIndexOfSlice.svg"), List(infoT("Index"), infoT("検索"), warnT("List苦手")), "末尾方向から探してthatと等価になる要素群の開始Indexを取得.<br>見つからなかった場合は-1."),
      FNA(G.List, "lastIndexOfSlice", List("B >: A"), List(T("that", "Seq[B]"), T("end", "Int")), "Int", listImg("lastIndexOfSlice(end).svg"), List(infoT("Index"), infoT("検索"), warnT("List苦手")), "end番目以前の末尾方向から探してthatと等価になる要素群の開始Indexを取得.<br>見つからなかった場合は-1."),

      F1A(G.List, "::", List("B >: A"), T("elem", "B"), "List[B]", listImg("cc.svg"), List(warnT("右結合"), infoT("連結")), "先頭に要素を追加したものを取得."),
      F1A(G.List, ":::", List("B >: A"), T("prefix", "List[B]"), "List[B]", listImg("ccc.svg"), List(warnT("右結合"), infoT("連結")), "List同士を連結したものを取得."),

      FNonA(G.List, "isEmpty", Nil, "Boolean", listImg("isEmpty.svg"), List(infoT("状況")), "要素があればfalse, Nilならtrue."),
      FNonA(G.List, "nonEmpty", Nil, "Boolean", listImg("nonEmpty.svg"), List(infoT("状況")), "要素があればtrue, Nilならfalse."),
      FNonA(G.List, "length<br>(size)", Nil, "Int", listImg("length.svg"), List(infoT("状況")), "要素数."),
      F1A(G.List, "sizeCompare", Nil, T("otherSize", "Int"), "Boolean", listImg("sizeCompare.svg"), List(infoT("比較")), "要素数が引数と比較して、<br>多い => 1<br>等しい => 0<br>少ない => -1."),
      F1A(G.List, "sizeCompare", Nil, T("that", "Iterable[_]"), "Boolean", listImg("sizeCompare(I).svg"), List(infoT("比較")), "要素数がthatの要素数と比較して、<br>多い => 1<br>等しい => 0<br>少ない => -1."),


      F1A(G.List, "count", Nil, T("p", "A => Boolean"), "Boolean", listImg("count.svg"), List(infoT("件数")), "pの結果がtrueになる要素数."),
      F1A(G.List, "segmentLength", Nil, T("p", "A => Boolean"), "Boolean", listImg("segmentLength.svg"), List(infoT("件数")), "先頭からpの結果がfalseになるまでの数."),
      FNA(G.List, "segmentLength", Nil, List(T("p", "A => Boolean"), T("from", "Int")), "Boolean", listImg("segmentLength(from).svg"), List(infoT("件数")), "from番目からpの結果がfalseになるまでの数."),

      F1A(G.List, "foreach", List("U"), T("f", "A => U"), "Unit", listImg("foreach.svg"), List(infoT("作用")), "全ての値で順にfを実行."),
      F1A(G.List, "tapEach", List("U"), T("f", "A => U"), "List[A]", listImg("tapEach.svg"), List(infoT("作用")), "全ての値で順にfを実行し元値をreturn."),

      F1A(G.List, "map", List("B"), T("f", "A => B"), "List[B]", listImg("map.svg"), List(infoT("変換")), "全ての値をfで変換."),
      F1A(G.List, "mapConserve", List("B >: A <: AnyRef"), T("f", "A => B"), "List[B]", listImg("map.svg"), List(infoT("変換")), "全ての値をfで変換.<br>ただし変換後、全て同じ値の場合は元のListをそのまま返す."),
      FI1A(G.List, "flatten", List("B"), T("toIterableOnce", "A => IterableOnce[B]"), "List[B]", listImg("flatten.svg"), List(infoT("変換")), "List[IterableOnce[B]]をList[B]に変換.<br>図はList[List[B]]のイメージ.<br>IterableOnceは大まかにコレクション型やOption型等と捉えると良さそうです。"),
      F1A(G.List, "flatMap", List("B"), T("f", "A => IterableOnce[B]"), "List[B]", listImg("flatMap.svg"), List(infoT("変換"), infoT("モナド")), "全ての値をfで変換しflattenまで行います.<br>fはIterableOnce[B]を返す関数である必要があります.<br>図はList[List[B]]に変換されてflattenされるイメージ.<br>IterableOnceは大まかにコレクション型やOption型等と捉えると良さそうです"),
      FI1A(G.List, "transpose", List("B"), T("asIterable", "A => Iterable[B]"), "List[List[B]]", listImg("transpose.svg"), List(dangerT("例外"), infoT("変換")), "行列を入れ替える.<br>要素数に差があると例外."),

      F1A(G.List, "zip", List("B"), T("that", "IterableOnce[B]"), "List[(A, B)]", listImg("zip.svg"), List(infoT("結合")), "thatコレクションと同順に1つずつタプル化.<br>少ない側の要素数に揃う."),
      FNA(G.List, "zipAll", List("A1 <: A", "B"), List(T("that", "Iterable[B]"), T("thisElem", "A1"), T("thatElem", "B")), "List[(A1, B)]", listImg("zipAll.svg"), List(infoT("結合")), "thatコレクションと同順に1つずつタプル化.<br>thisが少ない場合はthisElem, thatが少ない場合はthatElemで補う."),
      F1A(G.List, "lazyZip", List("B"), T("that", "Iterable[B]"), "LazyZip2[(A, B, List[A])]", listImg("zip.svg"), List(infoT("結合"), infoT("遅延")), "thatコレクションと同順に1つずつタプル化.<br>少ない側の要素数に揃う.<br>LazyZip返却版."),
      FNonA(G.List, "zipWithIndex", Nil, "List[(A, Int)]", listImg("zipWithIndex.svg"), List(infoT("結合")), "Indexとタプル化."),
      FNonA(G.List, "indices", Nil, "List[Int]", listImg("indices.svg"), List(infoT("変換"), infoT("Index")), "全要素のIndexをListとして取得."),
      FI1A(G.List, "unzip", List("A1", "A2"), T("asPair", "A = (A1, A2)"), "(List[A1], List[A2])", listImg("unzip.svg"), List(infoT("分配")), "2要素タプルのListを分割した2つのListを取得."),
      FI1A(G.List, "unzip3", List("A1", "A2", "A3"), T("asTriple", "A = (A1, A2, A3)"), "(List[A1], List[A2], List[A3])", listImg("unzip3.svg"), List(infoT("分配")), "3要素タプルのListを分割した3つのListを取得."),

      FNonA(G.List, "reverse", Nil, "List[A]", listImg("reverse.svg"), List(infoT("並び替え")), "順番を反転する."),
      FI1A(G.List, "sorted", List("B >: A"), T("ord", "Ordering[B]"), "List[B]", listImg("sorted.svg"), List(infoT("並び替え")), "昇順に並び替える."),
      FunctionInfo(G.List, "sortBy", List("B >: A"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), true)),
        "List[B]", listImg("sortBy.svg"), List(infoT("並び替え")), "fで変換した値で昇順に並び替える."),
      F1A(G.List, "sortWith", Nil, T("lt", "(A, A) => Boolean"), "List[A]", listImg("sortWith.svg"), List(infoT("並び替え")), "隣り合う要素でfを実行した時に常にtrueになるように並び替える."),

      F1A(G.List, "reduce<br>(reduceLeft)", List("B >: A"), T("op", "(B, A) => B"), "B", listImg("reduce.svg"), List(dangerT("例外"), infoT("畳み込み")), "先頭から末尾までを順次、<br>opで演算した結果とその次の要素をopで演算する.<br>Nilの場合は例外."),
      F1A(G.List, "reduceOption<br>(reduceLeftOption)", List("B >: A"), T("op", "(B, A) => B"), "Option[B]", listImg("reduceOption.svg"), List(infoT("畳み込み")), "先頭から末尾までを順次、<br>opで演算した結果とその次の要素をopで演算する.<br>Nilの場合はNone."),
      F1A(G.List, "reduceRight", List("B >: A"), T("op", "(B, A) => B"), "B", listImg("reduceRight.svg"), List(dangerT("例外"), infoT("畳み込み"), warnT("List苦手")), "末尾から先頭までを順次、<br>opで演算した結果とその前の要素をopで演算する.<br>Nilの場合は例外."),
      F1A(G.List, "reduceRightOption", List("B >: A"), T("op", "(B, A) => B"), "Option[B]", listImg("reduceRightOption.svg"), List(infoT("畳み込み"), warnT("List苦手")), "末尾から先頭までを順次、<br>opで演算した結果とその前の要素をopで演算する.<br>Nilの場合はNone."),
      FunctionInfo(G.List, "fold<br>(foldLeft)", List("B >: A"),
        List(ArgsInfo(List(T("z", "B")), false),
          ArgsInfo(List(T("op", "(B, A) => B")), false)),
        "B", listImg("fold.svg"), List(infoT("畳み込み")), "先頭から末尾までを順次、<br>opで演算した結果とその次の要素をopで演算する.<br>ただし先頭値にはzを利用."),
      FunctionInfo(G.List, "foldRight", List("B >: A"),
        List(ArgsInfo(List(T("z", "B")), false),
          ArgsInfo(List(T("op", "(B, A) => B")), false)),
        "B", listImg("foldRight.svg"), List(infoT("畳み込み"), warnT("List苦手")), "末尾から先頭までを順次、<br>opで演算した結果とその次の要素をopで演算する.<br>ただし末尾値にはzを利用."),
      FI1A(G.List, "sum", List("B >: A"), T("num", "Numeric[B]"), "B", listImg("sum.svg"), List(infoT("畳み込み")), "合計値.<br>Nilの場合は0."),
      FI1A(G.List, "product", List("B >: A"), T("num", "Numeric[B]"), "B", listImg("product.svg"), List(infoT("畳み込み")), "全て要素の乗算値.<br>Nilの場合は1."),


      FunctionInfo(G.List, "scan<br>(scanLeft)", List("B >: A"),
        List(ArgsInfo(List(T("z", "B")), false),
          ArgsInfo(List(T("op", "(B, A) => B")), false)),
        "List[B]", listImg("scan.svg"), List(infoT("畳み込み")), "fold(foldLeft)の計算過程をListとして取得."),
      FunctionInfo(G.List, "scanRight", List("B >: A"),
        List(ArgsInfo(List(T("z", "B")), false),
          ArgsInfo(List(T("op", "(B, A) => B")), false)),
        "List[B]", listImg("scanRight.svg"), List(infoT("畳み込み"), warnT("List苦手")), "foldRightの計算過程をListとして取得."),

      FNonA(G.List, "mkString", Nil, "String", listImg("mkString.svg"), List(infoT("文字列作成")), "全要素を(toStringした結果を)結合した文字列."),
      F1A(G.List, "mkString", Nil, T("sep", "String"), "String", listImg("mkString(1).svg"), List(infoT("文字列作成")), "全要素を(toStringした結果を)seqで区切り結合した文字列."),
      FNA(G.List, "mkString", Nil, List(T("start", "String"), T("sep", "String"), T("end", "String")), "String", listImg("mkString(3).svg"), List(infoT("文字列作成")), "全要素を(toStringした結果を)seqで区切り結合した文字列から、先頭にstart 末尾にendを付与したもの."),

      F1A(G.List, "isDefinedAt", Nil, T("x", "Int"), "Boolean", listImg("isDefinedAt.svg"), List(infoT("存在")), "n番目の要素があればtrue."),
      F1A(G.List, "contains", List("A1 >: A"), T("elem", "A1"), "Boolean", listImg("contains.svg"), List(infoT("存在")), "elemが存在すればtrue."),
      F1A(G.List, "containsSlice", List("B >: A"), T("that", "Seq[B]"), "Boolean", listImg("containsSlice.svg"), List(infoT("存在")), "thatが存在すればtrue."),
      F1A(G.List, "endsWith", List("B >: A"), T("that", "Iterable[B]"), "Boolean", listImg("endsWith.svg"), List(infoT("存在"), warnT("List苦手")), "thatが末尾と一致すればtrue."),
      F1A(G.List, "exists", Nil, T("p", "A => Boolean"), "Boolean", listImg("exists.svg"), List(infoT("存在")), "pがtrueになる要素が存在すればtrue."),
      F1A(G.List, "forall", Nil, T("p", "A => Boolean"), "Boolean", listImg("forall.svg"), List(infoT("全要素確認")), "pがtrueになる要素のみであればtrue.<br>Nilの場合はtrue."),

      FI1A(G.List, "min", List("B >: A"), T("ord", "Ordering[B]"), "B", listImg("min.svg"), List(dangerT("例外"), infoT("最小値"), infoT("1つ取得")), "最小値.<br>Nilの場合は例外."),
      FI1A(G.List, "minOption", List("B >: A"), T("ord", "Ordering[B]"), "Option[B]", listImg("minOption.svg"), List(infoT("最小値"), infoT("1つ取得")), "最小値.<br>Nilの場合はNone."),
      FunctionInfo(G.List, "minBy", List("B"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), false)),
        "B", listImg("minBy.svg"), List(dangerT("例外"), infoT("最小値"), infoT("1つ取得")), "最小値.<br>Nilの場合は例外."),
      FunctionInfo(G.List, "minByOption", List("B"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), false)),
        "Option[B]", listImg("minByOption.svg"), List(infoT("最小値"), infoT("1つ取得")), "最小値.<br>Nilの場合はNone."),

      FI1A(G.List, "max", List("B >: A"), T("ord", "Ordering[B]"), "B", listImg("max.svg"), List(dangerT("例外"), infoT("最大値"), infoT("1つ取得")), "最大値.<br>Nilの場合は例外."),
      FI1A(G.List, "maxOption", List("B >: A"), T("ord", "Ordering[B]"), "Option[B]", listImg("maxOption.svg"), List(infoT("最大値"), infoT("1つ取得")), "最大値.<br>Nilの場合はNone."),
      FunctionInfo(G.List, "maxBy", List("B"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), false)),
        "B", listImg("maxBy.svg"), List(dangerT("例外"), infoT("最大値"), infoT("1つ取得")), "最大値.<br>Nilの場合は例外."),
      FunctionInfo(G.List, "maxByOption", List("B"),
        List(ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("ord", "Ordering[B]")), false)),
        "Option[B]", listImg("maxByOption.svg"), List(infoT("最大値"), infoT("1つ取得")), "最大値.<br>Nilの場合はNone."),

      F1A(G.List, "grouped", Nil, T("size", "Int"), "Iterator[A]", listImg("grouped.svg"), List(infoT("グループ化")), "size個ずつに分ける.<br>最後は端数になりえる."),
      F1A(G.List, "groupBy", List("K"), T("f", "A => K"), "Map[K, List[A]]", listImg("groupBy.svg"), List(infoT("グループ化")), "fを適用した結果をキーとしたMapを作成.<br>同じキーになる要素を集めたList値を保持."),
      FunctionInfo(G.List, "groupMap", List("K", "B"),
        List(ArgsInfo(List(T("key", "A => K")), false),
          ArgsInfo(List(T("f", "A => B")), false)),
        "Map[K, List[B]]", listImg("groupMap.svg"), List(infoT("グループ化")), "groupByの結果の値(Mapのvalue)にfを適用したものを返す."),
      FunctionInfo(G.List, "groupMapReduce", List("K", "B"),
        List(ArgsInfo(List(T("key", "A => K")), false),
          ArgsInfo(List(T("f", "A => B")), false),
          ArgsInfo(List(T("reduce", "(B, B) => B")), false)
        ),
        "Map[K, B]", listImg("groupMapReduce.svg"), List(infoT("グループ化")), "groupMapの結果の値(Mapのvalue)をreduceしたものを返す."),

      FNA(G.List, "updated", List("B >: A"), List(T("index", "Int"), T("elem", "B")), "List[B]", listImg("updated.svg"), List(infoT("変更")), "index番目をelemに変更したList."),
      FNA(G.List, "patch", List("B >: A"), List(T("from", "Int"), T("other", "IterableOnce[B]"), T("replaced", "Int")), "List[B]", listImg("patch.svg"), List(infoT("変更")), "from番目からreplaced個要素を削除し、代わりにotherを追加したList."),
      FNA(G.List, "padTo", List("B >: A"), List(T("len", "Int"), T("elem", "B")), "List[B]", listImg("padTo.svg"), List(infoT("変更")), "lenの要素数になるまでelemを追加したList.<br>最初からlen以上の要素があれば変化無し."),

      // TODO?
      // sameElements
      // withFilter
      // reverse_:::
      // sizeIs
      // etc?
    )

    def opImg(s: String) = s"${Render.imgPath}/functions/option/$s"
    
    val forOption = List(
      FNonA(G.Option, "get", Nil, "A", opImg("get.svg"), List(infoT("値取得"), dangerT("例外")), "Someの中身を取得.<br>Noneの場合は例外."),
      F1A(G.Option, "getOrElse", List("B >: A"), T("default", "=> B"),  "B", opImg("getOrElse.svg"), List(infoT("値取得")), "Someの中身を取得.<br>Noneの場合はdefault."),
      F1A(G.Option, "orElse", List("B >: A"), T("alternative", "=> Option[B]"),  "Option[B]", opImg("orElse.svg"), List(infoT("値取得")), "Someの場合は変化なし.<br>Noneの場合はalternative."),
      FunctionInfo(G.Option, "fold", List("B"),
        List(ArgsInfo(List(T("ifEmpty", "=> B")), false),
          ArgsInfo(List(T("f", "A => B")), false)),
        "B", opImg("fold.svg"), List(infoT("畳み込み")), "Noneの場合はifEmpty.<br>Someの場合はfで変換した値."),
      
      FNonA(G.Option, "nonEmpty<br>(isDefined)", Nil, "Boolean", opImg("nonEmpty.svg"), List(infoT("状況")), "Someならtrue.<br>Noneならfalse."),
      FNonA(G.Option, "isEmpty", Nil, "Boolean", opImg("isEmpty.svg"), List(infoT("状況")), "Someならfalse.<br>Noneならtrue."),

      F1A(G.Option, "contains", List("A1 >: A"), T("elem", "A1"), "Boolean", opImg("contains.svg"), List(infoT("存在")), "Someであり中身がelemと一致すればtrue.<br>それ以外はfalse."),
      F1A(G.Option, "exists", Nil, T("p", "A => Boolean"), "Boolean", opImg("exists.svg"), List(infoT("存在")), "Someであり中身にpを適用した結果がtrueになればtrue.<br>それ以外はfalse."),

      F1A(G.Option, "filter", Nil, T("p", "A => Boolean"), "Option[A]", opImg("filter.svg"), List(infoT("絞り込み")), "Someであり中身にpを適用した結果がtrueであれば変化なし.<br>それ以外はNone."),
      F1A(G.Option, "filterNot", Nil, T("p", "A => Boolean"), "Option[A]", opImg("filterNot.svg"), List(infoT("絞り込み")), "Someであり中身にpを適用した結果がfalseであれば変化なし.<br>それ以外はNone."),
      F1A(G.Option, "collect", List("B"), T("pf", "PartialFunction[A, B]"), "Option[B]", opImg("collect.svg"), List(infoT("絞り込み"), infoT("変換")), "Someであり中身がpfでマッチする場合は値が変換されたSome値.<br>それ以外はNone."),

      F1A(G.Option, "foreach", List("U"), T("f", "A => U"), "Unit", opImg("foreach.svg"), List(infoT("作用")), "Someなら中身でfを実行."),
      F1A(G.Option, "map", List("B"), T("f", "A => B"), "Option[B]", opImg("map.svg"), List(infoT("変換")), "Someなら中身をfで変換."),
      FI1A(G.Option, "flatten", List("B"), T("ev", "A <:< Option[B]"), "Option[B]", opImg("flatten.svg"), List(infoT("変換")), "Option[Option[B]]をOption[B]に変換.<br>内外共にSomeならSome、それ以外はNone."),
      F1A(G.Option, "flatMap", List("B"), T("f", "A => Option[B]"), "Option[B]", opImg("flatMap.svg"), List(infoT("変換"), infoT("モナド")), "fでmapした後にflatten.<br>fはOption[B]を返す関数."),
      
      F1A(G.Option, "toRight", List("X"), T("left", " => X"), "Either[X, A]", opImg("toRight.svg"), List(infoT("Either変換")), "Someならそのまま値を引き継いだRightに.<br>Noneであれば引数leftを含むLeftに."),
      F1A(G.Option, "toLeft", List("X"), T("right", "=> X"), "Either[A, X]", opImg("toLeft.svg"), List(infoT("Either変換")), "Someならそのまま値を引き継いだLeftに.<br>Noneであれば引数rightを含むRightに."),
    )

    def etImg(s: String) = s"${Render.imgPath}/functions/either/$s"

    val forEither = List(
      F1A(G.Either, "getOrElse", List("R1 >: R"), T("or", "=> R1"), "R1", etImg("getOrElse.svg"), List(infoT("値取得")), "Rightの中身を取得.<br>Leftの場合はor."),
      F1A(G.Either, "orElse", List("L1 >: L", "R1 >: R"), T("or", "=> Either[L1, R1]"), "Either[L1, R1]", etImg("orElse.svg"), List(infoT("Left変換")), "Rightの場合は変化なし.<br>Leftの場合はor."),
      FNA(G.Either, "fold", List("C"),List(T("fa", "L => C"), T("fb", "R => C")), "C", etImg("fold.svg"), List(infoT("畳み込み")), "Rightの場合は中身にfbを適用した値.<br>Leftの場合は中身をfaで変換した値."),
      
      FNonA(G.Either, "merge", Nil, "L | R", etImg("merge.svg"), List(infoT("値取得")), "RightでもLeftでも中身の値を取得."),
      FNonA(G.Either, "swap", Nil, "Either[R, L]", etImg("swap.svg"), List(infoT("型変換")), "RightならLeftに, LeftならRightにする."),
      
      FNonA(G.Either, "isRight", Nil, "Boolean", etImg("isRight.svg"), List(infoT("状況")), "Rightならtrue.<br>Leftならfalse."),
      FNonA(G.Either, "isLeft", Nil, "Boolean", etImg("isLeft.svg"), List(infoT("状況")), "Rightならfalse.<br>Leftならtrue."),
      
      F1A(G.Either, "contains", List("R1 >: R"), T("elem", "R1"), "Boolean", etImg("contains.svg"), List(infoT("存在")), "Rightであり中身がelemと一致すればtrue.<br>それ以外はfalse."),
      F1A(G.Either, "exists", Nil, T("p", "R => Boolean"), "Boolean", etImg("exists.svg"), List(infoT("存在")), "Rightであり中身にpを適用した結果がtrueになればtrue.<br>それ以外はfalse."),
      
      FNA(G.Either, "filterOrElse", List("L1 >: L"), List(T("p", "A => Boolean"), T("zero", "=> L1")), "Either[R, L1]", etImg("filterOrElse.svg"), List(infoT("フィルタ")), "Rightであり中身にpを適用した結果がtrueであれば変化なし.<br>Rightであり中身にpを適用した結果がfalseであればLeftにzeroを入れたもの.<br>Leftであれば変化なし."),
      
      F1A(G.Either, "foreach", List("U"), T("f", "R => U"), "Unit", etImg("foreach.svg"), List(infoT("作用")), "Rightなら中身でfを実行."),
      F1A(G.Either, "map", List("R1"), T("f", "R => R1"), "Either[L, R1]", etImg("map.svg"), List(infoT("変換")), "Rightなら中身をfで変換.<br>Leftは変化なし."),
      FI1A(G.Either, "flatten", List("L1 <: L", "R1"), T("ev", "R <:< Either[L1, R1]"), "Either[L1, R1]", etImg("flatten.svg"), List(infoT("変換")), "Either[L, Either[L1, R1]]をEither[L1, R1]に変換.<br>内外共にRightならRightに.<br>外Right, 内LeftならLeftに.<br>外Leftなら変化無し."),
      F1A(G.Either, "flatMap", List("L1 <: L", "R1"), T("f", "R => Either[L1, R1]"), "Either[L1, R1]", etImg("flatMap.svg"), List(infoT("変換"), infoT("モナド")), "fでmapした後にflatten.<br>fはEither[L1, R1]を返す関数."),
      
      FI1A(G.Either, "joinRight", List("L1 <: L", "R1 <: R", "C"),  T("ev", "R1 <:< Either[L1, C]"), "Either[L1, C]", etImg("joinRight.svg"), List(infoT("変換")), "≒ flatten."),
      FI1A(G.Either, "joinLeft", List("L1 <: L", "R1 <: R", "C"),  T("ev", "L1 <:< Either[C, R1]"), "Either[C, R1]", etImg("joinLeft.svg"), List(infoT("変換")), "Left側をflattenするような形.<br>Rightの時は変化無し."),
      
      FNonA(G.Either, "toOption", Nil, "Option[R]", etImg("toOption.svg"), List(infoT("Option変換")), "Rightならそのまま値を引き継いだSomeに.<br>LeftであればNoneに."),
      FI1A(G.Either, "toTry", Nil, T("ev", "L <:< Throwable"), "Try[R]", etImg("toTry.svg"), List(infoT("Try変換")), "Rightならそのまま値を引き継いだSuccessに.<br>Leftであればそのまま値を引き継いだFailureに.<br>Leftの中身はThrowableである必要があります."),
    )
    
    forList ::: forOption ::: forEither
  }
}
