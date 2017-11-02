package com.prb.f1

import scala.collection.mutable._

import org.junit._
import org.junit.Assert._

import com.prb.f1.dsl._
import com.prb.f1.NodeUtils._

class NodeUtilsTest {


  //  When Tree is empty
  @Test
  def testTreeEmpty(): Unit = {

    //    val myTree = Node(Option.empty[Int])
    //    val newTree: Node[Int] = Node.convertToParentedTree(myTree)
    //
    //    var expectedTreeList = new ListBuffer[Int]()
    //
    //    var resultList = new ListBuffer[Int]()
    //
    //    newTree.foreach(x => resultList += x)
    //
    //    assertEquals(expectedTreeList, resultList)
  }


  //  When Tree contains only one node
  @Test
  def testTreeWithOneLeaf(): Unit = {

    val myTree: Leaf[Int] = Leaf(99)

    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    var expectedTreeList = new ListBuffer[Int]()

    expectedTreeList += 99

    var resultList = new ListBuffer[Int]()

    newTree.foreach(x => resultList += x)

    assertEquals(expectedTreeList, resultList)
  }

  //  When Tree contains CNode
  @Test
  def testTreeWithDefault(): Unit = {

    val myTree: CNode[Int] = CNode(1,
      CNode(2,
        Leaf(3),
        LBNode(4,
          Leaf(0)
        )
      ),
      CNode(5,
        Leaf(6),
        LBNode(7,
          Leaf(12)
        )
      )
    )
    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    var expectedTreeList = new ListBuffer[Int]()

    expectedTreeList += (1, 2, 3, 4, 0, 5, 6, 7, 12)

    var resultList = new ListBuffer[Int]()

    newTree.foreach(x => resultList += x)

    assertEquals(expectedTreeList, resultList)
  }

  //  When Tree contains big CNode
  @Test
  def testTreeWithBig(): Unit = {

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

    var expectedTreeList = new ListBuffer[Int]()

    expectedTreeList += (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
      15, 16, 17, 18, 19, 20, 21, 22, 23)

    var resultList = new ListBuffer[Int]()

    newTree.foreach(x => resultList += x)

    assertEquals(expectedTreeList, resultList)
  }
}
