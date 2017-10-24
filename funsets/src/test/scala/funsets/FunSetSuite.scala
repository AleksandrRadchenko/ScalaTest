package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
  * This class is a test suite for the methods in object FunSets. To run
  * the test suite, you can either:
  *  - run the "test" command in the SBT console
  *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
  */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
    * Link to the scaladoc - very clear and detailed tutorial of FunSuite
    *
    * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
    *
    * Operators
    *  - test
    *  - ignore
    *  - pending
    */

  /**
    * Tests are written using the "test" operator and the "assert" method.
    */
  //   test("string take") {
  //     val message = "hello, world"
  //     assert(message.take(5) === "hell")
  //   }

  /**
    * For ScalaTest tests, there exists a special equality operator "===" that
    * can be used inside "assert". If the assertion fails, the two values will
    * be printed in the error message. Otherwise, when using "==", the test
    * error message will only say "assertion failed", without showing the values.
    *
    * Try it out! Change the values so that the assertion fails, and look at the
    * error message.
    */
  //   test("adding ints") {
  //     assert(1 + 2 === 3)
  //   }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tescts. For instane, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    * val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes? Then
    * the test methods are not even executed, because creating an instance of the
    * test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    *
    */

  trait TestSets {
    val s1: Set = singletonSet(1)
    val s2: Set = singletonSet(2)
    val s3: Set = singletonSet(3)
    val s10: Set = (x: Int) => (-10 <= x) && (x <= 10)
    val diff10vs3: Set = diff(s10, union(s1, union(s2, s3)))
    val s20: Set = (x: Int) => (-20 <= x) && (x <= 20)
  }

  /**
    * This test is currently disabled (by using "ignore") because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", exchange the
    * function "ignore" by "test".
    */
  test("singletonSet(1) contains 1") {

    /**
      * We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets {
      /**
        * The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(s1, 1), "Singleton")
      assert(contains(s2, 2), "Singleton")
      assert(contains(s3, 3), "Singleton")
      assert(contains(s10, 3), "10")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection contains common elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      val s_intesect = intersect(s, s2)
      assert(!contains(s_intesect, 1), "Intersect 1")
      assert(contains(s_intesect, 2), "Intersect 2")
      assert(!contains(s_intesect, 3), "Intersect 3")
      val ss1 = intersect(s1, s1)
      assert(contains(ss1, 1), "Intersect 11")

      assert(contains(intersect(s10, s2), 2))
    }
  }

  test("diff contains elements of s wich not belongs to t") {
    new TestSets {
      val s = union(s1, s2)
      val s_diff = diff(s, s2)
      assert(contains(s_diff, 1), "Diff 1")
      assert(!contains(s_diff, 2), "Diff 2")
      assert(!contains(s_diff, 3), "Diff 3")
      val ss1 = diff(s1, s1)
      assert(!contains(ss1, 1), "Diff 11")

      assert(!contains(diff10vs3, 1))
      assert(!contains(diff10vs3, 2))
      assert(!contains(diff10vs3, 3))
      assert(contains(diff10vs3, 6))
      assert(contains(diff10vs3, -6))
    }
  }

  test("filter contains elements of both sets") {
    new TestSets {
      val s = union(s1, s2)
      val s_filter = filter(s, (x: Int) => x == 2)
      assert(!contains(s_filter, 1), "Diff 1")
      assert(contains(s_filter, 2), "Diff 2")
      assert(!contains(s_filter, 3), "Diff 3")
    }
  }

  test("forall") {
    new TestSets {
      val s: Set = union(s1, s2)
      assert(forall(s1, s), "Forall 1")
      assert(forall(s2, s), "Forall 2")
      assert(!forall(s3, s), "not forall 3")
      assert(forall(s10, s20), "Forall 10 20")
      assert(!forall(s20, s10), "Not Forall 20 10")
    }
  }

  test("exists") {
    new TestSets {
      val s: Set = union(s1, s2)
      assert(exists(s, s1), "exists 1")
      assert(exists(s, s2), "exists 2")
      assert(!exists(s, s3), "not exists 3")
      assert(exists(s10, s20), "exists 10 20")
      assert(exists(s20, s10), "exists 20 10")
    }
  }

  test("map") {
    new TestSets {
      val s: Set = union(s1, s2)
      val s10plus5 : Set = map(s10, (x: Int) => x + 5)
      val s10sqr2 : Set = map(s10, (x: Int) => x * x)
      val s4 = singletonSet(4)
      val s5 = singletonSet(5)
      val s7 = singletonSet(7)
      val s1000 = singletonSet(1000)
      val customSet: Set = union(s1, union(s3, union(s4, union(s5, union(s7, s1000)))))
      val customSetMinus1: Set = map(customSet, (x: Int) => x - 1)
      assert(contains(s10plus5, 1), "contains 1")
      assert(!contains(s10plus5, -8), "not contains -8")
      assert(contains(s10sqr2, 49), "sqr contains 49")
      assert(!contains(s10sqr2, 121), "sqr !contains 121")
      assert(contains(customSetMinus1, 999), "custSet-1 contains 999")
      assert(contains(customSetMinus1, 0), "custSet-1 contains 0")
    }
  }


}
