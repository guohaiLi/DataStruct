package cn

import java.util.Random

/**
 * @author xuguohai
 * @date 2021/8/31
 * @version 1.0
 * @Description: 主要功能：
 */
package object edu360 {
  def swap(array: Array[Int],i: Int,j: Int): Unit ={
    val tem = array(i)
    array(i) = array(j)
    array(j) = tem
  }

  def RandomArray: Array[Int] ={
    val random: Random = new Random()
    (1 to 100).map(_ => random.nextInt(1000)).toArray
  }
}
