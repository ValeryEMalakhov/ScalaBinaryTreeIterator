package com.prb.f1

import scala.annotation.tailrec

object NodeUtils {

  implicit class NodeOps[T](node: Node[T]) {

    /** Apply the given procedure $visitor to the option's value,
      * if it is nonempty. Otherwise, apply the given procedure
      * to the empty root value.
      *
      * @param  visitor the procedure to apply.
      */
    def foreach(visitor: T => Unit): Unit = {

      visitor(node.value)
      foreachTailRec(visitor, Some(node), None)
    }

    @tailrec
    private def foreachTailRec(
        visitor: T => Unit,
        curNode: Option[Node[T]],
        preNode: Option[Node[T]]): Unit = {

      if (curNode.isDefined) {

        if (curNode.get.hasLeft & (curNode.get.parent == preNode || preNode.isEmpty)) {
          visitor(curNode.get.left.get.value)
          foreachTailRec(visitor, curNode.get.left, curNode)

        } else if (curNode.get.hasRight & curNode.get.right != preNode) {
          visitor(curNode.get.right.get.value)
          foreachTailRec(visitor, curNode.get.right, curNode)

        } else if (curNode.get.hasParent) {
          foreachTailRec(visitor, curNode.get.parent, curNode)

        } else {
          foreachTailRec(visitor, None, None)
        }
      }
    }
  }

}
