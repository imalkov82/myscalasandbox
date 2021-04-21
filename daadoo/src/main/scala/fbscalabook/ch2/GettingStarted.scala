package fbscalabook.ch2

object MyModule{
    def abs(n: Int): Int = 
        if (n < 0) n
        else n

    def fib(n: Int): Int = {
        @annotation.tailrec
        def loop(n: Int, prev: Int, cur: Int): Int = 
            if (n == 0) prev
            else loop(n - 1, cur, prev + cur)
        loop(n, 0, 1)
    }

    def fib2(n: Int): List[Int] = {
        def loop(n2: Int, prev: Int, cur: Int): List[Int] = 
            if(n2 == 0 | cur > n) Nil
            else cur :: loop(n2 - 1, cur, prev + cur)
        loop(n, 0, 1)
    }
}

object GettingStarted extends App{
    println(MyModule.fib2(10))
}