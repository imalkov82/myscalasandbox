package com.paypal

import java.util.Scanner

object FibSeq{
  private def calcFib(n: Int): List[Int] = n match {
        case 0 => List()
        case 1 => List(1, 1)
        case _ => {
            var acc = List(1, 1) 
            (1 to n) takeWhile {(i: Int) => acc = acc ++ List(acc.last + acc(acc.length - 2)); acc.last <= n}
            acc.dropRight(1)
        }
  }
  
  def fib(n: Int): String = calcFib(n).mkString(",")
}

object Interviews extends App{
    println(FibSeq.fib(100))
}