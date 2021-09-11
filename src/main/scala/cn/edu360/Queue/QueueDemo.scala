package cn.edu360.Queue

import java.lang.UnsupportedOperationException
import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
 * @author xuguohai
 * @date 2021/8/28
 * @version 1.0
 * @Description: 主要功能：
 *               利用数组实现队列
 */
object QueueDemo {
  def main(args: Array[String]): Unit = {
    val Queue: ArrayQueue[Int] = new ArrayQueue[Int](5)
    Queue.inQueue(10)
    Queue.inQueue(20)
    Queue.inQueue(30)
    Queue.inQueue(40)
    Queue.inQueue(50)
    Queue.prQueue
    val queue: Option[Int] = Queue.outQueue
    println("出队元素： " + queue)
    Queue.prQueue

    Queue.inQueue(60)
    Queue.prQueue
  }

  /*
  * T: ClassTag
  * 给T添加上下文
  * 当调用方法传递参数执行时，ClassTag会把参数类型记住，其实ClassTag就是隐式值，将类型传递给T，
  * */
  class ArrayQueue[T: ClassTag](initSize: Int) {
   //内置数组，存储元素
   private val arr: Array[T] = new Array[T](initSize)
    // 队头：指向队列中的第一个元素
    var head = 0

    // 队尾：指向队列中的下一个元素
    var tail = 0
    // 个数：统计队列中的元素
    var count = 0

    //判断队列是否为空
    def isEmpty = count == 0
//    def isEmpty: Boolean = {
//      arr.isEmpty
//    }

    //判断队列是否满
    def isFull = count == initSize
//    def isFull: Boolean = {
//      if (count == initSize)
//        true
//      else
//        false
//    }

    //入队
    def inQueue(ele: T): Unit ={
      if (isFull) throw new UnsupportedOperationException("队列已满，不支持入队！！！")
      //把入队的元素添加到下一个位置
      arr(tail) = ele
      //更新tail
      tail += 1
      //tail到了数组的尾部,则从头开始
      //如果tail走到了数组的最后一个元素，那么tail应该指向数组的头部
      if (tail == initSize) tail = 0
      //队列元素加1
      count += 1
    }


    //出队
    def outQueue: Option[T] = {
      //如果队列元素为空，返回None
      if(isEmpty) {
        None
      }else{
        //返回队头元素
        val res: T = arr(head)
        //更新队头的元素
        head += 1
        //head到了数组的尾部,则从头开始
        if (head == initSize) head = 0
        //队列元素加1
        count -= 1
        Some(res)
      }

    }

    //打印队列，由头打印到尾
    def prQueue: Unit ={
      var tem = head
      print("队列中的元素：")
      for (i <- 0 until(count) ){
        print(arr(tem))
        tem += 1
        if (tem == initSize) tem = 0
        if (i+1 != count) {
          print("->")
        }
      }
      println()
    }
//      print("队列中的元素：")
//      if (head > tail) tail += initSize
//      for (i <- head until(tail) ){
//        print(arr(i%initSize) + "->")
////        if (i + 1 != initSize && arr(i) != 0){
////          print("->")
////        }
//      }
//      println()
    }

}
