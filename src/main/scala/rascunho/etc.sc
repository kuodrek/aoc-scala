import scala.math
val input = Vector(4, 2, 2, 1, 0, 0)

val output = input.zipWithIndex.foldLeft(Vector.fill(input.length)(1)) {
  case (acc, (matches, index)) =>
  println(s"acc ${index+1}: $acc")
    println(s"index: $index")
  acc.take(index + 1) ++
  (index+1 to math.min(index+matches, input.length)).map(i => acc(i)+acc(index)) ++
  acc.drop(index + 1 + matches)
}.sum
println(output)
