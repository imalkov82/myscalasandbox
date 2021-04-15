package fbscalabook.ch5

/*
From: https://github.com/fpinscala/fpinscala/blob/master/exercises/src/main/scala/fpinscala/laziness/Stream.scala

*/
import Stream._
trait Stream[+A]{

    

}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream{
    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
        lazy val head = hd
        lazy val tail = tl
        Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] = 
        if(as.isEmpty) empty
        else cons(as.head, apply(as.tail: _*))

    val ones: Stream[Int] = Stream.cons(1, ones)

    def from(n: Int): Stream[Int] = ???

    def unforld[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = ???
}
