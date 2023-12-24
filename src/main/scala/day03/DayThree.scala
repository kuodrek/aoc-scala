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
    val engineSchematic = textFile.zipWithIndex.flatMap {
      case (line, i) => extractor.findAllMatchIn(line).map {
        case num @ IsInt(nb) => Number(nb, Coordinates(num.start, i), Coordinates(num.end - 1, i))
        case sym => Symbol(sym.matched, Coordinates(sym.start, i))
      }
    }.toArray
    val symbols: Array[Symbol] = engineSchematic.collect {
      case sym: Symbol => sym
    }
    val numbers: Array[Number] = engineSchematic.collect {
      case num: Number => num
    }

    numbers.collect {
      case num: Number if symbols.exists(_.checkForNumber(num)) => num.value
    }.sum
  }

  private def PartTwo(textFile: Iterator[String]) : Int = {
    val extractor: Regex = """\d+|\*""".r
    val engineSchematic = textFile.zipWithIndex.flatMap {
      case (line, i) => extractor.findAllMatchIn(line).map {
        case num @ IsInt(nb) => Number(nb, Coordinates(num.start, i), Coordinates(num.end - 1, i))
        case sym => Symbol(sym.matched, Coordinates(sym.start, i))
      }
    }.toArray
    val symbols: Array[Symbol] = engineSchematic.collect {
      case sym: Symbol => sym
    }
    val numbers: Array[Number] = engineSchematic.collect {
      case num: Number => num
    }

    val numberNearAsterisks: Array[(Symbol, Int)] = for {
      symbol: Symbol <- symbols
      number: Number <- numbers
      if symbol.checkForNumber(number)
    } yield (symbol, number.value)
    val numbersGroupedByAsterisks: Map[Symbol, Array[Int]] = numberNearAsterisks
      .groupBy(_._1)
      .map{
        case (key, value) => key -> value.map(_._2)
      }
    numbersGroupedByAsterisks.collect {
      case n if n._2.length == 2 => n._2.product
    }.sum

   }

  def main(args: Array[String]): Unit = {
    val filename: String = "./src/main/scala/day03/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
//    val totalSum: Int = PartOne(textFile.getLines)
    val totalSum: Int = PartTwo(textFile.getLines)
    println(totalSum)
  }
}
