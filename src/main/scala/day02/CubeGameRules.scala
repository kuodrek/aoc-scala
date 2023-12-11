package day02

object CubeGameRules {
  private val redCubesLimit: Int = 12
  private val greenCubesLimit: Int = 13
  private val blueCubesLimit: Int = 14

  def checkGame(cubeGame: CubeGame): Boolean = {
    if (
      cubeGame.redCubes > redCubesLimit
      || cubeGame.greenCubes > greenCubesLimit
      || cubeGame.blueCubes > blueCubesLimit) false else true
  }
}
