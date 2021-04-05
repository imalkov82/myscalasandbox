package sol

import collection.mutable.HashMap
object GapInPrimes {
  def gap(g: Int, m: Long, n: Long): String = ???
}

object JosephusSurvivor {
  def josephusSurvivor(n: Int, k: Int): Int = {
    /*
    if (n == 1) return 1
    return ( josephusSurvivor(n-1, k) + k - 1 ) % n + 1
     */
    LazyList
      .iterate(((1 until n + 1).toList, (k - 1) % n)) {
        case (xs, toEliminate) =>
          val (a, b) = xs.splitAt(toEliminate)
          (a ++ b.tail, (toEliminate + k - 1) % (xs.length - 1))
      }
      .collectFirst { case (head :: Nil, _) => head }
      .get
  }
}

object RemovedNumbers {
  /*
    def removNb(n: Long): List[(Long, Long)] = {
    val sum = n * (n+1) / 2
    (for {
      i <- 1L to n
      if ((sum.toDouble - i) / (i+1).toDouble).isWhole && (sum - i) / (i+1) <= n
    } yield (i, (sum - i) / (i+1))).toList
  }
   */
  def removNb(n: Long): List[(Long, Long)] = {
    val sumN = (1L until n + 1L).sum
    (2L until n)
      .foldLeft(List[Tuple2[Long, Long]]()){
        (acc, i) => (sumN.toDouble - i)/(i + 1) match {
          case x1 if (x1.isWhole && x1 < n) => acc :+ (i, x1.toLong)
          case _ => acc
        }}
  }
}

object WeightSort {
  def orderWeight(strng: String): String = {
    /*
    def orderWeight(str: String): String =
      str.split(" ").sortBy(d => (d.map(_.asDigit).sum, d)).mkString(" ")
     */
    strng
      .split(" ")
      .filter(_ != "")
      .map(s => (s, s.map(_.asDigit).sum))
      .sortWith{
        case ((org1, n1), (org2, n2)) =>
          if (n1 > n2 || (n1 == n2 && org1 > org2)) false
          else true
      }.map(_._1).mkString(" ")
  }
}

object Bud {
  /*
  def divSum(in: Long): Long = (2 to math.sqrt(in.toDouble).toInt).collect{
    case x if in % x == 0 => if(x*x == in) x.toLong else x.toLong + in/x
  }.sum
  def buddy(start: Long, limit: Long): String = {
    (start to limit).find{ n =>
      val ns = divSum(n)
      ns > n && divSum(ns) == n
    }.map(n => s"($n ${divSum(n)})").getOrElse("Nothing")
  }  
  */
  def properDivisorsSumMinusOne(start: Long, i: Long, minBy: Long=Long.MaxValue): Long = {
    if(i >= math.sqrt(start) || i >= minBy) 0
    else if((start / i.toDouble).isWhole)
      i + start / i + properDivisorsSumMinusOne(start, i + 1, math.min(minBy, start / i))
    else properDivisorsSumMinusOne(start, i + 1, minBy)
  }

  @annotation.tailrec
  def buddy(start: Long, limit: Long): String = {
    if (start >= limit) "Nothing"
    else {
      val sn_m_plus_1 = properDivisorsSumMinusOne(start, 2L)
      if (sn_m_plus_1 > start && properDivisorsSumMinusOne(sn_m_plus_1, 2L) == start)
        s"($start $sn_m_plus_1)"
      else buddy(start + 1, limit)
    }
  }
}

object MemoizedFib {
  /*  
  val cached: Map[Int, BigInt] = Map(0 -> 0, 1 -> 1)

  def fib(n: Int): BigInt =
    cached.getOrElseUpdate(n, fib(n-1) + fib(n-2))

  */
  val cache = collection.mutable.HashMap[Int, BigInt]()

  def fib(n: Int): BigInt = n match {
   case 0 | 1 => n
   case _ => {
     if (!cache.contains(n - 1))
       cache(n - 1) = fib(n - 1)
     if (!cache.contains(n - 2))
       cache(n - 2) = fib(n - 2)
     cache(n - 1) + cache(n - 2)
   }
  }
}

object MemoizedFib2 {  
  val cached: HashMap[Int, BigInt] = HashMap(0 -> 0, 1 -> 1)

  def fib(n: Int): BigInt =
    cached.getOrElseUpdate(n, fib(n-1) + fib(n-2))
}

object Kata5 extends App {
  println(MemoizedFib2.fib(50))
  println(MemoizedFib2.cached.values.takeWhile(_ < 50).drop(1))
}
