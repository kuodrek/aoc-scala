package day04
import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex.Match

case class WinningNumbers(numbers: List[Int])
case class ScratchGame(numbers: List[Int])
object IsInt {
  def unapply(in: Match): Option[Int] = in.matched.toIntOption
}

object DayFour {
  private def PartOne(textFile: Iterator[String]): Int = {
    val extractor = """\d+""".r
    val gamesString = textFile.map {
      line => line.split("\\|").toList
    }.toList
    // TODO: Need to extract games from string.
    // How should I do it, considering that the order of elements matter?
    0
  }
  private def PartTwo(textFile: Iterator[String]): Int = {
    0
  }
  def main(args: Array[String]): Unit = {
    val filename: String = "./src/main/scala/day04/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val totalSum = PartOne(textFile.getLines)
  }
}