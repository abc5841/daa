
import java.util.*;

public class NQueenSolver {

    static boolean isSafe(int row, int col, int[] board) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col ||
                Math.abs(board[i] - col) == Math.abs(i - row))
                return false;
        }
        return true;
    }

    static void printBoard(int[] board, int n) {
        System.out.println("\nCurrent Board State:");
        for (int i = 0; i < n; i++) {
            System.out.print("Row " + i + " | ");
            for (int j = 0; j < n; j++)
                System.out.print(board[i] == j ? "Q " : ". ");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of N: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Enter a positive number.");
            sc.close();
            return;
        }

        int[] board = new int[n];
        boolean[][] tried = new boolean[n][n];
        Arrays.fill(board, -1);

        int row = 0;
        boolean stopped = false;

        printBoard(board, n);

        while (row < n) {
            ArrayList<Integer> safe = new ArrayList<>();

            for (int c = 0; c < n; c++)
                if (!tried[row][c] && isSafe(row, c, board))
                    safe.add(c);

            if (safe.isEmpty()) {
                System.out.println("Backtracking from Row " + row);
                board[row] = -1;
                Arrays.fill(tried[row], false);

                if (row == 0) break;

                row--;
                board[row] = -1;
                printBoard(board, n);
                continue;
            }

            System.out.println("\nSafe columns: " + safe);
            System.out.println("Enter -1 to backtrack");
            System.out.println("Enter -2 to stop execution");
            System.out.print("Enter column for Row " + row + ": ");

            int col = sc.nextInt();

            if (col == -2) {
                stopped = true;
                System.out.println("Program stopped by user.");
                break;
            }

            if (col == -1) {
                if (row == 0) {
                    System.out.println("Cannot backtrack. Stopping search.");
                    break;
                }

                board[row] = -1;
                Arrays.fill(tried[row], false);
                row--;
                board[row] = -1;
                printBoard(board, n);
                continue;
            }

            if (col < 0 || col >= n || !safe.contains(col)) {
                System.out.println("Invalid or unsafe column!");
                continue;
            }

            board[row] = col;
            tried[row][col] = true;

            System.out.println("Queen placed successfully!");
            printBoard(board, n);
            row++;
        }

        if (row == n)
            System.out.println("All " + n + " queens placed successfully!");
        else if (!stopped)
            System.out.println("No solution found.");

        sc.close();
    }
}
/* Time Complexity: O(N! × N) – The algorithm explores possible queen placements using backtracking. 
Each safety check takes O(N) time. 
O(N!) is commonly used as the search-space bound.*/



//Space Complexity: O(N²) – The tried array stores N × N boolean values. The board array requires O(N) additional space.
