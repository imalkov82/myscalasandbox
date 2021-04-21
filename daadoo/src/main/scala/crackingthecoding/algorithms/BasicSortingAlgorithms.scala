package crackingthecoding.algorithms

object BubbleSort{
    def sort[A](arr: Array[A], f: (A, A) => Boolean): Array[A] = {
        for(i <- 0 until arr.length; j <- 0 until arr.length - i - 1){
             if (f(arr(j), arr(j + 1)) == true){
                val temp = arr(j)
                arr(j) = arr(j + 1)
                arr(j + 1) = temp
             }
        }
        arr
    }
}
object BasicSortingAlgorithms extends App{
    println(BubbleSort.sort(Array[Int](3, 4, 1, 0), (i: Int,j: Int) => i < j).mkString(","))
}

