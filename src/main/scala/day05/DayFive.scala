package day05
import scala.io.{BufferedSource, Source}

case class Seeds(Location: Vector[Long])
case class GardenTask(Category: String, numbersMap: Vector[Vector[Long]])

object DayFive {
  private def PartOne(): Int = {0}
  private def PartTwo(): Int = {0}

  def main(args: Array[String]): Unit = {
    val filename: String = "./src/main/scala/day05/input/data.txt"
    val textFile: BufferedSource = Source.fromFile(filename)
    val gardenData = textFile.mkString.split("\\n").toVector
    println(gardenData)
    val seeds = "\\d+".r.findAllMatchIn(gardenData.head)
      .flatMap(num => num.matched.toLongOption).toVector
  }
}