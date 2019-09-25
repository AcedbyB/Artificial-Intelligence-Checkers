import java.util.ArrayList;
import java.util.Scanner;

public class HumanSupervisor extends ComputerAgent {
    static Scanner Sc = new Scanner(System.in);

    public char[][] humanTurn(char[][] checkerBoard, char humanTroop) {
        char[][] trialCheckerBoard = new char[checkerBoard.length][checkerBoard.length];
        for (int i = 1; i < checkerBoard.length; i++)
            for (int j = 1; j < checkerBoard.length; j++) {
                trialCheckerBoard[i][j] = checkerBoard[i][j];
            }
        System.out.print("Your command: ");
        String command = Sc.nextLine();
        for (int i = 0; i <= command.length() - 5; i += 3) {
            int startingRow = command.charAt(i) - 'a' + 1;
            if (startingRow < 0) i += 32;
            int startingCol = command.charAt(i + 1) - '0';
            int endingRow = command.charAt(i + 3) - 'a' + 1;
            if (endingRow < 0) endingRow += 32;
            int endingCol = command.charAt(i + 4) - '0';
            if (trialCheckerBoard[startingRow][startingCol] != humanTroop && trialCheckerBoard[startingRow][startingCol] != (humanTroop + 32)) {
                System.out.println("Your move is not legit!");
                return humanTurn(checkerBoard, humanTroop);
            }
            trialCheckerBoard[endingRow][endingCol] = trialCheckerBoard[startingRow][startingCol];
            trialCheckerBoard[startingRow][startingCol] = ' ';
            if (Math.abs(endingRow - startingRow) > 1) {
                int middleRow = (startingRow + endingRow)/2;
                int middleCol = (startingCol + endingCol)/2;
                trialCheckerBoard[middleRow][middleCol] = ' ';
            }
                if (endingRow == 1 && humanTroop == 'W') trialCheckerBoard[endingRow][endingCol] = 'W';
            if (endingRow == checkerBoard.length - 1 && humanTroop == 'B')
                trialCheckerBoard[endingRow][endingCol] = 'B';
        }
        boolean isBlack = humanTroop == 'B';
        if (isValid(checkerBoard, trialCheckerBoard, isBlack)) return trialCheckerBoard;
        else {
            System.out.println("Your move isn't the right one right now, please try something else :)");
            return humanTurn(checkerBoard, humanTroop);
        }
    }


    public boolean isValid(char[][] a, char[][] b, boolean isBlack) {
        ArrayList<State> CaptureMoves = searchForAllCaptureMoves(a, isBlack);
        if(CaptureMoves.size() > 0) {
            for (State s : CaptureMoves) {
                if (equalCharArrays(b, s.board)) return true;
            }
            return false;
        }
        ArrayList<State> NormalMoves = searchForAllNormalMoves(a, isBlack);
        for (State s : NormalMoves) {
            if (equalCharArrays(b, s.board)) return true;
        }
        return false;
    }

    public boolean equalCharArrays(char[][] a, char[][] b) {
        for (int i = 1; i < a.length; i++)
            for (int j = 1; j < a.length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        return true;
    }

    public static boolean humanLost(char[][] checkerBoard, char humanTroop) {
        for (int i = 1; i < checkerBoard.length; i++)
            for (int j = 1; j < checkerBoard.length; j++) {
                if (checkerBoard[i][j] == humanTroop || checkerBoard[i][j] == humanTroop + 32) return false;
            }
        return true;
    }

}

