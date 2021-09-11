package cn.edu360.tree

/**
 * @author xuguohai
 * @date 2021/9/2
 * @version 1.0
 * @Description: 主要功能：
 */
object BinaryTree {
  def main(args: Array[String]): Unit = {
    val tree: BinaryTree[Int] = initBTree()
    tree.preForeach(println)

    println("中序遍历：")
    tree.infixForeach(println)
//    tree.infixForeach(x => {
//
//
//    })
  }

  //初始化二叉树
  def initBTree(): BinaryTree[Int] ={

    // 创建5个节点对象
    val n_10 = new treeNode[Int](10)
    val n_9 = new treeNode[Int](9)
    val n_20 = new treeNode[Int](20)
    val n_15 = new treeNode[Int](15)
    val n_35 = new treeNode[Int](35)

    // 构建节点之间的关系
    n_10.left = n_9
    n_10.right = n_20

    n_9.p = n_10

    n_20.p = n_10
    n_20.left = n_15
    n_20.right = n_35

    n_15.p = n_20
    n_35.p = n_20

    // 创建一个二叉树对象
    val tree: BinaryTree[Int] = new BinaryTree[Int]

    //指定二叉树的根节点
    tree.root = n_10

    //返回二叉树
    tree
  }
}

//创建二叉树
class BinaryTree[T]{
  var root: treeNode[T] = _

  //前序遍历
  def preForeach(op: T => Unit): Unit ={
    if(root != null)
      root.preForeach(op)
  }

  //中序遍历
  def infixForeach(op: T => Unit): Unit ={
    if (root != null)
      root.infixForeach(op)
  }
}

//定义二叉树的节点
class treeNode[T](val value: T){
  //父节点
  var p: treeNode[T] = _
  //左右节点
  var left: treeNode[T] = _
  var right: treeNode[T] = _

  // 先序遍历二叉树（根，左，右）
  def preForeach(op: T => Unit): Unit ={
    //遍历当前节点
    op(value)
    // 遍历左子节点
    if(left != null) left.preForeach(op)
    // 遍历右子节点
    if(right != null) right.preForeach(op)
  }

  // 中序遍历二叉树（左，根，右）
  def infixForeach(op: T => Unit): Unit ={
    // 遍历左
    if(left != null) left.infixForeach(op)
    // 遍历根
    op(value)
    // 遍历右
    if (right != null) right.infixForeach(op)
  }
}
