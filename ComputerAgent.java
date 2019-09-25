import java.util.ArrayList;
import java.util.Collections;

public abstract class ComputerAgent {

    public int requiredDepth;
    public int stateVisited = 0;
    public ArrayList<State> searchForAllCaptureMoves(char[][] a, boolean isBlack) {
        ArrayList<State> AllAvailableCaptureMoves = new ArrayList<>();
        ArrayList<State> CaptureMoves = new ArrayList<>();
        char searchTarget;
        if (isBlack) searchTarget = 'B';
        else searchTarget = 'W';
        for (int i = 1; i < a.length; i++)
            for (int j = 1; j < a.length; j++)
                if (a[i][j] == searchTarget || a[i][j] == searchTarget + 32) {
                    CaptureAdvance(new State(a), i, j, AllAvailableCaptureMoves, true, isBlack);
                }
        Collections.sort(AllAvailableCaptureMoves);
        for (State s : AllAvailableCaptureMoves) {
            if(s.numberOfCaptures == AllAvailableCaptureMoves.get(0).numberOfCaptures) CaptureMoves.add(s);
        }
        return CaptureMoves;
    }

    public ArrayList searchForAllNormalMoves(char[][] a, boolean isBlack) {
        ArrayList<State> NormalMoves = new ArrayList<>();
        char searchTarget;
        if (isBlack) searchTarget = 'B';
        else searchTarget = 'W';
        for (int i = 1; i < a.length; i++)
            for (int j = 1; j < a.length; j++)
                if (a[i][j] == searchTarget || a[i][j] == searchTarget + 32) {
                    NormalAdvance(new State(a), i, j, NormalMoves, isBlack);
                }
        return NormalMoves;
    }


    public void CaptureAdvance(State cur, int stateX, int stateY, ArrayList<State> moves, boolean initial, boolean isBlack) { //seach for applicable capture moves.
        if (isBlack) {
            if (stateX == cur.board.length - 1) cur.board[stateX][stateY] = 'B';
            boolean capturable = false;
            if ((stateX + 2 < cur.board.length && stateY + 2 < cur.board.length) && cur.board[stateX + 2][stateY + 2] == ' ' && (cur.board[stateX + 1][stateY + 1] == 'w' || cur.board[stateX + 1][stateY + 1] == 'W')) {
                State next = new State(cur.board);
                next.numberOfCaptures++;
                next.board[stateX + 2][stateY + 2] = cur.board[stateX][stateY];
                next.board[stateX + 1][stateY + 1] = ' ';
                next.board[stateX][stateY] = ' ';
                capturable = true;
                CaptureAdvance(next, stateX + 2, stateY + 2, moves, false, isBlack);
            }
            if ((stateX + 2 < cur.board.length && stateY - 2 > 0) && cur.board[stateX + 2][stateY - 2] == ' ' && (cur.board[stateX + 1][stateY - 1] == 'w' || cur.board[stateX + 1][stateY - 1] == 'W')) {
                State next = new State(cur.board);
                next.numberOfCaptures++;
                next.board[stateX + 2][stateY - 2] = cur.board[stateX][stateY];
                next.board[stateX + 1][stateY - 1] = ' ';
                next.board[stateX][stateY] = ' ';
                capturable = true;
                CaptureAdvance(next, stateX + 2, stateY - 2, moves, false, isBlack);
            }
            if (cur.board[stateX][stateY] == 'B') {
                if ((stateX - 2 > 0 && stateY + 2 < cur.board.length) && cur.board[stateX - 2][stateY + 2] == ' ' && (cur.board[stateX - 1][stateY + 1] == 'w' || cur.board[stateX - 1][stateY + 1] == 'W')) {
                    State next = new State(cur.board);
                    next.numberOfCaptures++;
                    next.board[stateX - 2][stateY + 2] = cur.board[stateX][stateY];
                    next.board[stateX - 1][stateY + 1] = ' ';
                    next.board[stateX][stateY] = ' ';
                    capturable = true;
                    CaptureAdvance(next, stateX - 2, stateY + 2, moves, false, isBlack);
                }
                if ((stateX - 2 > 0 && stateY - 2 > 0) && cur.board[stateX - 2][stateY - 2] == ' ' && (cur.board[stateX - 1][stateY - 1] == 'w' || cur.board[stateX - 1][stateY - 1] == 'W')) {
                    State next = new State(cur.board);
                    next.numberOfCaptures++;
                    next.board[stateX - 2][stateY - 2] = cur.board[stateX][stateY];
                    next.board[stateX - 1][stateY - 1] = ' ';
                    next.board[stateX][stateY] = ' ';
                    capturable = true;
                    CaptureAdvance(next, stateX - 2, stateY - 2, moves, false, isBlack);
                }
            }
            if (!capturable && !initial) moves.add(cur);
        } else {
            if (stateX == 1) cur.board[stateX][stateY] = 'W';
            boolean capturable = false;
            if ((stateX - 2 > 0 && stateY + 2 < cur.board.length) && cur.board[stateX - 2][stateY + 2] == ' ' && (cur.board[stateX - 1][stateY + 1] == 'b' || cur.board[stateX - 1][stateY + 1] == 'B')) {
                State next = new State(cur.board);
                next.numberOfCaptures++;
                next.board[stateX - 2][stateY + 2] = cur.board[stateX][stateY];
                next.board[stateX - 1][stateY + 1] = ' ';
                next.board[stateX][stateY] = ' ';
                capturable = true;
                CaptureAdvance(next, stateX - 2, stateY + 2, moves, false, isBlack);
            }
            if ((stateX - 2 > 0 && stateY - 2 > 0) && cur.board[stateX - 2][stateY - 2] == ' ' && (cur.board[stateX - 1][stateY - 1] == 'b' || cur.board[stateX - 1][stateY - 1] == 'B')) {
                State next = new State(cur.board);
                next.numberOfCaptures++;
                next.board[stateX - 2][stateY - 2] = cur.board[stateX][stateY];
                next.board[stateX - 1][stateY - 1] = ' ';
                next.board[stateX][stateY] = ' ';
                capturable = true;
                CaptureAdvance(next, stateX - 2, stateY - 2, moves, false, isBlack);
            }
            if (cur.board[stateX][stateY] == 'W') {
                if ((stateX + 2 < cur.board.length && stateY + 2 < cur.board.length) && cur.board[stateX + 2][stateY + 2] == ' ' && (cur.board[stateX + 1][stateY + 1] == 'b' || cur.board[stateX + 1][stateY + 1] == 'B')) {
                    State next = new State(cur.board);
                    next.numberOfCaptures++;
                    next.board[stateX + 2][stateY + 2] = cur.board[stateX][stateY];
                    next.board[stateX + 1][stateY + 1] = ' ';
                    next.board[stateX][stateY] = ' ';
                    capturable = true;
                    CaptureAdvance(next, stateX + 2, stateY + 2, moves, false, isBlack);
                }
                if ((stateX + 2 < cur.board.length && stateY - 2 > 0) && cur.board[stateX + 2][stateY - 2] == ' ' && (cur.board[stateX + 1][stateY - 1] == 'b' || cur.board[stateX + 1][stateY - 1] == 'B')) {
                    State next = new State(cur.board);
                    next.numberOfCaptures++;
                    next.board[stateX + 2][stateY - 2] = cur.board[stateX][stateY];
                    next.board[stateX + 1][stateY - 1] = ' ';
                    next.board[stateX][stateY] = ' ';
                    capturable = true;
                    CaptureAdvance(next, stateX + 2, stateY - 2, moves, false, isBlack);
                }
            }
            if (!capturable && !initial) moves.add(cur);
        }
    }

    public void NormalAdvance(State cur, int stateX, int stateY, ArrayList<State> moves, boolean isMachine) { //search for normal moves
        if (isMachine) {
            if (stateX == cur.board.length - 1) cur.board[stateX][stateY] = 'B';
            if (stateX + 1 < cur.board.length && stateY + 1 < cur.board.length && cur.board[stateX + 1][stateY + 1] == ' ') {
                State next = new State(cur.board);
                next.board[stateX + 1][stateY + 1] = cur.board[stateX][stateY];
                if (stateX + 1 == cur.board.length - 1) next.board[stateX + 1][stateY + 1] = 'B';
                next.board[stateX][stateY] = ' ';
                moves.add(next);
            }
            if (stateX + 1 < cur.board.length && stateY - 1 > 0 && cur.board[stateX + 1][stateY - 1] == ' ') {
                State next = new State(cur.board);
                next.board[stateX + 1][stateY - 1] = cur.board[stateX][stateY];
                if (stateX + 1 == cur.board.length - 1) next.board[stateX + 1][stateY - 1] = 'B';
                next.board[stateX][stateY] = ' ';
                moves.add(next);
            }
            if (cur.board[stateX][stateY] == 'B') {
                if (stateX - 1 > 0 && stateY + 1 < cur.board.length && cur.board[stateX - 1][stateY + 1] == ' ') {
                    State next = new State(cur.board);
                    next.board[stateX - 1][stateY + 1] = cur.board[stateX][stateY];
                    next.board[stateX][stateY] = ' ';
                    moves.add(next);
                }
                if (stateX - 1 > 0 && stateY - 1 > 0 && cur.board[stateX - 1][stateY - 1] == ' ') {
                    State next = new State(cur.board);
                    next.board[stateX - 1][stateY - 1] = cur.board[stateX][stateY];
                    next.board[stateX][stateY] = ' ';
                    moves.add(next);
                }
            }
        } else {
            if (stateX == 1) cur.board[stateX][stateY] = 'W';
            if (stateX - 1 > 0 && stateY + 1 < cur.board.length && cur.board[stateX - 1][stateY + 1] == ' ') {
                State next = new State(cur.board);
                next.board[stateX - 1][stateY + 1] = cur.board[stateX][stateY];
                if (stateX - 1 == 1) next.board[stateX - 1][stateY + 1] = 'W';
                next.board[stateX][stateY] = ' ';
                moves.add(next);
            }
            if (stateX - 1 > 0 && stateY - 1 > 0 && cur.board[stateX - 1][stateY - 1] == ' ') {
                State next = new State(cur.board);
                next.board[stateX - 1][stateY - 1] = cur.board[stateX][stateY];
                if (stateX - 1 == 1) next.board[stateX - 1][stateY - 1] = 'W';
                next.board[stateX][stateY] = ' ';
                moves.add(next);
            }
            if (cur.board[stateX][stateY] == 'W') {
                if (stateX + 1 < cur.board.length && stateY + 1 < cur.board.length && cur.board[stateX + 1][stateY + 1] == ' ') {
                    State next = new State(cur.board);
                    next.board[stateX + 1][stateY + 1] = cur.board[stateX][stateY];
                    next.board[stateX][stateY] = ' ';
                    moves.add(next);
                }
                if (stateX + 1 < cur.board.length && stateY - 1 > 0 && cur.board[stateX + 1][stateY - 1] == ' ') {
                    State next = new State(cur.board);
                    next.board[stateX + 1][stateY - 1] = cur.board[stateX][stateY];
                    next.board[stateX][stateY] = ' ';
                    moves.add(next);
                }
            }
        }
    }

    public char[][] playTheState(char[][] a, boolean isBlack) {
        return a;
    }
}
