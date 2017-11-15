package com.prb.f1

import org.specs2._

import com.prb.f1.dsl._

class NodeSpecTest extends mutable.Specification {

  "When DSL Node contains" >> {

    "single Leaf must return Node only with root" >> {
      Node.convertToParentedTree(Leaf(99)) must_==
        Node[Int](99, None, None, None)
    }

    "CNode, where" >> {

      val myTree: CNode[Int] = CNode(99, Leaf(0), Leaf(1))
      val newTree: Node[Int] = Node.convertToParentedTree(myTree)

      "the root value must return the first parameter of the Node" >> {
        newTree.value must_== 99
      }
      "the left Leaf should have valid parent" >> {
        newTree.left.get.parent.get.value must_== newTree.value
      }
      "and its value must return the second parameter of the Node" >> {
        newTree.left.get.value must_== 0
      }
      "the right Leaf should have valid parent" >> {
        newTree.right.get.parent.get.value must_== newTree.value
      }
      "and its value must return the third parameter of the Node" >> {
        newTree.right.get.value must_== 1
      }
    }

    "LBNode, where" >> {

      val myTree: LBNode[Int] = LBNode(99, Leaf(0))
      val newTree: Node[Int] = Node.convertToParentedTree(myTree)

      "the root value must return the first parameter of the Node" >> {
        newTree.value must_== 99
      }
      "the left Leaf should have valid parent" >> {
        newTree.left.get.parent.get.value must_== newTree.value
      }
      "and its value must return the second parameter of the Node" >> {
        newTree.left.get.value must_== 0
      }
      "the right Leaf must be `None`" >> {
        newTree.right must beNone
      }
    }

    "RBNode, where" >> {

      val myTree: RBNode[Int] = RBNode(99, Leaf(1))
      val newTree: Node[Int] = Node.convertToParentedTree(myTree)

      "the root value must return the first parameter of the Node" >> {
        newTree.value must_== 99
      }
      "the left Leaf must be `None`" >> {
        newTree.left must beNone
      }
      "the right Leaf should have valid parent" >> {
        newTree.right.get.parent.get.value must_== newTree.value
      }
      "and its value must return the third parameter of the Node" >> {
        newTree.right.get.value must_== 1
      }
    }
  }
}
