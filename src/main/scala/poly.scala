package scalamuc

trait Poly {
  def apply[A, B](a: A)(implicit C: this.Case[A, B]): B = C(a)

  class MkPoly[A] {
    def apply[B](f: A => B): Case[A, B] = new Case[A, B] {
      def apply(a: A) = f(a)
    }
  }
  def at[A] = new MkPoly[A]

  trait Case[A, B] {
    def apply(a: A): B
  }
}

object addOne extends Poly {
  implicit val intCase = at[Int] { a => a + 1 }
  implicit val stringCase = at[String] { s => s + "1" }
}
