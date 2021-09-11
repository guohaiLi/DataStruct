package cn.edu360.Linked_list

/**
 * @author xuguohai
 * @date 2021/8/30
 * @version 1.0
 * @Description: 主要功能：
 */
object JosephuGame {
  def main(args: Array[String]): Unit = {
    val last: Int = GameStart(10, 1, 3)
    println("存活的元素(链表)：" + last)

    val i: Int = JosephusQuestionByMath(10, 3)
    println("存活的元素(递归)：" + i)

  }

  def GameStart(count: Int, startNum: Int, endNum: Int) = {
    val list: CircleDoublyLinkedList[Int] = new CircleDoublyLinkedList[Int]
    //1.初始化循环双向链表
    for (i <- 1 to count) {
      list.add(i)
    }

    //2.找到开始的元素的上一个
    var startNode: list.node = list.find(startNum).pre

    print("枪毙顺序：")
    while (list.head != list.tail) {
      //3.找到结束元素
      for (i <- 1 to endNum) {
        startNode = startNode.nex
      }
      //4.删除结束元素
      list.delete(startNode.value)
      print(startNode.value + "->")

      //找到开始元素的上一个元素
      startNode = startNode.pre
    }
    println()
    //返回存活元素
    startNode.value
  }

  /**
   * f(1) = 1
   * f(t) = (m+f(t-1)-1)%t + 1
   *
   * @Author: xuguohai
   */
  def JosephusQuestionByMath(count: Int, num: Int): Int = {
    if (count == 1) {
      1
    } else {
      (num + JosephusQuestionByMath(count - 1, num) - 1) % count + 1
    }
  }
}

