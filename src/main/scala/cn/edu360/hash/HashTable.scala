package cn.edu360.hash

import cn.edu360.Linked_list.{DoubleLinkedList, DoubleList}

/**
 * @author xuguohai
 * @date 2021/9/1
 * @version 1.0
 * @Description: 主要功能：
 */
object HashTable {
  def main(args: Array[String]): Unit = {
    val hash_table: hash[Int] = new hash[Int](3)
    hash_table.add_hash(10)
    hash_table.add_hash(20)
    hash_table.add_hash(30)
    hash_table.add_hash(40)
    hash_table.add_hash(50)

    hash_table.printAll

    hash_table.delete(30)

    hash_table.printAll
  }

}

class hash[T](size: Int){
  // 创建一个存储双向链表的数组
  val array: Array[DoubleList[T]] = new Array[DoubleList[T]](size)
  val len = array.length

  // 向hash表中添加数据
  def add_hash(ele: T): Unit ={
    // 得到索引
    val index: Int = ele.hashCode().abs % len

    // 首先判断次数的链表是否已经创建, 如果没有需要先创建链表对象
    if(array(index) == null){
      array(index)  = new DoubleList[T]
    }

    // 从数组中取出该位置的链表
    val list: DoubleList[T] = array(index)
    list.add(ele)
  }

  // 打印hash表中的内容
  def printAll: Unit ={
    for (i <-0 until(array.length)){
      val DList: DoubleList[T] = array(i)
      print(s"${i} :")
     // if (DList != null)
        DList.printAll

     // println()
    }
  }

  // 删除hash表中的内容
  def delete(ele: T): Unit ={
    // 先找到删除元素的位置
    val i: Int = ele.hashCode().abs % len
    array(i).delete(ele)
  }
}

/**
基本的数据结构:
数组
    优点
        元素的定位比较快, 有索引  O(1)
    缺点
        删除和添加比较慢 O(n)

链表
    优点
        插入和删除比较快  O(1)
    确定
        查找元素  O(n)

散列表或者hash表, 集合了数组和链表的优点

*/