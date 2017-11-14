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

    // Tree visualisation
    /*
                      /1\
              /2\             /15\
         /3\       11\    16\      /18\
     /4\     /7\    /12\    17    19  20\
    5   6  /8  10  13  14              /21\
          9                           22  23
    */

    @tailrec
    private def tailRecTreeTraversal(
        visitor: T => Unit,
        curNode: Option[Node[T]]): Unit = {

      curNode match {
        case Some(cur) =>
          cur match {
            // if isLeaf, try to get the parent Node
            case Node(_, None, None, parent, _) =>
              tailRecTreeTraversal(visitor, parent)

            // if right Node exist - go right and delete this way from parent Node
            case Node(_, None, right, _, _) =>
              right.foreach(r => visitor(r.value))
              right.foreach(r => r.parent = Some(Node(cur.value, cur.left, None, cur.parent, cur.childCount)))
              tailRecTreeTraversal(visitor, right)

            // if left Node exist - go left and delete this way from parent Node
            case Node(_, left, _, _, _) =>
              left.foreach(l => visitor(l.value))
              left.foreach(l => l.parent = Some(Node(cur.value, None, cur.right, cur.parent, cur.childCount)))
              tailRecTreeTraversal(visitor, left)
          }
        case None =>
      }
    }
  }

}
