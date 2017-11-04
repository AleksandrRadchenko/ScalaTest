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
    }
  }
  test("insert test") {
    new TestTrees {
      val t: List[(Char, Bit)] = insert(List(('a', 1), ('b', 1)), 'a')
      println(t)
    }
  }



}
