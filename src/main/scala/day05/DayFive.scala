package day05
import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

case class DestinationMap(destination: Long, source: Long, length: Long) {
  def CheckSourceCandidate(candidate: Long): Boolean = {
    if (candidate >= source && candidate <= (source + length)) true
    else false
  }
  def GetDestination(candidate: Long): Long = candidate - (source - destination)
}

object DayFive {
  private def PartOne(seeds: Array[Long], destinations: Array[Array[DestinationMap]]): Long = {
    val soils: Array[Long] = seeds.map(seed => SourceToDestination(seed, destinations(0)))
    val fertilizers: Array[Long] = soils.map(soil => SourceToDestination(soil, destinations(1)))
    val waters: Array[Long] = fertilizers.map(fertilizer => SourceToDestination(fertilizer, destinations(2)))
    val lights: Array[Long] = waters.map(water => SourceToDestination(water, destinations(3)))
    val temperatures: Array[Long] = lights.map(light => SourceToDestination(light, destinations(4)))
    val humidities: Array[Long] = temperatures.map(temperature => SourceToDestination(temperature, destinations(5)))
    val locations: Array[Long] = humidities.map(humidity => SourceToDestination(humidity, destinations(6)))
    locations.min
  }
  private def SourceToDestination(candidate: Long, destinations: Array[DestinationMap]) : Long = {
    val destination: Option[DestinationMap] = destinations.find(_.CheckSourceCandidate(candidate))
    destination match {
      case Some(destinationMap) => destinationMap.GetDestination(candidate)
      case None => candidate
    }
  }

  def main(args: Array[String]): Unit = {
    val filename: String = "./src/main/scala/day05/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val gardenData = textFile.mkString.split("\\n\\n")

    val extractor: Regex = "\\d+".r

    val seeds = extractor.findAllMatchIn(gardenData.head)
      .flatMap(num => num.matched.toLongOption).toArray

    val destinations: Array[Array[DestinationMap]] = {
      val pattern = """(\d+)\s+(\d+)\s+(\d+)""".r
      val gardenMaps: Array[Array[String]] = gardenData.drop(1).map(gardenMap => gardenMap.split(":"))
      val splitDestinationMaps = gardenMaps.map(
        gardenMap => gardenMap.last.split("\n").tail
      )
      splitDestinationMaps.map(
        array => array.map {
          case pattern(m1, m2, m3) => DestinationMap(m1.toLong, m2.toLong, m3.toLong)
        }
      )
    }

    val minLocation = PartOne(seeds, destinations)
    println(minLocation)
  }
}