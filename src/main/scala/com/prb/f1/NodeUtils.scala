package com.prb.f1

object NodeUtils {

  implicit class NodeOps[T](node: Node[T]) {

    def foreach(visitor: T => Unit): Unit = {

      if (node.size != 0) {

        var cur: Option[Node[T]] = Some(node)
        var tmp: Option[Node[T]] = None
        var blockLeftBranch: Boolean = false

        if (cur.isDefined) visitor(cur.get.value)

        while (cur.isDefined) {

          if (cur.get.hasLeft & !blockLeftBranch) {

            cur = cur.get.left
            if (cur.isDefined) visitor(cur.get.value)

          } else if (cur.get.hasRight & cur.get.right != tmp) {

            cur = cur.get.right
            if (cur.isDefined) visitor(cur.get.value)
            blockLeftBranch = false

          } else if (cur.get.parent.isDefined) {
            tmp = cur
            cur = cur.get.parent
            blockLeftBranch = true

          } else cur = None
        }
      }
    }
  }

}
