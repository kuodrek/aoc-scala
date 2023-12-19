package day03
import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

object DayThree {
  private def PartOne(textFile: Iterator[String]): Int = {
    val extractor: Regex = """\d+|[^.\d]""".r
    val engineSchematic = textFile.mkString.split("\n").zipWithIndex.flatMap {
      case (line, i) => extractor.findAllMatchIn(line).map {
        case num @ IsInt(nb) => Number(nb, Coordinates(num.start, i), Coordinates(num.end, i))
        case sym => Symbol(sym.matched, Coordinates(sym.start, i))
      }
    }
    val sum = engineSchematic.map {
      case num: Number => num.value // need to check if num is in neighborhood of symbol
    }.sum
    0
  }
  def main(args: Array[String]): Unit = {
    // Get input
    // Save in val: List[List[Char]] (each character is an element)
    // Somehow keep track of number indexes
    // If special character is adjacent to number, sum
    val filename: String = "./src/main/scala/day03/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val totalSum: Int = PartOne(textFile.getLines)
//    val pattern
  }
}
