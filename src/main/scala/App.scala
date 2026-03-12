import org.scalajs.dom
import data.Data
import renders.Render

@main
def App(): Unit = {
  
  dom.document.querySelector("#app").innerHTML = {
    Render.all(Data.functions)
  } 
    
}
