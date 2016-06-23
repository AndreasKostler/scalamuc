package scalamuc

import org.scalatest.junit.JUnitSuite

import org.junit.Test
import org.junit.Assert._

class CoproductTest extends JUnitSuite {
  type ISB = Int :+: String :+: Boolean :+: CNil

  def assertTypedEquals[A](expected: A, actual: A): Unit = assertEquals(expected, actual)

  /*
  @Test
  def testInject {
    val foo1 = Coproduct[ISB](23)
    val foo2 = Coproduct[ISB]("foo")
    val foo3 = Coproduct[ISB](true)

    assertTypedEquals(Inl(23), foo1)
    assertTypedEquals(Inr(Inl("foo")), foo2)
    assertTypedEquals(Inr(Inr(Inl(true))), foo3)
  }
   */

  /*
  @Test
  def testMap {
    type C = Int :+: String :+: CNil
    val foo1 = Coproduct[C](42)
    val foo2 = Coproduct[C]("foo")

    assertTypedEquals(Inl(43), foo1 map addOne)
    assertTypedEquals(Inr(Inl("foo1")), foo2 map addOne)
  }
   */

  /*
  @Test
  def testGCD {
    implicitly[GCD.Aux[_10, _5, _5]]
    implicitly[GCD.Aux[_9, _6, _3]]
  }
   */

}
