import java.util.Scanner;

public class Main {

    static ComputerAgent Terminator;
    static HumanSupervisor HS = new HumanSupervisor();
    static Scanner sc = new Scanner(System.in);
    static char[][] checkerBoard;
    static int gameDimension;
    static char humanTroop;

    public static void printCheckerBoard() {
        if (checkerBoard.length == 5) {
            System.out.println("  1 2 3 4");
            System.out.println(" +-+-+-+-+");
            System.out.println("A|" + checkerBoard[1][1] + "|" + checkerBoard[1][2] + "|" + checkerBoard[1][3] + "|" + checkerBoard[1][4] + "|");
            System.out.println(" +-+-+-+-+");
            System.out.println("B|" + checkerBoard[2][1] + "|" + checkerBoard[2][2] + "|" + checkerBoard[2][3] + "|" + checkerBoard[2][4] + "|");
            System.out.println(" +-+-+-+-+");
            System.out.println("C|" + checkerBoard[3][1] + "|" + checkerBoard[3][2] + "|" + checkerBoard[3][3] + "|" + checkerBoard[3][4] + "|");
            System.out.println(" +-+-+-+-+");
            System.out.println("D|" + checkerBoard[4][1] + "|" + checkerBoard[4][2] + "|" + checkerBoard[4][3] + "|" + checkerBoard[4][4] + "|");
            System.out.println(" +-+-+-+-+");
        } else {
            System.out.println("  1 2 3 4 5 6 7 8");
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.println("A|" + checkerBoard[1][1] + "|" + checkerBoard[1][2] + "|" + checkerBoard[1][3] + "|" + checkerBoard[1][4] + "|" + checkerBoard[1][5] + "|" + checkerBoard[1][6] + "|" + checkerBoard[1][7] + "|" + checkerBoard[1][8] + "|");
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.println("B|" + checkerBoard[2][1] + "|" + checkerBoard[2][2] + "|" + checkerBoard[2][3] + "|" + checkerBoard[2][4] + "|" + checkerBoard[2][5] + "|" + checkerBoard[2][6] + "|" + checkerBoard[2][7] + "|" + checkerBoard[2][8] + "|");
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.println("C|" + checkerBoard[3][1] + "|" + checkerBoard[3][2] + "|" + checkerBoard[3][3] + "|" + checkerBoard[3][4] + "|" + checkerBoard[3][5] + "|" + checkerBoard[3][6] + "|" + checkerBoard[3][7] + "|" + checkerBoard[3][8] + "|");
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.println("D|" + checkerBoard[4][1] + "|" + checkerBoard[4][2] + "|" + checkerBoard[4][3] + "|" + checkerBoard[4][4] + "|" + checkerBoard[4][5] + "|" + checkerBoard[4][6] + "|" + checkerBoard[4][7] + "|" + checkerBoard[4][8] + "|");
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.println("E|" + checkerBoard[5][1] + "|" + checkerBoard[5][2] + "|" + checkerBoard[5][3] + "|" + checkerBoard[5][4] + "|" + checkerBoard[5][5] + "|" + checkerBoard[5][6] + "|" + checkerBoard[5][7] + "|" + checkerBoard[5][8] + "|");
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.println("F|" + checkerBoard[6][1] + "|" + checkerBoard[6][2] + "|" + checkerBoard[6][3] + "|" + checkerBoard[6][4] + "|" + checkerBoard[6][5] + "|" + checkerBoard[6][6] + "|" + checkerBoard[6][7] + "|" + checkerBoard[6][8] + "|");
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.println("G|" + checkerBoard[7][1] + "|" + checkerBoard[7][2] + "|" + checkerBoard[7][3] + "|" + checkerBoard[7][4] + "|" + checkerBoard[7][5] + "|" + checkerBoard[7][6] + "|" + checkerBoard[7][7] + "|" + checkerBoard[7][8] + "|");
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.println("H|" + checkerBoard[8][1] + "|" + checkerBoard[8][2] + "|" + checkerBoard[8][3] + "|" + checkerBoard[8][4] + "|" + checkerBoard[8][5] + "|" + checkerBoard[8][6] + "|" + checkerBoard[8][7] + "|" + checkerBoard[8][8] + "|");
            System.out.println(" +-+-+-+-+-+-+-+-+");
        }
        System.out.println();
    }


    public static void prepareBattlefield(int dimension) {
        checkerBoard = new char[dimension + 1][dimension + 1];
        for (int i = 1; i <= dimension; i++) for (int j = 1; j <= dimension; j++) checkerBoard[i][j] = ' ';
        if (dimension == 4) {
            checkerBoard[1][2] = 'b';
            checkerBoard[1][4] = 'b';
            checkerBoard[4][1] = 'w';
            checkerBoard[4][3] = 'w';
        } else {
            for (int i = 1; i <= 3; i++)
                for (int j = (i % 2); j <= 8; j += 2) checkerBoard[i][j] = 'b';
            for (int i = 6; i <= 8; i++)
                for (int j = (i % 2); j <= 8; j += 2) checkerBoard[i][j] = 'w';
        }
    }

    public static int countCurrentTroopsOnBothSide() {
        int ans = 0;
        for (int i = 1; i <= gameDimension; i++)
            for (int j = 1; j <= gameDimension; j++) if (checkerBoard[i][j] != ' ') ans++;
        return ans;
    }

    public static void HumanBattleStart() { //this function is for when the human decide to play against bot.
        int roundWithoutCapturing = 0;      //this is going to decide if things gonna be a tie.
        int currentTroopsOnBothSide = countCurrentTroopsOnBothSide();
        while (!HS.humanLost(checkerBoard, humanTroop)) {
            System.out.println("White turn!");
            if (humanTroop == 'W') {
                printCheckerBoard();
                checkerBoard = HS.humanTurn(checkerBoard, 'W');
                printCheckerBoard();
                System.out.println("Black turn!");
                System.out.println("I'm thinking....");
                char[][] tmp = Terminator.playTheState(checkerBoard, true);
                if (tmp == null) {
                    System.out.println("Black lost!");
                    return;
                } else {
                    checkerBoard = tmp;
                }
                printCheckerBoard();
                if (HS.humanLost(checkerBoard, humanTroop)) {
                    System.out.println("White lost!");
                    return;
                }
            } else {
                System.out.println("I'm thinking....");
                char[][] tmp = Terminator.playTheState(checkerBoard, false);
                if (tmp == null) {
                    System.out.println("White lost!");
                    return;
                } else {
                    checkerBoard = tmp;
                }
                printCheckerBoard();
                if (HS.humanLost(checkerBoard, humanTroop)) {
                    System.out.println("Black lost!");
                    return;
                }
                System.out.println("Black turn!");
                checkerBoard = HS.humanTurn(checkerBoard, 'B');
                printCheckerBoard();
            }
            System.out.println("Consecutive rounds without capturing: " + roundWithoutCapturing + " rounds");
            System.out.println();
            if (currentTroopsOnBothSide == countCurrentTroopsOnBothSide()) {
                roundWithoutCapturing++;
                if (roundWithoutCapturing == 10) { //after 10 rounds with no capturing it would return a tie!
                    System.out.println("During last 10 moves there has been no capturing action! This is a tie! ");
                    return;
                }
            } else {
                currentTroopsOnBothSide = countCurrentTroopsOnBothSide();
                roundWithoutCapturing = 0;
            }
        }
    }

    public static void setUpComputerAgent(int choice) {
        if (choice == 1) Terminator = new RandomAgent();
        if (choice == 2) Terminator = new MinimaxAgent();
        if (choice == 3) Terminator = new AlphaBetaAgent();
        if (choice == 4) {
            Terminator = new H_MinimaxAgent();
            System.out.println("What is the required depth cutoff? The deeper the smarter the AI (enter 1-8)");
            Terminator.requiredDepth = sc.nextInt();
        }
    }

    public static void BotsBattleStart() { //this is for 2 bots against each other.
        printCheckerBoard();
        ComputerAgent TestSubject = new RandomAgent();
        while (1 > 0) {
            System.out.println("White turn!");
            char[][] tmp = TestSubject.playTheState(checkerBoard, false);
            if (tmp == null) {
                System.out.println("Black Triumph!");
                return;
            } else {
                checkerBoard = tmp;
            }
            printCheckerBoard();
            System.out.println("Black turn!");
            tmp = Terminator.playTheState(checkerBoard, true);
            if (tmp == null) {
                System.out.println("White triumph!");
                return;
            } else {
                checkerBoard = tmp;
            }
            printCheckerBoard();
        }
    }

    public static void gameStart() {
        System.out.println("Checkers by Bao Tran");
        System.out.println("Choose your playfield: 4x4(enter 1) or 8x8(enter 2)");
        int choice = sc.nextInt();
        if (choice == 1) gameDimension = 4;
        else gameDimension = 8;
        prepareBattlefield(gameDimension);
        System.out.println("Choose the opponent: ");
        System.out.println("1.An agent that plays randomly.");
        System.out.println("2.An agent that plays based on Minimax.");
        System.out.println("3.An agent that plays based on Minimax and Alpha-beta pruning.");
        System.out.println("4.An agent that plays based on H-Minimax with a fixed depth cut-off.");
        System.out.println("Enter 1,2,3 or 4: ");
        choice = sc.nextInt();
        setUpComputerAgent(choice);
        System.out.println("You want to play(enter 1) or to see the opponent versus a computer that plays randomly(enter 2)?");
        choice = sc.nextInt();
        if (choice == 1) {
            System.out.println("You wanna play Black(enter 1) or White(enter 2) ?");
            choice = sc.nextInt();
            if (choice == 1) {
                humanTroop = 'B';
            } else {
                humanTroop = 'W';
            }
            HumanBattleStart();
        } else {
            System.out.println("The random agent gonna plays the white!");
            BotsBattleStart();
        }
    }

    public static void main(String[] args) {
        gameStart();
    }

}
