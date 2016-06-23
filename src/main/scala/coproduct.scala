package scalamuc

sealed trait Coproduct
sealed trait :+:[H, T <: Coproduct] extends Coproduct
sealed trait CNil extends Coproduct

case class Inl[H, T <: Coproduct](head: H) extends :+:[H, T]

case class Inr[H, T <: Coproduct](tail: T) extends :+:[H, T]

object CoproductOps {
  // Inject
  trait Inject[C <: Coproduct, I] {
    def apply(i: I): C
  }
  object Inject {
    implicit def hdCase[H, T <: Coproduct] = new Inject[H :+: T, H] {
      def apply(i: H): H :+: T = Inl(i)
    }
    implicit def tlCase[H, T <: Coproduct, I](implicit tlI: Inject[T, I]) = new Inject[H :+: T, I] {
      def apply(i: I): H :+: T = Inr(tlI(i))
    }
  }

  trait Mapper[P <: Poly, C <: Coproduct] {
    type Out <: Coproduct
    def apply(c: C): Out
  }
  object Mapper {
    implicit def cnilCase[P <: Poly] = new Mapper[P, CNil] {
      type Out = CNil
      def apply(c: CNil): CNil = c
    }

    implicit def cpCase[H, T <: Coproduct, P <: Poly, R](implicit
      hc: P#Case[H, R],
      tm: Mapper[P, T]) = new Mapper[P, H :+: T] {
      type Out = R :+: tm.Out
      def apply(c: H :+: T) = c match {
        case Inl(x) => Inl(hc(x))
        case Inr(x) => Inr(tm(x))
      }
    }
  }
}

object Coproduct {
  import CoproductOps._

  class MkCoproduct[C <: Coproduct] {
    def apply[A](a: A)(implicit inj: Inject[C, A]): C = inj(a)
  }
  def apply[C <: Coproduct] = new MkCoproduct[C]

}
