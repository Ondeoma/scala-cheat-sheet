import org.scalajs.dom
import data.Data
import renders.Render

@main
def App(): Unit = {
  val (html, fs) = Render.all(Data.functions)
  dom.document.querySelector("#app").innerHTML = html
  fs.foreach(_())
}
