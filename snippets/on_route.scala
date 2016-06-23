sealed trait Place
trait Home extends Place
trait Station extends Place
trait MUC extends Place
trait Rome extends Place


sealed trait Mode
trait Taxi extends Mode
trait Train extends Mode
trait Plane extends Mode

trait Move[A <: Place, M <: Mode, B <: Place]
// Our facts
object Move {
  implicit val fact1 = new Move[Home, Taxi, Station] {}
  implicit val fact2 = new Move[Station, Train, MUC] {}
  implicit val fact3 = new Move[MUC, Plane, Rome] {}
}

// Alle wege fuehren nach Rom...
trait OnRoute[A <: Place]
object OnRoute {
  implicit val baseCase = new OnRoute[Rome] {}
  implicit def indCase[A <: Place, M <: Mode, B <: Place](implicit
    move: Move[A, M, B],
    onRoute: OnRoute[B]
  ) = new OnRoute[A] {}
}


