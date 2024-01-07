import scala.math
val input = Vector(4, 2, 2, 1, 0, 0)
//val input = Vector(4, 2, 2, 1, 0, 0, 4, 2, 3)

val output = input.zipWithIndex.foldLeft(Vector.fill(input.length)(1)) {
  case (acc, (matches, index)) =>
  val value = Vector.fill(math.max(matches, 1))(1)
  println(s"acc ${index+1}: $acc")
    println(s"current value: $value")
  acc.zipWithIndex.map {
    case (elem, i) if
  }
}.sum
println(output)
