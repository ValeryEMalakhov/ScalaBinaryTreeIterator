package com.prb.f1

object NodeUtils {

  implicit class NodeOps[T](node: Node[T]) {

    def foreach(visitor: T => Unit): Unit = {

      if (node.size != 0) {

        var cur: Node[T] = node
        var blockLeftBranch: Boolean = false

        visitor(cur.value)

        while (cur != null) {

          if (cur.hasLeft && !blockLeftBranch) {
            cur = cur.left.get
            visitor(cur.value)

          } else if (cur.hasRight) {
            cur = cur.right.get
            visitor(cur.value)
            //  if turned to the right, we can again turn to the left
            blockLeftBranch = false

          } else {

            //  if turned up a level, we can't turn to the left
            blockLeftBranch = true

            while (cur.hasParent && cur.parent.get.hasRight && cur.parent.get.right.get == cur) {
              cur = cur.parent.get
            }

            //  root blocker on the return way
            if (cur.hasParent) cur = cur.parent.get
            else cur = null
          }
        }
      }
    }
  }

}
