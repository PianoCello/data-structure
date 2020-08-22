package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * @author PianoCello
 * @date 2020-07-13
 */
public class _0036_ValidSudoku {

    /**
     * 解法一：一次遍历，将结果存储到 HashMap 中
     * 时间复杂度：O(11)，因为我们只对 81 个单元格进行了一次迭代。
     * 空间复杂度：O(1)。
     */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer>[] rows = new HashMap[9];
        Map<Integer, Integer>[] column = new HashMap[9];
        Map<Integer, Integer>[] box = new HashMap[9];
        //初始化 HashMap
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            column[i] = new HashMap<>();
            box[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (rows[i].put((int) c, 0) != null) {
                        return false;
                    }
                    if (column[j].put((int) c, 0) != null) {
                        return false;
                    }
                    // row/3 表示在 3*3 的大方格的第几行上，再乘以 3 表示这行第一个位置是几
                    // col/3 表示在这行上第几列，加起来就是在 3*3 方格的第几个格子内
                    int boxIndex = (i / 3) * 3 + j / 3;
                    if (box[boxIndex].put((int) c, 0) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 解法二：使用数组
     */
    public boolean isValidSudoku2(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int r = 0; r < 9; ++r) {
            for (int c = 0; c < 9; ++c) {
                if (board[r][c] != '.') {
                    int value = board[r][c] - '1';
                    int boxIndex = r / 3 * 3 + c / 3;
                    if (rows[r][value] || cols[c][value] || boxes[boxIndex][value]) {
                        return false;
                    }
                    rows[r][value] = true;
                    cols[c][value] = true;
                    boxes[boxIndex][value] = true;
                }
            }
        }
        return true;
    }

    /**
     * 解法三：回溯法
     */
    public boolean isValidSudoku3(char[][] board) {
        return backTrack(board,0,0);
    }

    private boolean backTrack(char[][] board, int row, int col) {
        // 进入下一行条件
        if (col == 9) {
            return backTrack(board, row + 1, 0);
        }
        // 终结条件
        if (row == 9) {
            return true;
        }
        // 判断当前数字是否有效
        if (board[row][col] != '.') {
            if (!isValid(board, row, col)) {
                return false;
            }
        }
        return backTrack(board, row, col + 1);
    }

    private boolean isValid(char[][] board, int row, int col) {
        // 取出当前位置的数字
        char cur = board[row][col];
        for (int k = 0; k < 9; k++) {
            // 同一行九个位置已出现 cur
            if (board[row][k] == cur && k != col) {
                return false;
            }
            // 同一列九个位置中已出现 cur
            if (board[k][col] == cur && k != row) {
                return false;
            }
            // 同一个子数独九个位置中已出现 cur
            int r = (row / 3) * 3 + k / 3;
            int c = (col / 3) * 3 + k % 3;
            if (board[r][c] == cur && r != row && c != col) {
                return false;
            }
        }
        return true;
    }

}
