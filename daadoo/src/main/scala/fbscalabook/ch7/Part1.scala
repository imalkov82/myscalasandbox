package fbscalabook.ch7

object Part1 extends App{
  def sum(ints: IndexedSeq[Int]): Int =
    if(ints.size <= 1)
      ints.headOption getOrElse 0
   else{
      val (l, r) = ints.splitAt(ints.length / 2)
      sum(l) + sum(r)
    }
}
