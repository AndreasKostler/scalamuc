trait Foo[A] {
  type Out
}
object Foo {
  type Aux[A, Out0] =
    Foo[A] { type Out = Out0 }
  // ...
}

def foo[A, Out0](implicit f: Foo[A] { type Out = Out0 }) = ???

def foo[A, Out](implicit f: Foo.Aux[A, Out], b: Bar[Out]) = ???
