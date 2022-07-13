package main.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 37. 解数独
 * todo 超时
 */
public class LC37SudokuSolver {
    /**
     * key:row值，value:本行使用的数值
     */
    public static final Map<Integer, Set<Integer>> ROW_USED = new HashMap<>();
    /**
     * key:col值，value:本列使用的数值
     */
    private static final Map<Integer, Set<Integer>> COL_USED = new HashMap<>();

    static {
        for (int i = 0; i < 9; i++) {
            ROW_USED.put(i, new HashSet<>());
            COL_USED.put(i, new HashSet<>());
        }
    }

    public void solveSudoku(char[][] board) {
        backtracking(board, 0, 0);
    }

    public void backtracking(char[][] board, int nextRow, int nextCol) {
        for (int i = nextRow; i < board.length; i++) { // 横向遍历树
            for (int j = nextCol; j < board[i].length; j++) {
                for (char k = 1; k <= 9; k++) {
                    if (isVaild(k, i, j, board)) {
                        board[i][j] = k;
                        ROW_USED.get(i).add((int) k);
                        COL_USED.get(j).add((int) k);
                        if (i == 9 && j == 9) {
                            return;
                        }

                        if (j == 9) {
                            nextCol = 0;
                            nextRow = i + 1;
                        } else {
                            nextRow = i;
                            nextCol = j + 1;
                        }
                        backtracking(board, nextRow, nextCol);
                        board[i][j] = '.';
                        ROW_USED.get(i).remove((int) k);
                        COL_USED.get(j).remove((int) k);
                    }
                }
            }
        }
    }

    private boolean isVaild(char k, int row, int col, char[][] board) {
        if (ROW_USED.get(row).contains((int) k)) {
            return false;
        }
        if (COL_USED.get(col).contains((int) k)) {
            return false;
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == k) {
                    return false;
                }
            }
        }
        return true;
    }

}
