package cn.edu360.sort

import cn.edu360

/**
 * @author xuguohai
 * @date 2021/8/31
 * @version 1.0
 * @Description: 主要功能：
 */
object InsertionSort {
  def main(args: Array[String]): Unit = {
    val array: Array[Int] = Array(3, 6, 1, 7, 8, 10, 9)
    Insert(array)

    array.mkString(",").foreach(print)
  }

  /**
   * 具体排序:  
   *  第 1 个元素是有序的, 
   *  第 2 个元素与前面的进行比较, 如果碰到大的就交换元素；碰到小的停下来。然后前 2 个元素排序完成 
   *  第 3 个元素同理
   *  n 个元素, 只需要排 n-1 次即可
   *
   * */

  def Insert(array: Array[Int]): Unit ={
    //有len-1 个元素需要排序
    for (i <-0 until(array.length -1)){
      for (j <- i + 1 until(0,-1)){   //从 i+1 的位置开始向前比较
        // 如果后面的元素比前面的小，则交换两个元素
        if(array(j) < array(j - 1)){
          edu360.swap(array, j, j - 1)
        }
      }
    }
  }
}
