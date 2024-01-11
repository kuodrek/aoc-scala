package day05
import scala.io.{BufferedSource, Source}

case class DestinationMap(destination: Int, source: Int, length: Int) {
  def CheckSourceCandidate(candidate: Int): Boolean = {
    if (candidate >= source && source <= (source + length)) true
    else false
  }
  def GetDestination(candidate: Int): Int = candidate - (source - destination)
}

def SourceToDestination(candidate: Int, destinations: List[DestinationMap]) : Int = {
  val destination: Option[DestinationMap] = destinations.find(_.CheckSourceCandidate(candidate))
  println(destination)
  destination match {
    case Some(destinationMap) => destinationMap.GetDestination(candidate)
    case None => candidate
  }
}

object DayFive {
  private def PartOne(): Int = {0}
  private def PartTwo(): Int = {0}
  private def SourceToDestination(source: Int, destinations: List[DestinationMap]) : Int = {0}
  def main(args: Array[String]): Unit = {
    val filename: String = "./src/main/scala/day05/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val gardenData = textFile.mkString.split("\\n").toVector
    println(gardenData)
    val seeds = "\\d+".r.findAllMatchIn(gardenData.head)
      .flatMap(num => num.matched.toLongOption).toVector
  }
}