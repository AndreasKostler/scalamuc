trait Nat
class _0 extends Nat
class Succ[P <: Nat] extends Nat

type _1 = Succ[_0]
type _2 = Succ[_1]
//...
trait Add[N <: Nat, M <: Nat] {
  type Out <: Nat
}
object Add {
  implicit def baseCase[M <: Nat] = new Add[_0, M] {
    type Out = M
  }

  implicit def indCase[N <: Nat, M <: Nat](implicit a: Add[N, Succ[M]]) =
    new Add[Succ[N], M] {
      type Out = a.Out
    }
}

