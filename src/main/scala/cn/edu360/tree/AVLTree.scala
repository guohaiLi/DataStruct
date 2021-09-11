package cn.edu360.tree

/**
 * @author xuguohai
 * @date 2021/9/2
 * @version 1.0
 * @Description: 主要功能：
 */
object AVLTreeDemo {
  def main(args: Array[String]): Unit = {

//    val arr = Array(8, 4, 12, 9, 10, 1, 6, 7, 5, 15, 13, 3)

//            val arr = Array(4, 3, 6, 5, 7, 8) // (右右)需要左旋
    //        val arr = Array(10, 12, 8, 9, 7, 6) // (左左)需要右旋
//            val arr: Array[Int] = Array(10, 11, 7, 6, 8, 9) // (左右) 先左旋再右旋
    val arr: Array[Int] = Array(10, 8, 16, 18, 14, 12) // (右左) 先右旋再左旋

    val AVLTree: AVLTree[Int] = initSBTree(arr)
    println("AVL树中序遍历结果：")
    AVLTree.infixForeach(x => {
      print(x + "->")
    })
    println()

    val height: Int = AVLTree.height()
    println("树的高度：" + height)
  }

  // 初始化一个排序二叉树
  def initSBTree(arr: Array[Int]): AVLTree[Int] = {
    val tree: AVLTree[Int] = new AVLTree[Int]
    arr.foreach(x => {
      tree.add(x)
    })
    tree
  }

}

// AVL树的节点
class AVLNode[T: Ordering](var value: T) {
  // 从冥界召唤可以比较 T 类型的比较器(Ordering)
  private val orderValueT: Ordering[T] = implicitly[Ordering[T]]
  // 父节点
  var p: AVLNode[T] = _
  // 左节点
  var left: AVLNode[T] = _
  // 右节点
  var right: AVLNode[T] = _

  // 插入节点
  def add(ele: T): Unit = {
    // 如果小于等于当前值, 则在左边添加
    if (orderValueT.lteq(ele, value)) {
      // 如果左节点是 null, 则左节点置为新节点
      if (left == null) {
        left = new AVLNode[T](ele)
        left.p = this
      }
      else // 否则递归的在左节点添加节点
        left.add(ele)
    } else { // 否则向右边添加
      if (right == null) {
        right = new AVLNode[T](ele)
        right.p = this
      }
      else right.add(ele)
    }

    // 对树进行旋转
    rotate()
  }

  // 对树进行旋转
  def rotate(): Unit ={
    // 左左 -> 右旋。平衡因子大于1，左边高
    if ((this.leftHigh() - this.rightHigh()).abs > 1 && this.left.rightHigh() - this.left.leftHigh() < 0){
      println(value + "左左...")
      rightRotate()
    }

    // 右右 -> 左旋。平衡因子大于1，右边高
    if ((this.leftHigh() - this.rightHigh()).abs > 1 && this.right.rightHigh() - this.right.leftHigh() > 0){
      println(value + "右右...")
      leftRotate()
    }

    // 左右 -> 左旋+右旋。平衡因子大于1，当前节点的左节点是右边高
    if ((this.leftHigh() - this.rightHigh()).abs > 1 && this.left.rightHigh() - this.left.leftHigh() > 0){
      println(value + "左右...")
      // 先对当前节点的左节点做左旋
      this.left.leftRotate()
      // 再对当前节点做右旋
      rightRotate()
    }

    // 右左 -> 右旋+左旋.平衡因子大于1，当前节点的右节点是左边高
    if ((this.leftHigh() - this.rightHigh()).abs > 1 && this.right.rightHigh() - this.right.leftHigh() < 0){
      println(value + "右左...")
      // 先对当前节点的右节点做右旋
      this.right.rightRotate()
      // 再对当前节点做左旋
      leftRotate()
    }
  }

  // 左旋
  def leftRotate(): Unit = {
    // 1.先缓存需要交换的节点信息
    val tempP = p
    val tempR = right
    val tempRL = right.left

    // 2.进行交换
    // 让当前节点的右指针指向原来右节点的左节点
    this.right = tempRL
    // 让原来右节点的左指针指向当前节点
    tempR.left = this
    // 让父节点指向原来的右节点
    if (tempP != null && tempP.left == this){ // 当前节点为父节点的左孩子
      tempP.left = tempR
    }else if (tempP != null){   // 当前节点为父节点的右孩子
      tempP.right = tempR
    }
    // 建立父节点的关系
    if (tempRL != null) tempRL.p = this
    this.p = tempR
    tempR.p = p
  }

  // 右旋
  def rightRotate(): Unit = {
    // 1. 先缓存需要交换的节点信息
    val tempP = p
    val tempL = this.left
    val tempLR = tempL.right // 有可能不存在

    // 2. 旋转
    // 将当前节点的左指针指向当前节点左节点的右孩子
    this.left = tempLR
    // 将原来左节点的右指针指向当前节点
    tempL.right = this
    // 让父节点指向原来左节点
    if(tempP != null && tempP.left == this){
      tempP.left = tempL
    }else if (tempP != null && tempP.right == this){
      tempP.right = tempL
    }
    // 建立父节点的关系
    if (tempLR != null) tempLR.p = this
    this.p = tempL
    tempL.p = p

  }
  /*
  * 计算树的高度
  * */
  def height(): Int = leftHigh().max(rightHigh()) + 1

  // 计算左子树的高度
  def leftHigh(): Int = if (left == null) -1 else left.height()
  // 计算右子树的高度
  def rightHigh(): Int = if (right == null) -1 else right.height()

  // 中序遍历节点
  def infixForeach(op: T => Unit): Unit = {
    if (left != null) left.infixForeach(op)
    op(value)
    if (right != null) right.infixForeach(op)
  }

  // 查找节点
  def searchNode(ele: T): AVLNode[T] = {
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
class AVLTree[T: Ordering] {
  // 排序二叉树的根节点
  var root: AVLNode[T] = _

  // 向排序二叉树中添加节点
  def add(ele: T): Unit = {
    // 如果 root 节点为 null, 则把元素置为 root 位置
    if (root == null)
      root = new AVLNode[T](ele)
    else {
      // 如果 root 节点不为空则调用 root 的 add 方法来添加元素
      root.add(ele)

      // 注意：平衡后需要重新寻找根节点
      if (root.p != null){
        root = root.p
      }
    }
  }

  // 中序遍历二叉树
  def infixForeach(op: T => Unit): Unit = {
    if (root == null)
      println("二叉排序树为空！！！")
    else
      root.infixForeach(op)
  }

  // 查找节点
  def searchNode(ele: T): AVLNode[T] = {
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

  // 计算树的高度
  def height(): Int = if (root == null) -1 else root.height()
}

