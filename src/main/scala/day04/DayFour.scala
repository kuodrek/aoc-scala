package day04
import scala.annotation.tailrec
import scala.io.{BufferedSource, Source}
import scala.math.pow


case class ScratchGame(winningNumbers: List[Int], numbers: List[Int])

object DayFour {
  private def PartOne(gamesLabeled: List[ScratchGame]): Int = {
    val gamesMatched = gamesLabeled.map {
      scratchGame => scratchGame.numbers.intersect(scratchGame.winningNumbers).length
    }.filter(game => game > 0)
    gamesMatched.collect {
      case num: Int => pow(2,num-1).toInt
    }.sum
  }
  private def PartTwo(gamesLabeled: List[ScratchGame]): Int = {
    val gamesResults: Vector[Int] = gamesLabeled.map {
      scratchGame => scratchGame.numbers.intersect(scratchGame.winningNumbers).length
    }.toVector

    val result = gamesResults.zipWithIndex.foldLeft(Vector.fill(gamesLabeled.size)(1)) {
      case (acc , (game: Int, i: Int)) =>
      acc
    }.sum
    result
  }
  def main(args: Array[String]): Unit = {
    // Get data
    val filename: String = "./src/main/scala/day04/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val gamesString: Iterator[List[String]] = textFile.getLines.map(line => line.substring(line.indexOf(":")+1).split("\\|").toList)

    // Extract games from string and label into ScratchGame objects
    val extractor = """\d+""".r
    val extractNumbers: String => List[Int] = game => extractor.findAllMatchIn(game).map(_.matched.toInt).toList

    val gamesLabeled: List[ScratchGame] = gamesString.map {
      game => ScratchGame(extractNumbers(game.head), extractNumbers(game.last))
    }.toList

    val totalSumPartOne = PartOne(gamesLabeled)
    val totalSumPartTwo = PartTwo(gamesLabeled)
    println(totalSumPartOne)
    println(totalSumPartTwo)
  }
}