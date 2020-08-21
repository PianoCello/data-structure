package com.pianocello.leetcode;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 * 输入: 4
 * 输出: 2
 *
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * @author PianoCello
 * @date 2020-08-18
 */
public class _0052_NQueensII {

    /**
     * 解法一：回溯法
     */
    private int num = 0;
    public int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        backTrack(board,0);
        return num;
    }

    /**
     * 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后一行
     */
    private void backTrack(boolean[][] board, int row) {
        // 结束条件
        if (row == board.length) {
            ++num;
            return;
        }
        int len = board[row].length;
        for (int col = 0; col < len; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = true;
            backTrack(board, row + 1);
            board[row][col] = false;
        }
    }

    /**
     * 判断当前行列是否可以放皇后
     */
    private boolean isValid(boolean[][] board, int row, int col) {
        // 判断列是否冲突
        for (boolean[] rows : board) {
            if(rows[col])
            return false;
        }
        // 判断左上方斜线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j])
                return false;
        }
        // 判断右上方斜线
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j])
                return false;
        }
        return true;
    }

    /**
     * 解法二：使用 bitmap 回溯
     * 时间复杂度：O(N!).
     * 空间复杂度：O(N).
     */
    public int totalNQueens2(int n) {
        return backtrack(0, 0, 0, 0, 0, n);
    }

    /**
     row: 当前放置皇后的行号
     hills: 主对角线占据情况 [1 = 被占据，0 = 未被占据]
     next_row: 下一行被占据的情况 [1 = 被占据，0 = 未被占据]
     dales: 次对角线占据情况 [1 = 被占据，0 = 未被占据]
     count: 所有可行解的个数
     */
    public int backtrack(int row, int hills, int next_row, int dales, int count, int n) {
        // 棋盘所有的列都可放置，
        // 即，按位表示为 n 个 '1'
        // bin(cols) = 0b1111 (n = 4), bin(cols) = 0b111 (n = 3)
        // [1 = 可放置]
        int columns = (1 << n) - 1;

        if (row == n)   // 如果已经放置了 n 个皇后
            count++;  // 累加可行解
        else {
            // 当前行可用的列
            // ! 表示 0 和 1 的含义对于变量 hills, next_row and dales的含义是相反的
            // [1 = 未被占据，0 = 被占据]
            int free_columns = columns & ~(hills | next_row | dales);

            // 找到可以放置下一个皇后的列
            while (free_columns != 0) {
                // free_columns 的第一个为 '1' 的位
                // 在该列我们放置当前皇后
                int curr_column = -free_columns & free_columns;

                // 放置皇后
                // 并且排除对应的列
                free_columns ^= curr_column;

                count = backtrack(row + 1,
                        (hills | curr_column) << 1,
                        next_row | curr_column,
                        (dales | curr_column) >> 1,
                        count, n);
            }
        }

        return count;
    }

}
