package day03
import scala.util.matching.Regex.Match

object IsInt {
  def unapply(in: Match): Option[Int] = in.matched.toIntOption
}