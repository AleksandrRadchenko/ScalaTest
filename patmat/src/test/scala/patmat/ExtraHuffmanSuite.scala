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
        makeCodeTree(Leaf('x', 1), Leaf('e', 1)), Leaf('t', 2))
      assert(sampleTree == Fork(Fork(Leaf('x',1),Leaf('e',1),List('x', 'e'),2),Leaf('t',2),List('x', 'e', 't'),4))
    }
  }

  test("times test") {
    new TestTrees {
      val t: List[(Char, Bit)] = times(List('a', 'b', 'a'))
      assert(t == List(('a', 2), ('b', 1)))
      val t3: List[(Char, Bit)] = times(List('a', 'b', 'b', 'g', 'g', 'b', 'a'))
      assert(t3 == List(('a', 2), ('b', 3), ('g', 2)))
    }
  }
  test("insert test") {
    new TestTrees {
      val t: List[(Char, Bit)] = insert(List(('a', 1), ('b', 1)), 'a')
      assert(t == List(('a', 2), ('b', 1)))
    }
  }
  test("makeOrderedLeafList test") {
    new TestTrees {
      val t: List[(Char, Int)] = times(List('a', 'b', 'b', 'g', 'g', 'b', 'a'))
      assert(makeOrderedLeafList(t) == List(Leaf('a',2), Leaf('g',2), Leaf('b',3)))
    }
  }

  test("combine test") {
    new TestTrees {
      private val ordered = List(Leaf('g',1), Leaf('a',3), Leaf('b',5))
      private val expected = List(
        Fork(Leaf('g',1), Leaf('a',3), List('g', 'a'), 4),
        Leaf('b',5)
      )
      assert(combine(ordered) == expected)
    }
  }

  test("insertNode test") {
    new TestTrees {
      private val ordered = List(Leaf('g',1), Leaf('a',3), Leaf('b',5))
      private val newNode = Leaf('u', 2)
      assert(insertNode(ordered, newNode) == List(Leaf('g',1), Leaf('u', 2), Leaf('a',3), Leaf('b',5)))
    }
  }

  test("fullyCombined test") {
    new TestTrees {
      private val ordered = List(Leaf('g',1), Leaf('a',3), Leaf('b',5))
      private val expected = List(Fork(Fork(Leaf('g',1), Leaf('a',3), List('g', 'a'), 4), Leaf('b',5), List('g', 'a', 'b'), 9))
      private val fullyCombined = until(singleton, combine)(ordered)
      assert(fullyCombined == expected)
    }
  }

  test("createCodeTree test") {
    new TestTrees {
      private val chars = List('a', 'b', 'b', 'g', 'b', 'a', 'b', 'a', 'b')
      private val expected = Fork(Fork(Leaf('g',1), Leaf('a',3), List('g', 'a'), 4), Leaf('b',5), List('g', 'a', 'b'), 9)
      private val codeTree = createCodeTree(chars)
      assert(codeTree == expected)
    }
  }

  test("createCodeTree2 test") {
    new TestTrees {
      private val chars = List()
      private val codeTree = intercept[IllegalArgumentException](createCodeTree(chars))
    }
  }

  test("decode test") {
    new TestTrees {
      private val bits = List(1,0,1,1)
      private val codeTree = Fork(Leaf('b', 5), Fork(Leaf('a', 3), Leaf('g', 1), List('a', 'g'), 4), List('b', 'a', 'g'), 9)
      assert(decode(codeTree, bits) == "ag".toList)
    }
  }

  test("decodedSecret test") {
    new TestTrees {
      println(decodedSecret.mkString)
    }
  }

  test("charToCode test") {
    new TestTrees {
      private val codeTree = Fork(Leaf('b', 5), Fork(Leaf('a', 3), Leaf('g', 1), List('a', 'g'), 4), List('b', 'a', 'g'), 9)
      private val charA = 'a'
      private val charG = 'g'
      private val charB = 'b'
      assert(charToCode(codeTree, charA) == List(1,0))
      assert(charToCode(codeTree, charG) == List(1,1))
      assert(charToCode(codeTree, charB) == List(0))
    }
  }

  test("encode test") {
    new TestTrees {
      private val codeTree = Fork(Leaf('b', 5), Fork(Leaf('a', 3), Leaf('g', 1), List('a', 'g'), 4), List('b', 'a', 'g'), 9)
      private val text = "agbgb".toList
      assert(encode(codeTree)(text) == List(1,0,1,1,0,1,1,0))
    }
  }

  test("convert test") {
    new TestTrees {
      private val codeTree = Fork(Leaf('b', 5), Fork(Leaf('a', 3), Leaf('g', 1), List('a', 'g'), 4), List('b', 'a', 'g'), 9)
      private val codeTable = List(('b', List(0)), ('a', List(1,0)), ('g', List(1,1)))
      assert(convert(codeTree) == codeTable)
    }
  }

  test("quickEncode test") {
    new TestTrees {
      private val codeTree = Fork(Leaf('b', 5), Fork(Leaf('a', 3), Leaf('g', 1), List('a', 'g'), 4), List('b', 'a', 'g'), 9)
      private val text = "agbgb".toList
      assert(quickEncode(codeTree)(text) == List(1,0,1,1,0,1,1,0))
    }
  }

}
