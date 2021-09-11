package cn.edu360.Linked_list

/**
 * @author xuguohai
 * @date 2021/8/29
 * @version 1.0
 * @Description: 主要功能：
 */
object DoubleLinkedList {
  def main(args: Array[String]): Unit = {
    val list: DoubleList[Int] = new DoubleList[Int]
    list.add(10)
    list.add(20)
    //    list.add(30)
    //    list.add(40)
    list.printAll
    //
    //    val node: list.node = list.find(10)
    //    println("查询结果：" + node.value)
    //    val node1: list.node = list.find(40)
    //    println("查询结果：" + node1.value)
    //    val node2: list.node = list.find(30)
    //    println("查询结果：" + node2.value)
    //    val node3: list.node = list.find(100)
    //    val value: Int = node3.value
    //    println("查询结果：" + value )

    list.delete(10)
    list.printAll
    list.delete(20)
    println("-------")
    list.printAll
    //    list.delete(30)
    //    list.printAll
    //    list.delete(100)
    //    list.printAll
    //
    //    list.add(100)
    //    list.printAll

  }
}

class DoubleList[T] {

  //Exception in thread "main" java.lang.StackOverflowError
  case class node(var pre: node, value: T, var nex: node)

  //  {
  //    override def toString: String = value + ""
  //  }
  // 使用这个case，则程序正常
  //  case class node(value: T, var pre: node, var nex: node)

  //{
  //    override def toString: String = value + ""
  //  }

  //定义头尾指针
  var head: node = _
  var tail: node = _

  //删除元素
  def add(ele: T): Unit = {
    val newnode = node(null, ele, null)
    //    val newnode = node(ele, null, null)
    if (head == null) {
      head = newnode
    } else {
      // 1. tail的next指向新节点
      tail.nex = newnode
      //2. 新节点pre指向tail
      newnode.pre = tail
    }
    // 让tail指向新节点
    tail = newnode
  }

  // 删除指定的元素
  def delete(ele: T): Boolean = {
    val node1: node = find(ele)
    if (node1 == null) false

    else {
      if (head.eq(tail)) { //只有一个节点需要删除
        head = null
        tail = null
      }
      else if (node1 == head) {
        head = head.nex
        head.pre = null
      } else if (node1 == tail) {
        tail = tail.pre
        tail.nex = null
      }
      else {
        node1.pre.nex = node1.nex
        node1.nex.pre = node1.pre
      }
      true
    }
  }

  def find(ele: T): node = {
    //由头结点开始查
    var tem = head
    while (tem != null) {
      if (tem.value == ele) return tem
      tem = tem.nex
    }
    null
  }

  //打印链表
  def printAll: Unit = {
    if (head != null) {
      var temp = head
      do {
        print(temp.value + "->")
        temp = temp.nex
      } while (temp != null)
      println()
    } else
      println("NULL")
  }
}

