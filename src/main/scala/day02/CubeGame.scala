package day02

import scala.util.Try
import scala.util.matching.Regex

case class CubeGame(game: List[String]) {
  private val numberPattern: Regex = "\\d+".r
  private val extractColor: String => Int = color => {
    val filteredGame: String = Try {
      game.find(line => line.contains(color)).toString
    }.getOrElse("0")
    val cubeNumber: Option[String] = numberPattern.findFirstIn(filteredGame)
    val result = cubeNumber match {
      case Some(value) => value.toInt
      case None => 0
    }
    result
  }

  val redCubes: Int = extractColor("red")
  val greenCubes: Int = extractColor("green")
  val blueCubes: Int = extractColor("blue")

  override def toString: String = s"Red cubes: $redCubes, Green cubes: $greenCubes, Blue cubes: $blueCubes"
}