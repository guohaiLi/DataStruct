package cn.edu360.sort

import java.util.Random

/**
 * @author xuguohai
 * @date 2021/9/1
 * @version 1.0
 * @Description: 主要功能：
 */
object MergeSort {
  def main(args: Array[String]): Unit = {
    val random: Random = new Random()
    val array1: Array[Int] = (1 to 1000).map(_ => random.nextInt(100000)).toArray

    val l: Long = System.currentTimeMillis()
    Merge_dec(array1, 0, array1.length - 1)
    println(System.currentTimeMillis() - l)

    println("-------------")
    array1.mkString(",").foreach(print)
  }

  def Merge_dec(array: Array[Int], start: Int, end: Int): Unit = {
    if (start >= end) return
    // 计算平均值的位置
    val mid = (start + end) / 2

    //分解左边进行排序
    Merge_dec(array, start, mid)
    //分解右边进行排序
    Merge_dec(array, mid + 1, end)

    //治，合并
    Merge(array, start, mid, end)
  }

  def Merge(array: Array[Int], start: Int, mid: Int, stop: Int): Unit = {
    // 截取已经有序的左边数组 [start, mid, ∞]
    //slice(from: Int, until: Int)
    val left = array.slice(start, mid + 1) :+ Int.MaxValue
    // 截取已经有序的右边数组 [mid + 1, stop, ∞]
    val right = array.slice(mid + 1, stop + 1) :+ Int.MaxValue

    // 定义数组下标
    var left_index = 0
    var right_index = 0

    for (i <- start to stop) { // i表示原数组中的索引
      if (left(left_index) <= right(right_index)) {
        array(i) = left(left_index)
        left_index += 1
      }
      else {
        array(i) = right(right_index)
        right_index += 1
      }
    }
  }
}

/**
 * 1.分解
 *  待排序的数组分成2个子数组: A[0, …. len/2] 和 A[len/2 + 1, len - 1]
 * 2.解决
 *  使用归并排序递归的地排序两个子序列
 * 3.合并
 *  合并两个已排序的子序列
 * 当待排序的序列长度为 1 时, 递归”开始回升”, 在这种情况下不需要做任何操作, 因为长度为1的序列都已经排好序了
 */

