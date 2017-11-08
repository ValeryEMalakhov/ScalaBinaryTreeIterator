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
      tailRecTreeTraversal(visitor, Some(node))
    }

    @tailrec
    private def tailRecTreeTraversal(
        visitor: T => Unit,
        curNode: Option[Node[T]]): Unit = {

      if (curNode.flatMap(cur => cur.left.map(l => l)).isDefined) {

        curNode.foreach(cur => cur.left.foreach(lVal => visitor(lVal.value)))

        tailRecTreeTraversal(visitor, curNode.flatMap(cur =>
          cur.left.map(l => l.copy(parent = curNode.map(cur =>
            cur.copy(left = None))))))

      } else if (curNode.flatMap(cur => cur.right.map(r => r)).isDefined) {

        curNode.foreach(cur => cur.right.foreach(rVal => visitor(rVal.value)))

        tailRecTreeTraversal(visitor, curNode.flatMap(cur => cur.right.map(r =>
          r.copy(parent = curNode.map(cur =>
            cur.copy(right = None))))))

      } else if (curNode.flatMap(cur => cur.parent.map(p => p)).isDefined) {
        tailRecTreeTraversal(visitor, curNode.get.parent)
      }
    }
  }

}
