on_route(rome). 

on_route(Place):-
 move(Place,Method,NewPlace),
 on_route(NewPlace).

move(home,taxi,nuremberg).
move(nuremberg,train,munich-hbf).
move(munich-hbf,taxi,munich-apt).
move(munich-apt,plane,rome).

