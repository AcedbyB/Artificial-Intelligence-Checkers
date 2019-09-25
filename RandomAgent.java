import java.util.ArrayList;
import java.util.Random;

public class RandomAgent extends ComputerAgent {

    public char[][] playTheState(char[][] a, boolean isBlack) { // this will play the current checker board with the form of char[][] a, the boolean is which side u play
        long startTime = System.currentTimeMillis();
        ArrayList<State> CaptureMoves = searchForAllCaptureMoves(a, isBlack);
        ArrayList<State> NormalMoves = searchForAllNormalMoves(a, isBlack);
        Random rand = new Random();
        if (CaptureMoves.size() > 0) {
            int moveIndex = rand.nextInt(CaptureMoves.size());
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + elapsedTime*1.0/1000);
            System.out.println("    Gonna do move randomly cause im a random bot!!");
            return CaptureMoves.get(moveIndex).board;
        } else if (NormalMoves.size() > 0) {
            int moveIndex = rand.nextInt(NormalMoves.size());
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + elapsedTime*1.0/1000);
            System.out.println("    Gonna do move randomly cause im a random bot!!");
            return NormalMoves.get(moveIndex).board;
        } else {
            System.out.println("    I have lost :(");
            System.out.println();
            return null;
        }
    }
}
