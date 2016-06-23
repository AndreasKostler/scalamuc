type E = A :+: B :+: CNil

def query =
  Select[E]
    .q((One[B], More[A], One[B]) where (
      (_: B, as: Queue[A], b: B) => b.value > average(as.map(_.value))))
    .within(1 minute)
    .compile

val as =
  ThermometerSource(abs) // TypedPipe[E]
    .groupByEntity
    .matchEvents(query)
    .toTypedPipe
    .collect {
    case (_, Some((b, as, c))) => (b.id, as map (_.id), c.id).toString }

