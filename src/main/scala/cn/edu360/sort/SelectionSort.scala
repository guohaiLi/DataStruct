package cn.edu360.sort

/**
 * @author xuguohai
 * @date 2021/8/30
 * @version 1.0
 * @Description: 主要功能：
 */
object SelectionSort {
  def main(args: Array[String]): Unit = {
    val array: Array[Int] = Array(3, 6, 2, 7, 8, 10, 9, 1, 5)
    Select(array)

    array.mkString(",").foreach(print)
  }

  /**  
   * 选择排序: 
   * 本质: 
   *  1. 首先选择第 1 个数为最小的数, 然后让这个数和后面所有的数比较, 
   *   轮下来找到最小的数, 然后把最小的放在第 1 个位置 
   *  2. 再选择第 2 个数为第 2 小的数, 再后面逐次比较,....... 
   *
   * @param arr 
   * */

  def Select(array: Array[Int]): Unit = {
    //一共 len 个元素, 只需要找到 len-1 个就可以了, 剩下一个位置自动正确
    for (i <- 0 until (array.length - 1)) {
      var minIndex = i
      // 让第 i 个元素, 逐次与 i + 1 位置元素比较
      for (j <- i + 1 until (array.length)) {
        // 如果有比 minIndex 的位置更小的元素, 就记录下新的索引
        if (array(j) < array(minIndex)) {
          minIndex = j
        }
      }
      // 如果array(i)不是最小的元素，则与找到的最小元素交换位置
      if (i != minIndex) {
        val tem = array(i)
        array(i) = array(minIndex)
        array(minIndex) = tem
      }
    }
  }
}

/**
 *
 *  冒泡排序优缺点：优点:比较简单，空间复杂度较低，是稳定的；
                 缺点:时间复杂度太高，效率慢；
    选择排序优缺点：优点：一轮比较只需要换一次位置；
                 缺点：效率慢，不稳定
 * */