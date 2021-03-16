package codewars.sol

object Opstrings {
  private def stringTrans(strng: String): Array[String] = ???
  def diag1Sym(strng: String): String = {
    val xs = strng.split("\n")
    (0 until xs.length)
    .foldLeft(List[String]()){
      case (acc, idx) =>  acc :+ xs.map(s => s(idx)).mkString("")
    }.mkString("\n")
  }

  def rot90Clock(strng: String): String = {
    val xs = strng.split("\n")
    (0 until xs.length)
    .foldLeft(List[String]()){
      case (acc, idx) => acc :+ xs.map(s => s(idx)).mkString("").reverse
    }.mkString("\n")
  }

  def selfieAndDiag1(strng: String): String = {
    strng.split("\n")
    .zip(diag1Sym(strng).split("\n"))
    .map{
      case (s1, s2) => s"$s1|$s2"
    }.mkString("\n")
  }

  def oper(f: String => String, s: String): String = f(s)
}

object Kata6 extends App {
  println(Opstrings.rot90Clock("rgavce\nvGcEKl\ndChZVW\nxNWgXR\niJBYDO\nSdmEKb"))
  println()
  println(Opstrings.diag1Sym("abcd\nefgh\nijkl\nmnop"))
  println()
  println(Opstrings.selfieAndDiag1("abcd\nefgh\nijkl\nmnop"))

}

