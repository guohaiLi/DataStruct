package cn.edu360.Linked_list


/**
 * @author xuguohai
 * @date 2021/8/29
 * @version 1.0
 * @Description: 主要功能：
 */
object CircleDoubleLinkedList {
  def main(args: Array[String]): Unit = {
//    val list = new CircleDoubleList[Int]
val list: CircleDoublyLinkedList[Int] = new CircleDoublyLinkedList[Int]
    list.add(10)
    list.add(100)
    list.add(40)
    list.printAll
    val node = list.find(100)
    if (node == null) {
      println("查找失败")
    }else{
      println("查询结果："+ node.value)
    }

    list.delete(10)
    list.printAll

    list.delete(40)
    list.printAll

    list.delete(100)
    list.printAll

    list.add(300)
    list.printAll


  }
}

class CircleDoublyLinkedList[T] extends DoubleList[T] {
  //增加元素
  override def add(ele: T): Unit = {
    super.add(ele)
    //让头元素与尾元素形成环
    head.pre = tail
    tail.nex = head

  }
  //打印链表
  override def printAll: Unit = {
    if (head == null) return
    var temp = head
    while (true) {
      if (temp.eq(tail)) {
        println(temp.value)
        return
      }
      else {
        print(temp.value + "->")
      }
      temp = temp.nex
    }

  }

  //删除元素
  override def delete(ele: T): Boolean = {
    //如果删除元素成功，重新构成环
    if (super.delete(ele)){
      if (head != null && tail != null) {
        head.pre = tail
        tail.nex = head
      }
      true
    }
    else false
  }

}