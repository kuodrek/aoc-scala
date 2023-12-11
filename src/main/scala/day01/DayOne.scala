package day01

import scala.annotation.unused
import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

object DayOne {
  @unused
  private def PartOne(textFile: Iterator[String]): Int = {
    val numberPattern: Regex = "[1-9]+".r
    val numbersMap: String => Seq[String] = (line: String) => numberPattern.findAllIn(line).flatMap(str => str.map(_.toString)).toSeq
    val StringNumSeq2: Seq[Seq[String]] = textFile.map(line => numbersMap(line)).toSeq
    val concatNumSeq: Seq[Int] = StringNumSeq2.map(numList => (numList.head + numList.last).toInt)
    val totalSum: Int = concatNumSeq.sum
    totalSum
  }
  private def PartTwo(textFile: Iterator[String]): Int = {
    val textNumberPattern: Regex = "[1-9]|one|two|three|four|five|six|seven|eight|nine|eightwo|eighthree|twone|oneight|fiveight|nineight|threeight|sevenine".r
    val textToInteger: Map[String, Int] = Map(
      "one" -> 1,
      "two" -> 2,
      "three" -> 3,
      "four" -> 4,
      "five" -> 5,
      "six" -> 6,
      "seven" -> 7,
      "eight" -> 8,
      "nine" -> 9,
    )
    val EdgeCasesHead: Map[String, Int] = Map(
      "oneight" -> 1,
      "twone" -> 2,
      "fiveight" -> 5,
      "nineight" -> 9,
      "threeight" -> 3,
      "sevenine" -> 7,
      "eightwo" -> 8,
      "eighthree" -> 8
    )
    val EdgeCasesTail: Map[String, Int] = Map(
      "oneight" -> 8,
      "twone" -> 1,
      "fiveight" -> 8,
      "nineight" -> 8,
      "threeight" -> 8,
      "sevenine" -> 9,
      "eightwo" -> 2,
      "eighthree" -> 3
    )
    val digitToInteger: IndexedSeq[(String, Int)] = (1 to 9).map(i => i.toString -> i)

    val headRepr: Map[String, Int] = textToInteger ++ digitToInteger ++ EdgeCasesHead
    val tailRepr: Map[String, Int] = textToInteger ++ digitToInteger ++ EdgeCasesTail

    val textualNumbers: String => Seq[String] = (line: String) => textNumberPattern.findAllIn(line).toSeq
    val output: Seq[Seq[String]] = textFile.map(line => textualNumbers(line)).toSeq
    val HeadAndLastNum: Seq[Int] = output.map(numList => headRepr(numList.head) * 10 + tailRepr(numList.last))
    val totalSum: Int = HeadAndLastNum.sum
    totalSum
  }
  def main(args: Array[String]) : Unit = {
    val filename: String = "./src/main/scala/day01/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
//    val totalSum: Int = PartOne(textFile.getLines)
    val totalSum: Int = PartTwo(textFile.getLines)
    println(totalSum)
    textFile.close()
  }
}
