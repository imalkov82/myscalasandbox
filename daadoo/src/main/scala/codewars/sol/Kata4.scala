package sol

import scala.collection.mutable.{ArrayBuffer, SortedSet}
import javax.swing.text.View

object DblLinear {
  /*
  lazy val u:LazyList[Int] = (1  #:: u.flatMap(x => Seq(2 * x + 1, 3 * x + 1))).distinct

  def dblLinear(n: Int): Int = {
    val a = u.take(10*n).toList.sorted
    a(n)
  }
  Sol2:
  def dblLinear(n: Int): Int = doIteration(SortedSet(1), n)

  @scala.annotation.tailrec
  private def doIteration(sortedSet: SortedSet[Int], index: Int): Int = index match {
    case 0 => sortedSet.head
    case _ => doIteration(sortedSet.tail + (2 * sortedSet.head + 1) + (3 * sortedSet.head + 1), index - 1)
  }
  */

  def dblLinear(n: Int): Int = {
    def dblLinearRec(n: Int, i: Int = 1): List[Int] = (i * 2 + 1, i * 3 + 1, n) match {
      case (a, b, 0) => a::b::Nil
      case (a, b, _) => a::b::dblLinearRec(n - 1, a) ++ dblLinearRec(n - 1, b)
    }
    dblLinearRec((math.log10(n)/math.log10(2)).round.toInt + 1).distinct.sorted.take(n).last
  }
}

object StringMix {
  def mix(s1: String, s2: String): String = {
    (s1.map((_, 1)) ++ s2.map((_, 2)))
    .filter{
      case (c, _) => ('a' to 'z').contains(c)
      case _ => false
    }.groupBy(_._1)
    .mapValues(xs => (xs.filter(_._2 == 1).size, xs.filter(_._2 == 2).size))
    .filter{
      case (_, (0, 0)) | (_, (0, 1)) | (_, (1, 0)) | (_, (1, 1)) => false
      case _ => true
    }.map{
      case (c, (n1, n2)) if n1 > n2 => (c, n1, 1)
      case (c, (n1, n2)) if n1 < n2 => (c, n2, 2)
      case (c, (n1, n2)) if n1 == n2 => (c, n2, 3)}
    .toList.sortBy(r => (r._2, r._3, r._1))(Ordering.Tuple3(Ordering.Int.reverse, Ordering.Int, Ordering.Char))
    .map{case (c, n, i) => s"${if (i == 3) "=" else i}:${List.range(0, n).map(_ => c).mkString}"}.mkString("/")
  }
}

object SumOfDivided {
  /*
    (2 to lst.map(_.abs).max)
      .collect {
        case i if BigInt(i).isProbablePrime(i) && lst.count(_ % i == 0) > 0 =>
          s"($i ${lst.filter(_ % i == 0).sum})"
      }
      .mkString  
  */
  val factorBase = Seq(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97) ++ 
  Seq(101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199)

  private def trialDivision(n: Int, i: Int=0): List[Int] = {
    if (n == 1){
      return Nil
    }
    else if (math.floorMod(n, factorBase(i)) == 0){
      return factorBase(i) :: trialDivision(n / factorBase(i), i)
    }
    else return trialDivision(n, i + 1)
  }
  
  def sumOfDivided(lst: Array[Int]): String = {
    lst
    .flatMap(x => trialDivision(x).distinct.map(y => (y, x)))
    .groupBy(_._1)
    .mapValues(_.map(_._2).distinct.sum)
    .map{case(k, v) => s"($k $v)"}
    .mkString("")
  }
}

object Kata4 extends App {
  // println(StringMix.mix("Are they here", "yes, they are here"))
  // println(StringMix.mix("looping is fun but dangerous", "less dangerous than coding"))
  // println(SumOfDivided.sumOfDivided(Array(15,21,24,30,45))) // "(2 54)(3 135)(5 90)(7 21)"
  // println(SumOfDivided.sumOfDivided(Array(107, 158, 204, 100, 118, 123, 126, 110, 116 )))
  val lst = Array(107, 158, 204, 100, 118, 123, 126, 110, 116 )
  println((2 to lst.map(_.abs).max))
}
