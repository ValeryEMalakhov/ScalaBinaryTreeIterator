package com.prb.f1

import scala.collection.mutable._

import org.specs2._

import com.prb.f1.dsl._
import com.prb.f1.NodeUtils._

class NodeUtilsSpecTest extends mutable.Specification {

  "A tree that consists of" >> {

    "nothing must return nothing" >> {
      var resultList = new ListBuffer[Int]()

      None.foreach(x => resultList += x)

      resultList must beEmpty
    }

    "only one Leaf must return only its" >> {
      val newTree: Node[Int] = Node.convertToParentedTree(Leaf(99))
      var resultList = new ListBuffer[Int]()

      newTree.foreach(x => resultList += x)

      resultList must_== ListBuffer[Int](99)
    }

    "different nodes must return values in DFS pre-order traversal" >> {
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
      var resultList = new ListBuffer[Int]()

      newTree.foreach(x => resultList += x)

      resultList must_== ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8,
        9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23)
    }
  }

}
