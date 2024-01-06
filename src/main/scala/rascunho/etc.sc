val input = Vector(1, 2, 3, 4)

val output = input.zipWithIndex.foldLeft(Vector.fill(input.size)(1)) {
  case (acc, (value, index)) =>
    println(acc)
    acc ++ acc.take(index+1).map(_*(index+1))
}
println(output)