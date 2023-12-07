import scala.io.Source
import scala.util.matching.Regex

object AdventOfCode extends App {
  val filename = "/home/kuodrek/Documents/luis/scala-adventures/advent-of-code/advent-of-code/src/main/scala/data.txt"
  val textFile = Source.fromFile(filename).getLines

  val numberPattern: Regex = "[0-9]+".r
  val numbersMap: String => Seq[String] = (line: String) => numberPattern.findAllIn(line).flatMap(str => str.map(_.toString)).toSeq
  val StringNumSeq2: Seq[Seq[String]] = textFile.map(line => numbersMap(line)).toSeq
  val concatNumSeq = StringNumSeq2.map(numList => (numList.head+numList.last).toInt)
  val totalSum = concatNumSeq.sum
  println(StringNumSeq2)
  println(concatNumSeq)
  println(totalSum)
}