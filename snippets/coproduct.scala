sealed trait :+:[A, B]
case class Inl[A, B](a: A) extends :+:[A, B]
case class Inr[A, B](b: B) extends :+:[A, B]

// type C = Either[Int, String]
type C = Int :+: String
// val i: C = Left(42)
val i: C = Inl(42)
// val s: C = Right("Foo")
val s: C = Inr("Foo")


sealed trait Coproduct
sealed trait :+:[H, T <: Coproduct] extends Coproduct
sealed trait CNil extends Coproduct

case class Inl[H, T <: Coproduct](head : H) extends :+:[H, T]

case class Inr[H, T <: Coproduct](tail : T) extends :+:[H, T]


// type C = :+:[Int, :+:[String, :+:[Boolean, CNil]]]
type C = Int :+: String :+: Boolean :+: CNil
type R = String :+: Boolean :+: CNil
type C = Int :+: R

val i: C = Inl(42)
val r: R = Inl("foo")
val s: C = Inr(r) // Inr(Inl("foo"))
