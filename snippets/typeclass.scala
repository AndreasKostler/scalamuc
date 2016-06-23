
// A => A
def id[A](x: A) = a

// * => *
type Id[A] = A

// ((A => A) , A) => A
def apply[A](f: A => A, x: A) = f(a)

// (( * => *) x *) => *
type Apply[F[X], T] = F[T]


trait Dependent[A] {
  type Out
  val value: Out
}

def foo[A](a: A)(implicit that: Dependent[A]): that.Out = that.value

implicit val intDep = new Dependent[Int] {
  type Out = String
  val value = "42"
}

trait Dependent1[A, Out] {
  val value: Out
}

implicit val intDep2 = new Dependent1[Int, String] {
  val value = "42"
}

def bar[A, Out](a: A)(implicit that: Dependent1[A, Out]): Out = that.value

trait Unapply[F] {
  type Out
}

object Unapply {
  implicit def unapply[F[_], A] = new Unapply[F[A]] {
    type Out = A
  }
}

trait Unapply1[F, Out]

object Unapply1 {
  implicit def unapply[F[_], A] = new Unapply1[F[A], A] {}
}

def unapply1[F, Out](f: F)(implicit U: Unapply1[F, Out]) = new { type T = Out }
def unapply[F](f: F)(implicit U: Unapply[F]) = new { type T = U.Out }


sealed trait Coproduct
sealed trait :+:[+H, +T <: Coproduct] extends Coproduct
sealed trait CNil extends Coproduct

case class Inl[+H, +T <: Coproduct](head : H) extends :+:[H, T]

case class Inr[+H, +T <: Coproduct](tail : T) extends :+:[H, T]

object coproduct {
  trait Inject[C <: Coproduct, I] extends Serializable {
    def apply(i: I): C
  }

  object Inject {
    def apply[C <: Coproduct, I](implicit inject: Inject[C, I]): Inject[C, I] = inject

    implicit def tlInject[H, T <: Coproduct, I](implicit tlInj : Inject[T, I]): Inject[H :+: T, I] = new Inject[H :+: T, I] {
      def apply(i: I): H :+: T = Inr(tlInj(i))
    }

    implicit def hdInject[H, T <: Coproduct]: Inject[H :+: T, H] = new Inject[H :+: T, H] {
      def apply(i: H): H :+: T = Inl(i)
    }
  }
}

sealed trait Exp
case class A() extends Exp
case class B() extends Exp

type Error = String

object Parser {
  def parse(exp: String): Either[A, Error] = exp match {
    case "a" => Left(A())
    case _ => Right("Boom!")
  }
}

sealed trait :+:[A, B]
case class Inl[A, B](a: A) extends :+:[A, B]
case class Inr[A, B](b: B) extends :+:[A, B]

// type C = Either[Int, String]
type C = Int :+: String
// val i: C = Left(42)
val i: C = Inl(42)
// val s: C = Right("Foo")
val s: C = Inr("Foo")

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

addOne(42) // 43
addOne("foo") // foo1
