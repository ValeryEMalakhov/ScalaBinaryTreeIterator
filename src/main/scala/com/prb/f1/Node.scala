package com.prb.f1

case class Node[T](
    value: T,
    left: Option[Node[T]] = None,
    right: Option[Node[T]] = None,
    var parent: Option[Node[T]] = None,
    var childCount: Int = 0) {

  def hasLeft: Boolean = left.isDefined

  def hasRight: Boolean = right.isDefined

  def hasParent: Boolean = parent.isDefined

  def isLeaf: Boolean = !hasLeft && !hasRight

  def size: Int = childCount + 1
}

object Node {

  /**
    * An implicit conversion that converts a DSL Node to a Node with mutable values
    */
  implicit def convertToParentedTree[T](node: dsl.Node[T]): Node[T] = {
    convertTree(node, None)
  }

  private def convertTree[T](node: dsl.Node[T], parent: Option[Node[T]]): Node[T] = {
    node match {
      case dsl.CNode(a, left1, right1) => {
        val left = convertTree(left1, None)
        val right = convertTree(right1, None)
        val currentNode = Node(a, Some(left), Some(right), parent)
        left.parent = Some(currentNode)
        right.parent = Some(currentNode)

        currentNode.childCount = currentNode.left.get.childCount + currentNode.right.get.childCount + 2
        currentNode
      }
      case dsl.LBNode(a, left1) => {
        val left = convertTree(left1, None)
        val currentNode = Node(a, Some(left), None, parent)
        left.parent = Some(currentNode)

        currentNode.childCount = currentNode.left.get.childCount + 1
        currentNode
      }
      case dsl.RBNode(a, right1) => {
        val right = convertTree(right1, None)
        val currentNode = Node(a, None, Some(right), parent)
        right.parent = Some(currentNode)

        currentNode.childCount = currentNode.right.get.childCount + 1
        currentNode
      }
      case dsl.Leaf(a) =>
        Node(a, None, None, parent)
    }
  }
}

