package day05
import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

case class DestinationMap(destination: Int, source: Int, length: Int) {
  def CheckSourceCandidate(candidate: Int): Boolean = {
    if (candidate >= source && source <= (source + length)) true
    else false
  }
  def GetDestination(candidate: Int): Int = candidate - (source - destination)
}

object DayFive {
  private def PartOne(): Int = {0}
  private def PartTwo(): Int = {0}
  private def SourceToDestination(candidate: Int, destinations: List[DestinationMap]) : Int = {
    val destination: Option[DestinationMap] = destinations.find(_.CheckSourceCandidate(candidate))
    println(destination)
    destination match {
      case Some(destinationMap) => destinationMap.GetDestination(candidate)
      case None => candidate
    }
  }

  def main(args: Array[String]): Unit = {
    val filename: String = "./src/main/scala/day05/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val gardenData = textFile.mkString.split("\\n")

    val extractor: Regex = "\\d+".r
    println(gardenData)
    val seeds = extractor.findAllMatchIn(gardenData.head)
      .flatMap(num => num.matched.toLongOption).toList
    val destinations = gardenData.drop(1).map(gardenMap => gardenMap.head -> extractor.findAllMatchIn(gardenMap))
    println(destinations)
  }
}