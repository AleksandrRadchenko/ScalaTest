package patmat

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class ExtraHuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("makeCodeTree test") {
    new TestTrees {
      val sampleTree: Fork = makeCodeTree(
        makeCodeTree(Leaf('x', 1), Leaf('e', 1)),
        Leaf('t', 2)
      )
      println(sampleTree)
    }
  }

  test("times test") {
    new TestTrees {
      val t: List[(Char, Bit)] = times(List('a', 'b', 'a'))
      println(t)
      val t3: List[(Char, Bit)] = times(List('a', 'b', 'b', 'g', 'g', 'b', 'a'))
      println(t3)
    }
  }
  test("insert test") {
    new TestTrees {
      val t: List[(Char, Bit)] = insert(List(('a', 1), ('b', 1)), 'a')
      println(t)
    }
  }
  test("makeOrderedLeafList test") {
    new TestTrees {
      val t: List[(Char, Bit)] = times(List('a', 'b', 'b', 'g', 'g', 'b', 'a'))
      println(t)
      val ordered = makeOrderedLeafList(t)
      println(ordered)
    }
  }

  test("combine test") {
    new TestTrees {
      private val ordered = List(Leaf('b',5), Leaf('a',3), Leaf('g',1))
      private val expected = List(
        Leaf('b',5),
        Fork(Leaf('a',3), Leaf('g',1), List('a', 'g'), 4))
      assert(combine(ordered) == expected)
    }
  }

  test("insertNode test") {
    new TestTrees {
      private val ordered = List(Leaf('b',5), Leaf('a',3), Leaf('g',1))
      private val newNode = Leaf('u', 2)
      //      println(insertNode(ordered, newNode))
      assert(insertNode(ordered, newNode) == List(Leaf('b',5), Leaf('a',3), Leaf('u', 2), Leaf('g',1)))
    }
  }

  test("fullyCombined test") {
    new TestTrees {
      private val ordered = List(Leaf('b',3), Leaf('a',2), Leaf('g',2))
      private val expected = List(makeCodeTree(Fork(Leaf('a', 2), Leaf('g', 2), List('a', 'g'), 4), Leaf('b', 3)))
//      println("Ordered = " + ordered)
//      println("Expected = " + expected)
      val fullyCombined = until(singleton, combine)(ordered)
//      println("Fully Combined = " + fullyCombined)
      assert(fullyCombined == expected)
    }
  }

  test("fullyCombined test2") {
    new TestTrees {
      private val ordered = List(Leaf('b',5), Leaf('a',3), Leaf('g',1))
      private val expected = List(Fork(Leaf('b', 5), Fork(Leaf('a', 3), Leaf('g', 1), List('a', 'g'), 4), List('b', 'a', 'g'), 9))
//      println("Ordered =        " + ordered)
//      println("Expected =       " + expected)
      val fullyCombined = until(singleton, combine)(ordered)
//      println("Fully Combined = " + fullyCombined)
      assert(fullyCombined == expected)
    }
  }

}
