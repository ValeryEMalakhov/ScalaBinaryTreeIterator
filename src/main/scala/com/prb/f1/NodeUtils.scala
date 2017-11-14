package com.prb.f1

import scala.annotation.tailrec

object NodeUtils {

  implicit class NodeOps[T](node: Node[T]) {

    /**
      * Apply the given procedure $visitor to the option's value,
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

      curNode match {
        case None =>

        // if isLeaf, try to get the parent Node
        case Some(Node(_, None, None, parent, _)) =>
          tailRecTreeTraversal(visitor, parent)

        // if right Node exist - go right and delete this way from parent Node
        case Some(Node(value, None, right, parent, childCount)) =>
          right.foreach(r => visitor(r.value))
          right.foreach(r => r.parent = Some(Node(value, None, None, parent, childCount)))
          tailRecTreeTraversal(visitor, right)

        // if left Node exist - go left and delete this way from parent Node
        case Some(Node(value, left, right, parent, childCount)) =>
          left.foreach(l => visitor(l.value))
          left.foreach(l => l.parent = Some(Node(value, None, right, parent, childCount)))
          tailRecTreeTraversal(visitor, left)
      }
    }
  }

}
