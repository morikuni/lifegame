package morikuni.lifegame

import util.Random

case class Stage(val width: Int, val height: Int, val livingCells: Set[Cell]){
  lazy val next: Stage = {
    val checkTargets = for{
      c1 <- livingCells
      c2 <- getAroundCells(c1)
    } yield c2

    val nextLivingCells = checkTargets.filter{ c =>
      val aroundLivengCellsCount = getAroundCells(c).filter(isLiving).size
      if(isLiving(c))
        aroundLivengCellsCount == 2 || aroundLivengCellsCount == 3
      else
        aroundLivengCellsCount == 3
    }

    copy(livingCells = nextLivingCells)
  }

  def putLivingCells(cells: Set[Cell]): Stage = copy(livingCells = livingCells ++ cells)
  def putLivingCell(cell: Cell): Stage = copy(livingCells = livingCells + cell)

  def getAroundCells(cell: Cell): Set[Cell] = {
    (for{
      y <- Range(cell.y-1, cell.y+2)
      if(y >= 0 && y < height)
      x <- Range(cell.x-1, cell.x+2)
      if(x >= 0 && x < width)
    } yield Cell(x, y)).toSet - cell
  }

  def isLiving(c: Cell): Boolean = livingCells.contains(c)
}

object Stage{
  def random(width: Int, height: Int): Stage = {
    val cells = for{
      y <- Range(0, height)
      x <- Range(0, width)
    } yield Cell(x, y)

    Stage(width, height, cells.filter(_ => Random.nextBoolean).toSet)
  }
}
