package cn.edu360.tree

/**
 * @author xuguohai
 * @date 2021/9/2
 * @version 1.0
 * @Description: 主要功能：
 */
object SearchBinaryTree {
  def main(args: Array[String]): Unit = {

    val arr = Array(8, 4, 12, 9, 10, 1, 6, 7, 5, 15, 13, 3)

    val sbTree: SearchBinaryTree[Int] = initSBTree(arr)
    println("二叉排序树中序遍历结果：")
    sbTree.infixForeach(x => {
      print(x + "->")
    })
    println()

    //    val value: SBTreeNode[Int] = sbTree.searchNode(15)
    //    println(value)
    //
    //    val value1: SBTreeNode[Int] = sbTree.searchNode(100)
    //    println(value1)

    //    sbTree.infixForeach(println)
    val bool: Boolean = sbTree.deleteNode(7)
    val bool2: Boolean = sbTree.deleteNode(70)
    println("二叉排序树删除7后中序遍历结果：")
    sbTree.infixForeach(x => {
      print(x + "->")
    })
    println()

    println("删除7：" + bool)
    println("删除70：" + bool2)
  }

  // 初始化一个排序二叉树
  def initSBTree(arr: Array[Int]): SearchBinaryTree[Int] = {
    val tree: SearchBinaryTree[Int] = new SearchBinaryTree[Int]
    arr.foreach(x => {
      tree.add(x)
    })
    tree
  }

}

// 二叉排序树的节点
class SBTreeNode[T: Ordering](var value: T) {
  // 从冥界召唤可以比较 T 类型的比较器(Ordering)
  private val orderValueT: Ordering[T] = implicitly[Ordering[T]]
  // 父节点
  var p: SBTreeNode[T] = _
  // 左节点
  var left: SBTreeNode[T] = _
  // 右节点
  var right: SBTreeNode[T] = _

  // 插入节点
  def add(ele: T): Unit = {
    // 如果小于等于当前值, 则在左边添加
    if (orderValueT.lteq(ele, value)) {
      // 如果左节点是 null, 则左节点置为新节点
      if (left == null) {
        left = new SBTreeNode[T](ele)
        left.p = this
      }
      else // 否则递归的在左节点添加节点
        left.add(ele)
    } else { // 否则向右边添加
      if (right == null) {
        right = new SBTreeNode[T](ele)
        right.p = this
      }
      else right.add(ele)
    }
  }

  // 中序遍历节点
  def infixForeach(op: T => Unit): Unit = {
    if (left != null) left.infixForeach(op)
    op(value)
    if (right != null) right.infixForeach(op)
  }

  // 查找节点
  def searchNode(ele: T): SBTreeNode[T] = {
    // 如果小于当前节点, 则去左边查找
    if (orderValueT.lt(ele, value) && left != null)
      left.searchNode(ele)

    // 如果大于当前节点, 则去右边查找
    else if (orderValueT.gt(ele, value) && right != null)
      right.searchNode(ele)

    // 如果和当前节点的值相等, 则返回当前节点
    else if (orderValueT.equiv(ele, value))
      this

    // 如果没有找到, 则返回null
    else null
  }

  // 删除节点
  def deleteSBT(ele: T): Boolean = {
    // 1. 如果当前节点为要删除的节点
    if (orderValueT.equiv(ele, value)) {
      // 由于将当前节点删除后，需要重新指定父节点的孩子节点，所以需要判断当前节点是左孩子还是右孩子
      // 假设当前节点为左孩子
      var isLeft = true
      //判断是否为右孩子
      if (p != null && p.right != null && orderValueT.equiv(p.right.value, ele)) {
        isLeft = false
      }
      // 1.1 如果当前节点为叶子节点
      if (left == null && right == null) {
        // 如果删除的是左孩子，则父节点的左指针指向空
        if (isLeft) p.left = null
        // 否则父节点的右指针指向空
        else p.right = null
      } else if (left != null && right != null) {
        // 1.2 如果当前节点的左右节点都不为空，则找到右子树的最小节点，将其删除，
        // 并将其值替换给当前节点
        value = right.deleteMin
      } else {
        // 1.3 否则当前节点只有一颗子树
        // 找到唯一的子节点
        val onlyNode = if (left != null) left else right
        if (isLeft) p.left = onlyNode
        else p.right = onlyNode
      }
      true
    }

    // 2. 如果要删除的节点比当前节点小
    else if (orderValueT.lt(ele, value)) {
      // 如果左节点为空，删除失败
      if (left == null) false
      // 否则递归删除左子树
      else left.deleteSBT(ele)
    }

    // 3. 否则要删除的节点比当前节点大
    else {
      // 如果右子树为空，则删除失败
      if (right == null) false
      // 否则递归删除右子树
      else right.deleteSBT(ele)
    }
  }

  // 寻找后继节点
  // 删除当前节点的最小节点，并返回最小节点的值域
  def deleteMin: T = {
    var minNode = this
    //遍历左节点, 找到最小的子节点
    while (minNode.left != null) {
      minNode = minNode.left
    }
    // 删除最小的节点
    minNode.deleteSBT(minNode.value)
    //返回最小节点的值域
    minNode.value
  }

  // 重写toString方法
  override def toString: String = s"value= $value \n p= ${p.value}"
}

// 排序二叉树
class SearchBinaryTree[T: Ordering] {
  // 排序二叉树的根节点
  var root: SBTreeNode[T] = _

  // 向排序二叉树中添加节点
  def add(ele: T): Unit = {
    // 如果 root 节点为 null, 则把元素置为 root 位置
    if (root == null)
      root = new SBTreeNode[T](ele)
    // 如果 root 节点不为空则调用 root 的 add 方法来添加元素
    else
      root.add(ele)
  }

  // 中序遍历二叉树
  def infixForeach(op: T => Unit): Unit = {
    if (root == null)
      println("二叉排序树为空！！！")
    else
      root.infixForeach(op)
  }

  // 查找节点
  def searchNode(ele: T): SBTreeNode[T] = {
    if (root == null) null
    else
      root.searchNode(ele)
  }

  // 删除指定节点
  def deleteNode(ele: T): Boolean = {
    // 1.如果根节点为空，返回false
    if (root == null) false
    else if (root.value == ele) { // 2.如果要删除的是根节点
      // 如果根节点是唯一的节点，则直接令root = null
      if (root.left == null && root.right == null) {
        root = null
      } else if (root.left != null && root.right != null) {
        // 如果根节点的左右节点都不为空，则删除右子树的最小节点，并且用右子树的最小值代替根节点
        root.value = root.right.deleteMin
      } else {
        // 如果根节点只有一颗子树，则用子树的节点代替根节点
        root = if (root.left != null) root.left else root.right
      }
      true
    }
    // 3.否则，要删除的就是root的子节点
    else root.deleteSBT(ele)
  }
}

