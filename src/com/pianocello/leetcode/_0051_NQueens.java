package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 * 输入: 4
 * 输出: [
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
public class _0051_NQueens {

    /**
     * 解法一：回溯法
     */
    private final List<List<String>> res = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][];
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            board[i] = chars;
        }
        backTrack(board, 0);
        return res;
    }

    /**
     * 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后一行
     */
    private void backTrack(char[][] board, int row) {
        // 到达结束条件
        if (row == board.length) {
            List<String> str = new ArrayList<>(row);
            for (char[] chars : board)
                str.add(new String(chars));
            res.add(str);
            return;
        }
        int len = board[row].length;
        // 第 row 行的所有列都是放置皇后的选择
        for (int col = 0; col < len; col++) {
            // 判断当前行列是否可以放置皇后
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            board[row][col] = 'Q';
            // 进入下一行决策
            backTrack(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }

    /**
     * 是否可以在 board[row][col] 放置皇后
     */
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }

}
