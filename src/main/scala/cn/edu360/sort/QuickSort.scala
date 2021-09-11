package cn.edu360.sort

import cn.edu360

import java.util.Random

/**
 * @author xuguohai
 * @date 2021/8/31
 * @version 1.0
 * @Description: 主要功能：
 */
object QuickSort {
  def main(args: Array[String]): Unit = {
//    val array: Array[Int] = Array(3, 6, 2, 7, 8, 10, 9, 1, 5)
//    Quick(array, 0, array.length - 1)

    val random: Random = new Random()
    val array1: Array[Int] = (1 to 1000).map(_ => random.nextInt(100000)).toArray

    val l: Long = System.currentTimeMillis()
    Quick(array1, 0, array1.length - 1)
    println(System.currentTimeMillis() - l)   //3

    println("-------------")
    array1.mkString(",").foreach(print)
  }

  /**
   * 快速排序
   * */
  def Quick(array: Array[Int], left: Int, right: Int): Unit = {
    // 如果左指针超过或等于右指针, 则排序已经完成, 无序再排序
    if (left >= right) return

    // 把数组分区, 保证左边的都小于等于某一个参数值, 右边的都大于等于某一个参考值
    // 返回参考值所在的位置, 作为划分数组的边界. 
    val m = quickPartition(array, left, right)

    // 再分别对左数组和右数组进行排序
    Quick(array, left, m - 1)
    Quick(array, m + 1, right)
  }


  /**
   * 快速排序分区
   * */
  def quickPartition(array: Array[Int], left: Int, right: Int): Int = {
    // 左扫描指针向右扫, 右扫描指针向左扫
    var l = left
    var r = right
    // 基准值
    val p = array(left)
    // 如果左扫描指针在右指针的左边就一直扫描
    while (l < r) {
      // 左指针向右跑, 跑到大于基准值,停下来
      while (l <= right && array(l) <= p) l += 1
      // 右指针向左跑, 跑到小于或等于基准值,停下来
      while (r > left && array(r) > p) r -= 1

      // 交换左右指针的位置，保证小于等于p 的去左边, 大于 p 的去右边
      if (r > l) edu360.swap(array, l, r)
    }
    // 让参考元素去到正确的位置, 右指针已经到了小于 p 的区域, 所以可以和右指针的元素交换
    cn.edu360.swap(array, left, r)
    // 此时右指针就是分割的位置
    r
  }
}
