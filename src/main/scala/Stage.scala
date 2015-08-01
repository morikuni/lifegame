package morikuni.lifegame

import util.Random

case class Stage(val width: Int, val height: Int, val livingCells: Set[Point]){
	lazy val next: Stage = {
		val checkTargets = for{
			p1 <- livingCells
			p2 <- getAroundCells(p1)
		} yield p2

		val nextLivingCells = checkTargets.filter{ p =>
			val aroundLivengCellsCount = getAroundCells(p).filter(livingCells.contains).size
			if(isLiving(p))
				aroundLivengCellsCount == 2 || aroundLivengCellsCount == 3
			else
				aroundLivengCellsCount == 3
		}

		copy(livingCells = nextLivingCells)
	}

	def putLivingCells(points: Set[Point]): Stage = copy(livingCells = livingCells ++ points)
	def putLivingCell(point: Point): Stage = copy(livingCells = livingCells + point)

	def getAroundCells(point: Point): Set[Point] = {
		(for{
			y <- Range(point.y-1, point.y+2)
			if(y >= 0 && y < height)
			x <- Range(point.x-1, point.x+2)
			if(x >= 0 && x < width)
		} yield Point(x, y)).toSet - point
	}

	def isLiving(p: Point): Boolean = livingCells.contains(p)
}

object Stage{
	def random(width: Int, height: Int): Stage = {
		val points = for{
			y <- Range(0, height)
			x <- Range(0, width)
		} yield Point(x, y)

		Stage(width, height, points.filter(_ => Random.nextBoolean).toSet)
	}
}
