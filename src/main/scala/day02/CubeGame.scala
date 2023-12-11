package day02

import scala.util.matching.Regex

case class CubeGame(game: List[String]) {
  private val numberPattern: Regex = "\\d+".r
  private val extractColor: String => Int = color => {
    val splitGame: List[String] = game.flatMap(cubeColor => cubeColor.split(","))
    val resultOption = splitGame.find(line => line.contains(color))
    resultOption match {
      case Some(result) => numberPattern.findFirstIn(result).map(_.toInt).getOrElse(0)
      case None => 0
    }
  }

  val redCubes: Int = extractColor("red")
  val greenCubes: Int = extractColor("green")
  val blueCubes: Int = extractColor("blue")

  override def toString: String = s";;Red cubes: $redCubes, Green cubes: $greenCubes, Blue cubes: $blueCubes;;"
}