import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

object AdventOfCode {
  def problemOnePartOne(textFile: Iterator[String]): Int = {
    val numberPattern: Regex = "[0-9]+".r
    val numbersMap: String => Seq[String] = (line: String) => numberPattern.findAllIn(line).flatMap(str => str.map(_.toString)).toSeq
    val StringNumSeq2: Seq[Seq[String]] = textFile.map(line => numbersMap(line)).toSeq
    val concatNumSeq = StringNumSeq2.map(numList => (numList.head + numList.last).toInt)
    val totalSum = concatNumSeq.sum
    totalSum
  }
  def problemOnePartTwo(textFile: Iterator[String]): Int = {
    val spelledOutNumberPattern: Regex = "twone|oneight|one|two|three|four|five|six|seven|eight|nine|[0-9]".r
    val spelledOutToInteger: String => Int = {
        case "one" | "1" => 1
        case "two" | "2" => 2
        case "three" | "3" => 3
        case "four" | "4" => 4
        case "five" | "5" => 5
        case "six" | "6" => 6
        case "seven" | "7" => 7
        case "eight" | "8" => 8
        case "nine" | "9" => 9
    }
    val SpelledOutNumbers: String => Seq[String] = (line: String) => spelledOutNumberPattern.findAllIn(line).toSeq
    val output = textFile.map(line => SpelledOutNumbers(line).map(s => spelledOutToInteger(s))).toSeq
    println(output)
    val HeadAndLastNum = output.map(numList => numList.head * 10 + numList.last)
    println(HeadAndLastNum)
    val totalSum = HeadAndLastNum.sum
    totalSum
  }
  def main(args: Array[String]) : Unit = {
    val filename = "/home/kuodrek/Documents/luis/scala-adventures/advent-of-code/advent-of-code/src/main/scala/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val totalSum = problemOnePartTwo(textFile.getLines)
    textFile.close()
    println(totalSum)
  }
}
