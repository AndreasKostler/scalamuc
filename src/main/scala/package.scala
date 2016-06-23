package object scalamuc {

  import CoproductOps._

  implicit class CoproductSyntax[C <: Coproduct](c: C) {
    def map[P <: Poly](p: P)(implicit mapper: Mapper[P, C]) = mapper(c)
  }
}
