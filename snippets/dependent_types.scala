trait Foo {
  type Out
  val value: Out
}

trait Bar[A]

def foo(x: Foo): x.Out = x.value

// Cannot be used in computational continuation
def foo[F[_]](f: F[x.Out])(implicit x: Foo) = ???

// Cannot be used in implicits (without using Aux)
def foo(implicit x: Foo, y: Bar[x.Out]) = ???
