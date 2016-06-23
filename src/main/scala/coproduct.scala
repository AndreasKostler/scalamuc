package scalamuc

sealed trait Coproduct
sealed trait :+:[H, T <: Coproduct] extends Coproduct
sealed trait CNil extends Coproduct

case class Inl[H, T <: Coproduct](head: H) extends :+:[H, T]

case class Inr[H, T <: Coproduct](tail: T) extends :+:[H, T]

object CoproductOps {
  // Inject

  // Map
}

object Coproduct {

}
