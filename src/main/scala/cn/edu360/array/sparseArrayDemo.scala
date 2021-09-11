package cn.edu360.array

/**
 * @author xuguohai
 * @date 2021/9/9
 * @version 1.0
 * @Description: 主要功能：
 */
object sparseArrayDemo {
  def main(args: Array[String]): Unit = {
    // 初始化一个二维数组
    val chessArray1: Array[Array[Int]] = Array.ofDim[Int](11,12)
    chessArray1(1)(2) = 1

    for (i<-0 until(chessArray1.length)){
      for (j <-0 until(chessArray1.length)){
        print(chessArray1(i)(j) + "\t")
      }
      println()
    }
  }
}
