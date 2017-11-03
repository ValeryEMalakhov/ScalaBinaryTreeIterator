package com.prb.f1

import scala.annotation.tailrec

object NodeUtils {

  implicit class NodeOps[T](node: Node[T]) {

    def foreach(visitor: T => Unit): Unit = {

      visitor(node.value)
      treeTailRec(visitor, node, null)
    }

    @tailrec private def treeTailRec(f: T => Unit, curNode: Node[T], preNode: Node[T]): Unit = {

      if (curNode != null) {

        if (curNode.hasLeft & (curNode.parent.contains(preNode) || preNode == null)) {
          f(curNode.left.get.value)
          treeTailRec(f, curNode.left.get, curNode)

        } else if (curNode.hasRight & !curNode.right.contains(preNode)) {
          f(curNode.right.get.value)
          treeTailRec(f, curNode.right.get, curNode)

        } else if (curNode.hasParent) {
          treeTailRec(f, curNode.parent.get, curNode)

        } else {
          val _curNode: Node[T] = null
          treeTailRec(f, _curNode, _curNode)
          //  treeTailRec(f, null, null)
        }
      }
    }
  }

}
