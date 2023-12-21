package day03
import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex
import scala.util.matching.Regex.Match

case class Coordinates(x: Int, y: Int)

case class Symbol(value: String, coordinates: Coordinates) {
  def checkForNumber(number: Number): Boolean = {
    if (coordinates.x < number.start.x-1 || coordinates.x > number.end.x+1) false
    else if (coordinates.y < number.start.y-1 || coordinates.y > number.end.y+1) false
    else true
  }
}
case class Number(value: Int, start: Coordinates, end: Coordinates)
object IsInt {
  def unapply(in: Match): Option[Int] = in.matched.toIntOption
}

object DayThree {
  private def PartOne(textFile: Iterator[String]): Int = {
    val extractor: Regex = """\d+|[^.\d]""".r
//    val text = textFile.mkString
    val engineSchematic = textFile.zipWithIndex.flatMap {
      case (line, i) => extractor.findAllMatchIn(line).map {
        case num @ IsInt(nb) => Number(nb, Coordinates(num.start, i), Coordinates(num.end - 1, i))
        case sym => Symbol(sym.matched, Coordinates(sym.start, i))
      }
    }.toArray
    val symbols = engineSchematic.collect {
      case sym: Symbol => sym
    }
    val numbers = engineSchematic.collect {
      case num: Number => num
    }

    numbers.collect {
      case num: Number if symbols.exists(_.checkForNumber(num)) => num.value
    }.sum
  }
  def main(args: Array[String]): Unit = {
    // Get input
    // Save in val: List[List[Char]] (each character is an element)
    // Somehow keep track of number indexes
    // If special character is adjacent to number, sum
    val filename: String = "./src/main/scala/day03/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val totalSum: Int = PartOne(textFile.getLines)
    println(totalSum)
  }
}
