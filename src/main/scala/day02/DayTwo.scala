package day02

import scala.io.{BufferedSource, Source}
import scala.util.Try
import scala.util.matching.Regex
import CubeGameRules.checkGame

object DayTwo {
  def PartOne(textFile: Iterator[String]): Int = {
    val splitGames = textFile.map(line => line.split(":").toList)
      .map(line => line(1))
      .map(line => line.split(";").toList)
      .map {
        innerList => innerList.map(line => line.split(":").toList)
      }
//    println(splitGames.toList)

    val cubeGames: Iterator[(List[CubeGame], Int)] = splitGames.map {
      gameSet => gameSet.map(game => CubeGame(game))
      }.zipWithIndex.map {
      case (element, index) => (element, index + 1)
    }
    println(cubeGames.toList)
    val totalSum: Int = cubeGames.flatMap {
      case (gameSet, index) =>
        gameSet.map(game => if (CubeGameRules.checkGame(game)) index else 0)
    }.sum
    println(totalSum)
    totalSum
  }
  def main(args: Array[String]): Unit = {
    val filename: String = "./src/main/scala/day02/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val totalSum: Int = PartOne(textFile.getLines)

    textFile.close()
  }
}
