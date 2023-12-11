package day02

import scala.io.{BufferedSource, Source}
import scala.util.Try
import scala.util.matching.Regex
import CubeGameRules.checkGame

object DayTwo {
  private def PartOne(textFile: Iterator[String]): Int = {
    val splitGames = textFile.map(line => line.split(":").toList)
      .map(line => line(1))
      .map(line => line.split(";").toList)
      .map {
        innerList => innerList.map(line => line.split(":").toList)
      }.toList
//    println(splitGames)

    val cubeGames: List[(List[CubeGame], Int)] = splitGames.map {
      gameSet => gameSet.map(game => CubeGame(game))
      }.zipWithIndex.map {
      case (element, index) => (element, index + 1)
    }
    println(cubeGames)
    val totalSum: Int = cubeGames.map {
      case (gameSet, index) =>
      val gameSetEval: List[Boolean] = gameSet.map(game => CubeGameRules.checkGame(game))
//      println(gameSetEval)
      if ( gameSetEval.forall(_ == true) ) index else 0
    }.sum
    totalSum
  }
  def main(args: Array[String]): Unit = {
    val filename: String = "./src/main/scala/day02/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val totalSum: Int = PartOne(textFile.getLines)
    println(totalSum)
    textFile.close()
  }
}
