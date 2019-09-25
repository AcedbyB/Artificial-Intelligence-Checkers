Checkers by Bao Tran

To run my code, simply compile every .java file in this folder and then run the Main class.
I have tried very hard to name every variable and function very carefully so that someone who has never seen it
before could understand it pretty well.

To be brief, I have:
+ a main class to run the game.
+ a state class to represent the "States" in the state-space model.
+ a computerAgent abstract class contains some space-space model factor and it is the parent of those below.
+ a random agent class playing randomly.
+ a minimax agent class plays using minimax algorithm.
+ an alpha-beta pruning minimax class which could reach depth 12 compare to 8 of the average minimax.
+ a heuristic minimax agent class which plays using minimax and a heuristic function ( which is in the state class)
+ the game would be decided as a "tide" if no single capture happened during the last 10 rounds)
+ At each step the AI would always report how many states searched and the time it takes (in seconds) for those searches.

I also have a feature of letting an advanced bot fight with a random one (will be prompted in the command). I have
tried and it shows that 2 advanced bots of mine fighting would always result in the same so I deleted that from my code.

The rest of the guide needed to test my code would be in the running process and also in the submission form
included with it.