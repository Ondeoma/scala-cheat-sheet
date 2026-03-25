package renders

import enums.FunctionGroup as FG
import functions.Filter
import models.*
import org.scalajs.dom.{HTMLElement, document, window}
import utils.HtmlUtil

object Render {

  lazy val basePath = "/scala-cheat-sheet"
  lazy val imgPath = s"$basePath/img"

  val filterTextCls = "text"
  val filterRtnCls = "rtn"
  val filterTagCls = "tag"

  val functionNameCls = "name"
  val functionTypArgsCls = "typArgs"
  val functionArgsCls = "args"
  val functionRtnCls = "rtn"
  val functionImageCls = "image"
  val functionDescriptionCls = "description"
  val functionTagsCls = "tags"
  val functionTagCls = "tag"

  val functionTableCols = List(
    "名前" -> functionNameCls,
    "型引数" -> functionTypArgsCls,
    "引数" -> functionArgsCls,
    "戻値" -> functionRtnCls,
    "図" -> functionImageCls,
    "説明" -> functionDescriptionCls,
    "キーワード" -> functionTagsCls)

  val anchorLinkCls = "anchor"

  val esc = HtmlUtil.escapeHtml


  def all(fis: List[FunctionInfo]): (String, List[() => Unit]) = {
    val listFs = fis.filter(_.group == FG.List)
    val listFT = renderFunctionsTable(FG.List, listFs)
    val listFS = renderFunctionsFilterArea(FG.List, listFs)
    val listSC = renderFunctionsSelectColArea(FG.List)

    val opFs = fis.filter(_.group == FG.Option)
    val opFT = renderFunctionsTable(FG.Option, opFs)
    val opFS = renderFunctionsFilterArea(FG.Option, opFs)
    val opSC = renderFunctionsSelectColArea(FG.Option)

    val etFs = fis.filter(_.group == FG.Either)
    val etFT = renderFunctionsTable(FG.Either, etFs)
    val etFS = renderFunctionsFilterArea(FG.Either, etFs)
    val etSC = renderFunctionsSelectColArea(FG.Either)

    // language=html
    val html =
      s"""
         |<h1>Scala Cheat Sheet</h1>
         |<h2 id="functions">See also</h2>
         |<ul>
         |  <li>
         |    <a href="https://docs.scala-lang.org/ja/cheatsheets/index.html">Scala Cheatsheet | Scala Documentation</a>
         |  </li>
         |</ul>
         |<h2 id="functions">Functions</h2>
         |
         |<div class="functions-toc">
         |<h3>TOC</h3>
         |<ul>
         |<li><a href="#functions-list">List</a></li>
         |<li><a href="#functions-option">Option</a></li>
         |<li><a href="#functions-either">Either</a></li>
         |</ul>
         |</div>
         |
         |<h3 id="functions-list">List[A]</h3>
         |$listFS
         |$listSC
         |$listFT
         |<h3 id="functions-option">Option[A]</h3>
         |$opFS
         |$opSC
         |$opFT
         |<h3 id="functions-either">Either[L, R]</h3>
         |$etFS
         |$etSC
         |$etFT
         |<div class="vh100minus200px"></div>
         |""".stripMargin

    val onLoadedFunctions: List[() => Unit] = List(
      () => addEventsFunctionsFilter(FG.List),
      () => addEventsFunctionsSelectCol(FG.List),
      () => addEventsFunctionsFilter(FG.Option),
      () => addEventsFunctionsSelectCol(FG.Option),
      () => addEventsFunctionsFilter(FG.Either),
      () => addEventsFunctionsSelectCol(FG.Either),
      addAnchorLinkEvents,
      goToAnchor
    )

    (html, onLoadedFunctions)
  }

  def getFunctionsFilterAreaCls(g: FG): String = {
    s"functions-$g-filter"
  }

  def getFunctionsSelectColAreaCls(g: FG): String = {
    s"functions-$g-select-col"
  }

  def getFunctionsTableCls(g: FG): String = {
    s"functions-$g"
  }

  def renderFunctionsFilterArea(g: FG,
                                fis: List[FunctionInfo]): String = {
    val cls = getFunctionsFilterAreaCls(g)
    val rtns = fis.map(_.rtn).distinct.sorted
    val tags = fis.flatMap(_.tags.map(_.name)).distinct.sorted
    val rtnSelects = rtns.map { r =>
      s"""<option value="${esc(r)}">${esc(r)}</option>"""
    }.mkString
    val tagChecks = tags.zipWithIndex.map { (t, i) =>
      val uid = s"""$g-kwd-sa-$i"""
      s"""<span><input id="$uid" type="checkbox" class="$filterTagCls" /><label for="$uid">${esc(t)}</label></span>"""
    }.mkString
    // language=html
    s"""
       |<table class="filter $cls">
       |  <tr>
       |    <th>曖昧検索</td>
       |    <td><input class="$filterTextCls" type="text"></td>
       |  </tr>
       |  <tr>
       |    <th>戻り値型</td>
       |    <td>
       |      <select class="$filterRtnCls">
       |        <option value=""></option>
       |        $rtnSelects
       |      </select>
       |    </td>
       |  </tr>
       |  <tr>
       |    <th>キーワード</td>
       |    <td>$tagChecks</td>
       |  </tr>
       |</table>
       |""".stripMargin
  }

  def addEventsFunctionsFilter(g: FG): Unit = {
    val cls = getFunctionsFilterAreaCls(g)
    val tblCls = getFunctionsTableCls(g)
    val filter = () => Filter.filterRow(s".$cls", s".$tblCls")
    document.querySelector(s".$cls") match {
      case area: HTMLElement =>
        area.querySelector(s".$filterTextCls").addEventListener("input", _ => filter())
        area.querySelector(s".$filterRtnCls").addEventListener("input", _ => filter())
        area.querySelectorAll(s".$filterTagCls")
          .foreach(_.addEventListener("input", _ => filter()))
      case _ => ()
    }
  }

  def renderFunctionsSelectColArea(g: FG): String = {
    val cls = getFunctionsSelectColAreaCls(g)
    val checks = functionTableCols.map { (name, cls) =>
      val uid = s"""$g-select-col-$cls"""
      s"""<span><input id="$uid" type="checkbox" data-name="$cls" checked /><label for="$uid">$name</label></span>"""
    }.mkString
    // language=html
    s"""
       |<table class="select-col $cls">
       |  <tr>
       |    <th>表示項目</td>
       |    <td>
       |      $checks
       |    </td>
       |  </tr>
       |</table>
       |""".stripMargin
  }

  def addEventsFunctionsSelectCol(g: FG): Unit = {
    val cls = getFunctionsSelectColAreaCls(g)
    val tblCls = getFunctionsTableCls(g)
    val filter = () => Filter.filterCol(s".$cls", s".$tblCls")
    document.querySelector(s".$cls") match {
      case area: HTMLElement =>
        area.querySelectorAll(s"input[type='checkbox']") 
          .foreach { ch => ch.addEventListener("input", _ => filter()) }
      case _ => ()
    }
  }

  def renderFunctionsTable(g: FG,
                           fis: List[FunctionInfo]): String = {
    val cls = getFunctionsTableCls(g)
    val rows = fis.map(fi => renderFunctionRow(g, fi, fis)).mkString("")
    val ths = functionTableCols.map { (name, cls) => s"""<th class="$cls">$name</th>""" }.mkString("")
    // language=html
    s"""
       |<table class="functions-table $cls">
       |  <thead>
       |    <tr>$ths</tr>
       |  </thead>
       |  <tbody>
       |    $rows
       |  </tbody>
       |</table>
       |""".stripMargin
  }

  def renderFunctionRow(g: FG,
                        fi: FunctionInfo,
                        allFi: List[FunctionInfo]): String = {
    val sameNames = allFi.filter(_.name == fi.name)
    val idx = sameNames.indexOf(fi)
    val id = s"function-${g}-${esc(fi.name)}-${idx}"
    // language=html
    s"""
       |<tr>
       |  <td id="$id" class="$functionNameCls">${fi.name}<a class="$anchorLinkCls" href="#$id">⚓</a></td>
       |  <td class="$functionTypArgsCls">${renderTypArgs(fi.typArgs)}</td>
       |  <td class="$functionArgsCls">${renderArgs(fi.args)}</td>
       |  <td class="$functionRtnCls">${fi.rtn}</td>
       |  <td class="$functionImageCls">${renderImg(fi.image)}</td>
       |  <td class="$functionDescriptionCls">${fi.description}</td>
       |  <td class="$functionTagsCls">${renderTags(fi.tags)}</td>
       |</tr>
       |""".stripMargin
  }

  def renderTypArgs(ts: List[String]): String = {
    if (ts.isEmpty) ""
    else ts.map(esc).mkString("[", ", ", "]")
  }

  def renderArgs(tis: List[ArgsInfo]): String = {
    tis.map { a =>
      val tis = a.tis.map(ti => s"${esc(ti.name)}: ${esc(ti.typ)}")
      val prefix = if (a.`implicit`) s"""<span class="args implicit">(implicit<br>""" else """<span class="args">("""
      tis.mkString(prefix, ",<br>", ")</span>")
    }.mkString("<br>")
  }

  def renderImg(src: String): String = {
    // language=html
    s"""<img alt="$src" src="$src" loading="lazy"/>"""
  }

  def renderTags(ts: List[Tag]): String = {
    // language=html
    ts.map(t => s"""<span class="$functionTagCls ${esc(t.mode)}">${esc(t.name)}</span>""").mkString("<div>", "", "</div>")
  }

  def addAnchorLinkEvents(): Unit = {
    document.querySelectorAll(s".$anchorLinkCls").foreach { a =>
      a.addEventListener("click", _ => {
        window.setTimeout(() => {
          val url = document.location.href
          window.navigator.clipboard.writeText(url)
        }, 100)
      })
    }
  }

  def goToAnchor(): Unit = {
    document.location.hash = document.location.hash
  }

}
