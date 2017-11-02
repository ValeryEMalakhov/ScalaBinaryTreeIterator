package com.prb.f1

import org.junit._
import org.junit.Assert._

import com.prb.f1.dsl._

class NodeTest {

  @Before
  def initialize(): Unit = {

  }

  //  When Tree is only leaf
  @Test
  def testWithLeaf(): Unit = {

    val myTree: Leaf[Int] = Leaf(99)
    val expectedTree: Node[Int] = Node(99, None, None, None)

    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    assertEquals(expectedTree, newTree)
  }

  //  When Tree contains CNode
  @Test
  def testCNodeWithLeftParent(): Unit = {

    val myTree: CNode[Int] = CNode(99, Leaf(0), Leaf(1))
    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    assertEquals(newTree.left.get.parent.get.value, newTree.value)
  }

  //  When Tree contains CNode
  @Test
  def testCNodeWithRightParent(): Unit = {

    val myTree: CNode[Int] = CNode(99, Leaf(0), Leaf(1))
    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    assertEquals(newTree.right.get.parent.get.value, newTree.value)
  }

  //  When Tree start with LBNode (left child)
  @Test
  def testLBNodeWithLeftParent(): Unit = {

    val myTree: LBNode[Int] = LBNode(99, Leaf(0))
    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    assertEquals(newTree.left.get.parent.get.value, newTree.value)
  }

  //  When Tree start with LBNode (right child)
  @Test
  def testLBNodeWithRightParent(): Unit = {

    val myTree: LBNode[Int] = LBNode(99, Leaf(0))
    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    assertEquals(newTree.right, None)
  }

  //  When Tree start with LBNode (right child)
  @Test
  def testRBNodeWithRightParent(): Unit = {

    val myTree: RBNode[Int] = RBNode(99, Leaf(1))
    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    assertEquals(newTree.right.get.parent.get.value, newTree.value)
  }

  //  When Tree start with LBNode (left child)
  @Test
  def testRBNodeWithLeftParent(): Unit = {

    val myTree: RBNode[Int] = RBNode(99, Leaf(1))
    val newTree: Node[Int] = Node.convertToParentedTree(myTree)

    assertEquals(newTree.left, None)
  }

}