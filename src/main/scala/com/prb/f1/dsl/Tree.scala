package com.prb.f1.dsl

trait Node[T] {
  val value: T
}

case class Leaf[T](override val value: T) extends Node[T]

case class LBNode[T](override val value: T, left: Node[T]) extends Node[T]

case class RBNode[T](override val value: T, right: Node[T]) extends Node[T]

case class CNode[T](override val value: T, left: Node[T], right: Node[T]) extends Node[T]
