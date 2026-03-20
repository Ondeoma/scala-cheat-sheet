package utils

object HtmlUtil {
  
  def escapeHtml(s: String): String = {

    def esc(c: Char): String = {
      c match {
        case '&' => "&amp;"
        case '<' => "&lt;"
        case '>' => "&gt;"
        case '"' => "&quot;"
        case '\'' => "&#39;"
        case _ => s"$c"
      }
    }
    
    s.flatMap(esc)
  }
}
