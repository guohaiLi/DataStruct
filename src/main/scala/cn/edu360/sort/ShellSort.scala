package cn.edu360.sort

import cn.edu360

/**
 * @author xuguohai
 * @date 2021/9/1
 * @version 1.0
 * @Description: 主要功能：
 */
object ShellSort {
  def main(args: Array[String]): Unit = {
    val array: Array[Int] = edu360.RandomArray
    shell(array)
    array.mkString(",").foreach(print)
  }

  def shell(array: Array[Int]): Unit ={
    // 初始化步长: 长度的一半
    var gap = array.length / 2
    while (gap > 0){
      // 从每组的第二行开始, (插入排序)
      for (i <- gap until(array.length)){
        var j = i - gap
        while (j >= 0){
          // 上一行和当前行进行比较 
          if(array(j) > array(j + gap))
            edu360.swap(array, j, j + gap)
          j -= gap
        }
      }
      gap = gap / 2
    }
  }
}
