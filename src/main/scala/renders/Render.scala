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

  val functionRtnCls = "rtn"
  val functionTagCls = "tag"
  val anchorLinkCls = "anchor"

  val esc = HtmlUtil.escapeHtml

  def all(fis: List[FunctionInfo]): (String, List[() => Unit]) = {
    val listFunctions = fis.filter(_.group == FG.List)
    val listFunctionTable = renderFunctionsTable(FG.List, listFunctions)
    val listFunctionSearch = renderFunctionsFilterArea(FG.List, listFunctions)

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
         |<h3 id="functions-list">List[A]</h3>
         |$listFunctionSearch
         |$listFunctionTable
         |<div class="vh100"></div>
         |""".stripMargin

    val onLoadedFunctions: List[() => Unit] = List(
      () => addEventsFunctionsFilter(FG.List),
      addAnchorLinkEvents,
      goToAnchor
    )

    (html, onLoadedFunctions)
  }

  def getFunctionsFilterAreaCls(g: FG): String = {
    s"functions-$g-filter"
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
    val filter = () => Filter.filter(s".$cls", s".$tblCls")
    document.querySelector(s".$cls") match {
      case area: HTMLElement =>
        area.querySelector(s".$filterTextCls").addEventListener("input", _ => filter())
        area.querySelector(s".$filterRtnCls").addEventListener("input", _ => filter())
        area.querySelectorAll(s".$filterTagCls")
          .foreach(_.addEventListener("input", _ => filter()))
      case _ => ()
    }
  }

  def renderFunctionsTable(g: FG,
                           fis: List[FunctionInfo]): String = {
    val cls = getFunctionsTableCls(g)
    val rows = fis.map(fi => renderFunctionRow(g, fi, fis)).mkString("")
    // language=html
    s"""
       |<table class="functions-table $cls">
       |  <thead>
       |    <tr>
       |      <th>名前</th>
       |      <th>型引数</th>
       |      <th>引数</th>
       |      <th>戻値</th>
       |      <th>図</th>
       |      <th>説明</th>
       |      <th>キーワード</th>
       |    </tr>
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
       |  <td id="$id" class="name">${fi.name}<a class="$anchorLinkCls" href="#$id">⚓</a></td>
       |  <td class="typArgs">${renderTypArgs(fi.typArgs)}</td>
       |  <td class="args">${renderArgs(fi.args)}</td>
       |  <td class="$functionRtnCls">${fi.rtn}</td>
       |  <td class="image">${renderImg(fi.image)}</td>
       |  <td class="description">${fi.description}</td>
       |  <td class="tags">${renderTags(fi.tags)}</td>
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
