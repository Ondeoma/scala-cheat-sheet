package functions

import org.scalajs.dom.{HTMLInputElement, HTMLSelectElement, HTMLTableCellElement, HTMLTableColElement, document}
import renders.Render

object Filter {

  def filterRow(searchAreaSelector: String,
                tableSelector: String): Unit = {
    val searchArea = document.querySelector(searchAreaSelector)
    val searchText = searchArea.querySelector(".text") match {
      case e: HTMLInputElement => e.value
      case _ => ""
    }
    val searchRtn = searchArea.querySelector(".rtn") match {
      case e: HTMLSelectElement => e.value
      case _ => ""
    }
    val searchTags = searchArea.querySelectorAll(".tag").collect {
      case e: HTMLInputElement if e.checked =>
        // 隣のラベル要素のテキストを取得
        e.nextElementSibling.textContent
    }

    val table = document.querySelector(tableSelector)
    table.querySelectorAll("tbody tr").foreach { tr =>
      val text = tr.textContent
      val rtn = tr.querySelector(s".${Render.functionRtnCls}").textContent
      val tags = tr.querySelectorAll(s".${Render.functionTagCls}").map(_.textContent)
      val isMatch = { 
        (searchText.isEmpty || fuzzyMatch(text, searchText)) &&
          (searchRtn.isEmpty || rtn == searchRtn) &&
          (searchTags.isEmpty || searchTags.exists(tags.contains))
      }
      if (isMatch) tr.classList.remove("unmatched-row")
      else tr.classList.add("unmatched-row")
    }
  }

  def filterCol(selectAreaSelector: String,
                tableSelector: String): Unit = {
    val selectArea = document.querySelector(selectAreaSelector)
    println(selectArea)
    val checks = selectArea.querySelectorAll("input[type='checkbox']") collect {
      case e: HTMLInputElement => e
    }

    val table = document.querySelector(tableSelector)
    checks.foreach { ch =>
      ch.dataset.get("name").foreach { name =>
        table.querySelectorAll(s".$name").collect {
          case e: HTMLTableCellElement => e
        }.foreach { cell =>
          if (ch.checked) cell.classList.remove("unmatched-col")
          else cell.classList.add("unmatched-col")
        }
      }
    }
  }
  
  def normalize(s: String): String = {
    import utils.StringUtil.*
    hiraToKana(halfToFull(s.toLowerCase))
  }
  
  def fuzzyMatch(t: String, 
                 s: String): Boolean = {
    normalize(t).contains(normalize(s))
  }

}
