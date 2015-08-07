package morikuni.lifegame

import org.scalajs.dom

class Drawer(val canvasWidth: Int, val canvasHeight: Int){
  def draw(ctx: dom.CanvasRenderingContext2D, stage: Stage): Unit = {
    val cellWidth = canvasWidth / stage.width
    val cellHeight = canvasHeight / stage.height

    val cells = for{
      y <- Range(0, stage.height)
      x <- Range(0, stage.width)
    } yield Cell(x, y)

    for(c <- cells){
      val canvasX = c.x * cellWidth
      val canvasY = c.y * cellHeight
      if(stage.livingCells.contains(c)) drawLivingCells(ctx, canvasX, canvasY, cellWidth, cellHeight)
      else drawDeadCells(ctx, canvasX, canvasY, cellWidth, cellHeight)
    }
  }

  def drawLivingCells(ctx: dom.CanvasRenderingContext2D, x: Int, y: Int, w: Int, h: Int): Unit = {
    ctx.fillStyle = "rgb(0, 255, 0)"
    ctx.fillRect(x, y, w, h)
  }


  def drawDeadCells(ctx: dom.CanvasRenderingContext2D, x: Int, y: Int, w: Int, h: Int): Unit = {
    ctx.fillStyle = "rgb(0, 0, 0)"
    ctx.fillRect(x, y, w, h)
  }
}
