package cn.edu360.Linked_list

/**
 * @author xuguohai
 * @date 2021/8/29
 * @version 1.0
 * @Description: 主要功能：
 */
object SinglyLinkedList {
  def main(args: Array[String]): Unit = {
    val sList: SinglyList[Int] = new SinglyList[Int]
    sList.add(10)
    sList.add(20)
    sList.add(30)
    sList.add(40)
    sList.add(50)

    sList.printAll
    val bool = sList.find(100)
    println(bool)
    println(sList.find(50))

    sList.delete(30)
    sList.printAll

    sList.delete(50)
    sList.printAll

    sList.delete(10)
    sList.printAll

    sList.delete(10)
    sList.printAll

    sList.add(200)
    sList.printAll

  }

  class SinglyList[T]{
    //定义单链表节点
    case class node(value: T,var next: node)
    //定义头指针、尾指针
    var tail: node = _
    var head: node = _

    //添加元素
    def add(ele: T): Unit ={
      val newNode: node = node(ele,null)
      if (head == null){
        head = newNode
        tail = newNode
      }else{
        tail.next = newNode
        tail = newNode
      }
    }

    //删除元素
    def delete(ele: T): Boolean ={
      val tuple: (Boolean, Int) = find(ele)
      if (tuple._1 && tuple._2 != 1){
        var tem = head
        for (i <- 1 until (tuple._2 -1 )){
          tem = tem.next
        }
        if(tem.next.next != null) tem.next = tem.next.next
        else {
          //如果删除的是尾节点，需要更新尾节点
          tem.next = null
          tail = tem
        }
        println("删除" + ele + "成功！！！")
        return true
      }else if (tuple._1){
        //如果头节点就是要删除,则删除当前的头节点,并把下一个节点设置为头节点
        head = head.next
        println("删除" + ele + "成功！！！")
        return true
      }
      println("删除" + ele + "失败！！！")
      false
    }
    //查找元素
    def find(ele: T): (Boolean, Int) ={
      var index = 0
      if(head == null) return (false,index)
      else{
        var tem = head
        while (tem != null){
          index += 1
          if (tem.value == ele) return (true,index)
          else tem = tem.next
        }
      }
      (false,0)
    }

    //打印链表
    def printAll: Unit ={
      if (head == null) return
      var tem = head
      do {
        print(tem.value)
        tem = tem.next
        if (tem != null) print("->")
      }while(tem != null)
      println()
    }
  }
}
