package com.prb.f1

object NodeUtils {

  implicit class NodeOps[T](node: Node[T]) {

    def foreach(visitor: T => Unit): Unit = {

      var curNode: Node[T] = node
      var preNode: Node[T] = null

      visitor(curNode.value)

      while (curNode != null) {

        if (curNode.hasLeft & (curNode.parent.contains(preNode) || preNode == null)) {
          preNode = curNode
          curNode = curNode.left.get
          visitor(curNode.value)

        } else if (curNode.hasRight & !curNode.right.contains(preNode)) {
          preNode = curNode
          curNode = curNode.right.get
          visitor(curNode.value)

        } else if (curNode.hasParent) {
          preNode = curNode
          curNode = curNode.parent.get

        } else curNode = null
      }
    }
  }

}
