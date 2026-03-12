package renders

import models.*

object Render {

  lazy val basePath = "/scala-cheat-sheet"
  lazy val imgPath = s"$basePath/img"

  def all(fis: List[FunctionInfo]): String = {
    val functionTable = renderFunctionsTable(fis)
    // language=html
    s"""
       |<h1>Scala Cheat Sheet</h1>
       |<h2 id="functions">Functions</h2>
       |<h3 id="functions-list">List[A]</h3>
       |$functionTable
       |""".stripMargin
  }
  
  def renderFunctionsTable(fis: List[FunctionInfo]): String = {
    val rows = fis.map(renderFunctionRow).mkString("")
    // language=html
    s"""
       |<table>
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

  def renderFunctionRow(fi: FunctionInfo): String = {
    // language=html
    s"""
       |<tr>
       |  <td class="name">${fi.name}</td>
       |  <td class="typArgs">${renderTypArgs(fi.typArgs)}</td>
       |  <td class="args">${renderArgs(fi.args)}</td>
       |  <td class="return">${fi.rtn}</td>
       |  <td class="image">${renderImg(fi.image)}</td>
       |  <td class="description">${fi.description}</td>
       |  <td class="tags">${renderTags(fi.tags)}</td>
       |</tr>
       |""".stripMargin
  }
  
  def renderTypArgs(ts: List[String]): String = {
    if (ts.isEmpty) ""
    else ts.mkString("[", ", ", "]")
  }
  
  def renderArgs(tis: List[TypeInfo]): String = {
    tis.map(ti => s"${ti.name}: ${ti.typ}").mkString("(", ", ", ")")
  }

  def renderImg(src: String): String = {
    // language=html
    s"""<img alt="$src" src="$src"/>"""
  }

  def renderTags(ts: List[Tag]): String = {
    // language=html
    ts.map(t => s"""<span class="tag ${t.mode}">${t.name}</span>""").mkString("<div>", "", "</div>")
  }

}
