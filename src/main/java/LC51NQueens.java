package main.java;

import java.util.*;

public class LC51NQueens {

    private static int N;
    private Set<Integer> columns = new HashSet<>();
    private Set<Integer> diagonals1 = new HashSet<>();
    private Set<Integer> diagonals2 = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        N = n;
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[N];
        Arrays.fill(queens, -1);
        backtrack(solutions, queens, 0);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int row) {
        if (row == N) {
            List<String> board = generateBoard(queens);
            solutions.add(board);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (columns.contains(i)) {
                continue;
            }
            int diagonal1 = row - i;
            if (diagonals1.contains(diagonal1)) {
                continue;
            }
            int diagonal2 = row + i;
            if (diagonals2.contains(diagonal2)) {
                continue;
            }
            queens[row] = i; // 标记 行row，列i是皇后
            columns.add(i);
            diagonals1.add(diagonal1);
            diagonals2.add(diagonal2);
            backtrack(solutions, queens, row + 1); // 下一行
            queens[row] = -1;
            columns.remove(i);
            diagonals1.remove(diagonal1);
            diagonals2.remove(diagonal2);
        }
    }

    public List<String> generateBoard(int[] queens) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            char[] row = new char[N];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

}
