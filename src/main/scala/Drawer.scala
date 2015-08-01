package morikuni.lifegame

import org.scalajs.dom

class Drawer(val canvasWidth: Int, val canvasHeight: Int){
	def draw(ctx: dom.CanvasRenderingContext2D, stage: Stage): Unit = {
		val cellWidth = canvasWidth / stage.width
		val cellHeight = canvasHeight / stage.height

		val points = for{
			y <- Range(0, stage.height)
			x <- Range(0, stage.width)
		} yield Point(x, y)

		for(p <- points){
			val canvasX = p.x * cellWidth
			val canvasY = p.y * cellHeight
			if(stage.livingCells.contains(p)) drawLivingCells(ctx, canvasX, canvasY, cellWidth, cellHeight)
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
