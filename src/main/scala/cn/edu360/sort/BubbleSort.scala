package cn.edu360.sort

import cn.edu360

/**
 * @author xuguohai
 * @date 2021/8/30
 * @version 1.0
 * @Description: 主要功能：
 */
object BubbleSort {
  def main(args: Array[String]): Unit = {
    val array: Array[Int] = Array(3, 6, 1, 7, 8, 10, 9)
    BubbleDemo(array)
    array.mkString(",").foreach(print)
  }

  /**  
   *冒泡排序 
   * 本质: 
   *  相邻的元素进行比较, 然后小的在前, 大的在后。一轮下能成功排序 1 个 
   *  如果有 n 个元素, 则需要排序 n-1 轮 
   * @param arr 
   * */

  def BubbleDemo(array: Array[Int]): Unit = {
    for (i <- 0 until (array.length - 1)) { //外层循环，有多少元素(len - 1)需要排序 
      //内存循环，没循环一次，得到一个最小值和一个最大值
      // 每个元素需要比较的次数(第 1 个比较 len-1, 第 2 个比较 len-1-1, ...)  
      for (j <- 0 until (array.length - 1 - i)) {
        if (array(j) > array(j + 1)) {
          edu360.swap(array, j, j+1)
//          val tem = array(j)
//          array(j) = array(j + 1)
//          array(j + 1) = tem
        }
      }
    }
  }
}
