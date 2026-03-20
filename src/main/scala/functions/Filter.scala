package functions

import org.scalajs.dom.{HTMLInputElement, HTMLSelectElement, document}
import renders.Render

object Filter {

  def filter(searchAreaSelector: String,
             tableSelect: String): Unit = {
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

    val table = document.querySelector(tableSelect)
    table.querySelectorAll("tbody tr").foreach { tr =>
      val text = tr.textContent
      val rtn = tr.querySelector(s".${Render.functionRtnCls}").textContent
      val tags = tr.querySelectorAll(s".${Render.functionTagCls}").map(_.textContent)
      val isMatch = { 
        (searchText.isEmpty || fuzzyMatch(text, searchText)) &&
          (searchRtn.isEmpty || rtn == searchRtn) &&
          (searchTags.isEmpty || searchTags.exists(tags.contains))
      }
      if (isMatch) tr.classList.remove("unmatched")
      else tr.classList.add("unmatched")
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
