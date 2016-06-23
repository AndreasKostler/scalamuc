gcd(X, X, X).

gcd(X, Y, Out) :-
    X < Y,
    Z is Y - X,
    gcd(X, Z, Out).

gcd(X, Y, Out) :-
    Y < X,
    gcd(Y, X, Out).

    
    
