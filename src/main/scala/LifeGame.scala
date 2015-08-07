import scalajs.js.annotation.JSExport
import scalajs.js.Dynamic.{global => g}
import org.scalajs.dom
import dom.document

import morikuni.lifegame._

@JSExport
object LifeGame{

  @JSExport
  def main(width: Int, height: Int): Unit = {
    type Ctx2D = dom.CanvasRenderingContext2D
    val canvas = document.getElementById("canvas").asInstanceOf[dom.html.Canvas]
    val ctx = canvas.getContext("2d").asInstanceOf[Ctx2D]
    var stage = Stage.random(width, height)
    val drawer = new Drawer(canvas.width, canvas.height)

    g.setInterval(() => {
      drawer.draw(ctx, stage)
      stage = stage.next
    }, 500)
  }
}


