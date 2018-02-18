import java.lang.*;
import java.util.*;
import java.io.*;

public class Sudoku {
    public static List<Open> getOpenSpaces(int[][] board) {
        List<Open> openSpaces = new ArrayList<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    Open space = new Open(r, c);
                    List<Integer> possib = space.getPossib();
                    updatePossib(r, c, possib, board);
                    openSpaces.add(space);
                }
            }
        }

        return openSpaces;
    }

    public static void updatePossib(int r, int c, List<Integer> possib, int[][] board) {
        for (int i = 1; i <= 9; i++)
            possib.add(i);

        for (int i = 0; i < board.length; i++) {
            if (possib.contains(board[i][c]))
                possib.removeAll(Arrays.asList(board[i][c]));
        }
        for (int i = 0; i < board[r].length; i++) {
            if (possib.contains(board[r][i]))
                possib.removeAll(Arrays.asList(board[r][i]));
        }
        for (int i = r / 3 * 3; i < (r / 3) * 3 + 3; i++) {
            for (int j = c / 3 * 3; j < (c / 3) * 3 + 3; j++) {
                if (possib.contains(board[i][j]))
                    possib.removeAll(Arrays.asList(board[i][j]));
            }
        }
    }

    public static boolean solve(int[][] board) {
        List<Open> openSpaces = getOpenSpaces(board);

        for (int i = 0; i < openSpaces.size();) {
            Open open = openSpaces.get(i);
            int r = open.getRow();
            int c = open.getCol();

            updatePossib(r, c, open.getPossib(), board);

            if (open.getPossib().isEmpty()) {
                if (i == 0)
                    return false;
                else
                    i--;
            } else {
                board[r][c] = open.getPossib().get(0);
                open.getPossib().remove(0);
                i++;
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                System.out.print(board[r][c]);
                if (c < 8)
                    System.out.print(" ");
            }
            if (r < 8)
                System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("/Users/shivashriganeshmahato/Documents/Projects/tests/src/sudoku.dat"));

        int n = s.nextInt();
        s.nextLine();

        for (int _n = 0; _n < n; _n++) {
            int[][] board = new int[9][9];

            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    board[r][c] = s.nextInt();
                }
            }

            printBoard(board);
            System.out.println("\n");

            solve(board);
            printBoard(board);
        }
    }
}
