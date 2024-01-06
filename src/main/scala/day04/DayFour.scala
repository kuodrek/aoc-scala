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
    val gamesMatched: Vector[Int] = gamesLabeled.map {
      scratchGame => scratchGame.numbers.intersect(scratchGame.winningNumbers).length
    }.filter(game => game > 0).toVector

    @tailrec
    def processNextCards(index: Int, maxDepth: Int, acc: Int): Int = {
      val nextIndex = index + 1
      println(s"nextWinCount (${index+1}): ${gamesLabeled(index).numbers.intersect(gamesLabeled(index).winningNumbers).length}")
      if (nextIndex <= maxDepth) processNextCards(nextIndex, maxDepth, acc+1)
      else acc+1

    }
    val result = gamesLabeled.zipWithIndex.foldLeft(Vector.fill(gamesLabeled.size)(1)) {
      case (acc: Int, (game: ScratchGame, i: Int)) =>
      val thisWinCount = game.numbers.intersect(game.winningNumbers).length
      println(s"THISWINCOUNT (${i+1}): ${thisWinCount}")
      val nextWinCount = if (i+1 < gamesLabeled.length) {
        processNextCards(i+1, thisWinCount, 0)
      }
      // for loop que roda  
      else 0
      acc + thisWinCount + nextWinCount
    }
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