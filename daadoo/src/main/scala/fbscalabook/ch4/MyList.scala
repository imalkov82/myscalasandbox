package fbscalabook.ch4

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List{
    def sum(ints: List[Int]): Int = ints match {
        case Nil => 0
        case Cons(head, tail) => head + sum(tail)
    }

    def product(ds: List[Double]): Double = ds match {
        case Nil => 1.0
        case Cons(0.0, _) => 0.0
        case Cons(head, tail) => head * product(tail)
    }

    def tail[A](l: List[A]): List[A] = l match {
        case Nil => Nil
        case Cons(head, tail) => tail
    }

    def setHead[A](h: A, l: List[A]): List[A] = l match {
        case Nil => Cons(h, Nil)
        case Cons(head, tail) => Cons(h, tail)
    }

    def drop[A](l: List[A], n: Int): List[A] = (n, l) match {
        case (_, Nil) => Nil
        case (0, xs) => xs
        case (a, Cons(_, tail)) => drop(tail, a - 1)
    }

    def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
        case Cons(head, tail) if f(head) => dropWhile(tail, f)
        case _ => l  
    }

    def apply[A](as: A*): List[A] = 
        if (as.isEmpty) Nil
        else Cons(as.head, apply(as.tail: _*))
}