package scalamuc

import shapeless.Nat
import shapeless.ops.nat.{Diff, LT}

object NatOps {

object NatOps {
  trait GCD[X <: Nat, Y <: Nat] {
    type Out <: Nat
  }
  object GCD {
    type Aux[X <: Nat, Y <: Nat, Out0 <: Nat] = GCD[X, Y] { type Out = Out0 }
    implicit def gcd1[X <: Nat] = new GCD[X, X] { type Out = X }
    implicit def gcd2[X <: Nat, Y <: Nat, Z <: Nat](implicit
      lt: LT[X, Y],
      diff: Diff.Aux[Y, X, Z],
      gcd: GCD[X, Z]
    ) = new GCD[X, Y] {
      type Out = gcd.Out
    }
    implicit def gcd3[X <: Nat, Y <: Nat](implicit
      lt: LT[Y, X],
      gcd: GCD[Y, X]) = new GCD[X, Y] {
      type Out = gcd.Out
    }
  }

  // ...
}



