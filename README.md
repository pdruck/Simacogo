# Simacogo
Simacogo is basically a combination of Connect-Four and Go and played on a 9x9 grid. 

The players take turns dropping their respective pieces ('O' or 'X') into a column (1-9).  2 points are given for two
of a player's pieces that are next to each other (up,down,left,right) and 1 point is given for two of a player's pieces
that are oriented diagonally (up and to the right, down and to the right, up and to the left, 
down and to the left). 

The game is over when the entire board is filled and the winner is the person with the most points.
###Initial State
~~~~
1 2 3 4 5 6 7 8 9 <-- Column Number
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . . <-- Empty board positions (.)
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . . <-- Piece drops to the bottom when placed
~~~~
###Example State
~~~~
O Score:	2
X Score:	3
1 2 3 4 5 6 7 8 9
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
X . . . . . . . .
X . . . . . . . .
O X O O . . . . .
~~~~
###Example Ending State
~~~~
O Score:	139	 <-- O wins
X Score:	116
1 2 3 4 5 6 7 8 9
X X X X X X X X O
X X X X O O X O X
X X X X O O X O O
X O X X O O O X X
X O X X X O O O O
X O X O O O O X X
O X X O O O O O O
X O X O O O O O X
O X X X O O O O O
~~~~
