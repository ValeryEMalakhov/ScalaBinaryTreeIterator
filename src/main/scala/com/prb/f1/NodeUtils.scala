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
      tailRecTreeTraversalOld(visitor, Some(node))
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

    // It is not tail-recursive, because the function call is not the last action
    private def recTreeTraversal(
        visitor: T => Unit,
        curNode: Option[Node[T]]): Unit = {

      /**
        * If the current Node has the left child,
        * apply the given procedure $visitor to the `left` Node,
        * then recursively call $recTreeTraversal
        * with changing `left` parent to the None */
      curNode.flatMap(cur => cur.left).foreach { leftNode =>

        visitor(leftNode.value)
        recTreeTraversal(visitor, Some(leftNode.copy(parent = None)))
      }
      // The same as for `left`, but only for` right`
      curNode.flatMap(cur => cur.right).foreach { rightNode =>

        visitor(rightNode.value)
        recTreeTraversal(visitor, Some(rightNode.copy(parent = None)))
      }
      // Otherwise, try to get a parent or complete the current iteration
      curNode.flatMap(cur => cur.parent).foreach { parentNode =>

        recTreeTraversal(visitor, Some(parentNode))
      }
    }

    @tailrec
    private def tailRecTreeTraversalOld(
        visitor: T => Unit,
        curNode: Option[Node[T]]): Unit = {

      // Go down the left branch if it exists
      if (curNode.flatMap(cur => cur.left).isDefined) {

        // apply the $visitor to the `left` Node
        curNode.foreach(cur => cur.left.foreach(lVal => visitor(lVal.value)))

        // recursively call $tailRecTreeTraversalOld
        //  with changing parent `left` Node to the None
        tailRecTreeTraversalOld(visitor,
          curNode.flatMap(cur => cur.left.map(l =>
            l.copy(parent = curNode.map(cur => cur.copy(left = None))))))

      } // Else go down the right branch if it exists
      else if (curNode.flatMap(cur => cur.right).isDefined) {

        // apply the $visitor to the `right` Node
        curNode.foreach(cur => cur.right.foreach(rVal => visitor(rVal.value)))

        // recursively call $tailRecTreeTraversalOld
        //  with changing parent `right` Node to the None
        tailRecTreeTraversalOld(visitor,
          curNode.flatMap(cur => cur.right.map(r =>
            r.copy(parent = curNode.map(cur => cur.copy(right = None))))))

      } // Else returns to the parent
      else if (curNode.flatMap(cur => cur.parent).isDefined) {
        tailRecTreeTraversalOld(visitor,
          curNode.flatMap(cur => cur.parent))

      } // Otherwise, the tree traversal is completed
    }
  }

}
