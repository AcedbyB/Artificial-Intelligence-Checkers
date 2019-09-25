import java.util.ArrayList;

public class MinimaxAgent extends ComputerAgent {

    public char[][] playTheState(char[][] a, boolean isBlack) { // this will play the current checker board with the form of char[][] a, the boolean is which side u play
        if(a.length == 9) requiredDepth = 8;
        else requiredDepth = 20;
        long startTime = System.currentTimeMillis();
        stateVisited = 0;
        ArrayList<State> CaptureMoves = searchForAllCaptureMoves(a, isBlack);
        ArrayList<State> NormalMoves = searchForAllNormalMoves(a, isBlack);
        if (CaptureMoves.size() > 0) {
            State bestMove = CaptureMoves.get(0);
            for (State next : CaptureMoves) {
                runDFS(next, true, 1);
                if (next.utilityPoint > bestMove.utilityPoint && isBlack) bestMove = next;
                if (next.utilityPoint < bestMove.utilityPoint && !isBlack) bestMove = next;
            }
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + elapsedTime*1.0/1000);
            System.out.println("    Searched " + stateVisited + " states! Playing the optimal one!" );
            return bestMove.board;
        } else if (NormalMoves.size() > 0) {
            State bestMove = NormalMoves.get(0);
            for (State next : NormalMoves) {
                runDFS(next, true, 1);
                if (next.utilityPoint > bestMove.utilityPoint && isBlack) bestMove = next;
                if (next.utilityPoint < bestMove.utilityPoint && !isBlack) bestMove = next;
            }
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + elapsedTime*1.0/1000);
            System.out.println("    Searched " + stateVisited + " states! Playing the optimal one!" );
            return bestMove.board;
        } else {
            System.out.println("    I have lost :(" );
            System.out.println();
            return null;
        }

    }

    public int runDFS(State cur, boolean min, int depth) {
        stateVisited++;
        int curPoint = cur.getUtilityPoint();
        if (curPoint == 18092000) return 1;
        else if (curPoint == -18092000) return -1;
        if (depth == requiredDepth) {
            return 0;
        }

        if (min) cur.utilityPoint = 18092000;
        else cur.utilityPoint = -18092000;
        boolean isMachine;
        if (min) isMachine = false;
        else isMachine = true;
        ArrayList<State> CaptureMoves = searchForAllCaptureMoves(cur.board, isMachine);
        ArrayList<State> NormalMoves = searchForAllNormalMoves(cur.board, isMachine);
        if (CaptureMoves.size() > 0) {
            for (State next : CaptureMoves) {
                if (min)
                    cur.utilityPoint = Math.min((runDFS(next, false, depth + 1)), cur.utilityPoint);
                else
                    cur.utilityPoint = Math.max((runDFS(next, true, depth + 1)), cur.utilityPoint);
            }
        } else {
            for (State next : NormalMoves) {
                if (min)
                    cur.utilityPoint = Math.min((runDFS(next, false, depth + 1)), cur.utilityPoint);
                else
                    cur.utilityPoint = Math.max((runDFS(next, true, depth + 1)), cur.utilityPoint);
            }
        }
        return cur.utilityPoint;
    }
}

