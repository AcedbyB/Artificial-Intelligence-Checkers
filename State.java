public class State implements Comparable<State> {

    public char board[][];
    public int utilityPoint;
    public int numberOfCaptures = 0;

    public State(char[][] a) {
        board = new char[a.length][a.length];
        for (int i = 1; i < a.length; i++)
            for (int j = 1; j < a.length; j++)
                board[i][j] = a[i][j];
    }

    public int compareTo(State S) //this is to sort those capture states at which one contains the most captures/
    {
        if(this.numberOfCaptures > S.numberOfCaptures) return 1;
        else if (this.numberOfCaptures < S.numberOfCaptures) return -1;
        return 0;
    }

    public int getUtilityPoint() { //this is the heuristic function.
        int blackPoint = 0;
        int whitePoint = 0;
        for (int i = 1; i < board.length; i++)
            for (int j = 1; j < board.length; j++) {
                if (board[i][j] == 'b') blackPoint += 1;
                if (board[i][j] == 'B') blackPoint += 3;
                if (board[i][j] == 'w') whitePoint += 1;
                if (board[i][j] == 'W') whitePoint += 3;
            }
        if(blackPoint == 0) return -18092000;
        else if(whitePoint == 0) return 18092000;
        else return blackPoint - whitePoint;
    }

}
