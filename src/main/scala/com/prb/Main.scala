package com.prb

import com.prb.f1.Node
import com.prb.f1.NodeUtils._
import com.prb.f1.dsl._

object Main extends App {

  val myTree: CNode[Int] = CNode(1,
    CNode(2,
      CNode(3,
        CNode(4,
          Leaf(5),
          Leaf(6)
        ),
        CNode(7,
          LBNode(8,
            Leaf(9)
          ),
          Leaf(10)
        )
      ),
      RBNode(11,
        CNode(12,
          Leaf(13),
          Leaf(14)
        ),
      )
    ),
    CNode(15,
      RBNode(16,
        Leaf(17)
      ),
      CNode(18,
        Leaf(19),
        LBNode(20,
          CNode(21,
            Leaf(22),
            Leaf(23)
          )
        )
      )
    )
  )

  val newTree: Node[Int] = Node.convertToParentedTree(myTree)

  println(s"Tree root value: ${newTree.value}\n")
  println(s"Tree root child count: ${newTree.childCount}\n")
  println(s"Tree size: ${newTree.size}\n")

  println(s"Printing tree node/leaf values: ")
  newTree.foreach(x => println(x))

}
