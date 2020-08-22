package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * @author PianoCello
 * @date 2020-08-22
 */
public class _0037_SudokuSolver {

    /**
     * 解法一：回溯法
     */
    public void solveSudoku(char[][] board) {
        backTrack(board, 0, 0);
    }

    private boolean backTrack(char[][] board, int row, int col) {
        // 结束条件
        if (row == 9) {
            return true;
        }
        if (col == 9) {
            backTrack(board, row + 1, 0);
        }
        if (board[row][col] != '.') {
            return backTrack(board, row, col + 1);
        }
        // 找出可选列表 (不在行 列 宫格内的数字)
        Set<Character> selectable = findSelectable(board, row, col);
        if (selectable.isEmpty()) {
            return false;
        }
        Character[] characters = selectable.toArray(new Character[0]);
        int size = characters.length;
        for (int i = 0; i < size; i++) {
            board[row][col] = characters[i];
            boolean flag = backTrack(board, row, col + 1);
            if (!flag) {
                board[row][col] = '.';
            }
        }

        return true;
    }

    private Set<Character> findSelectable(char[][] board, int row, int col) {
        Set<Character> set = new HashSet<>();
        for (char i = '1'; i <= '9'; i++) {
            set.add(i);
        }
        Set<Character> exist = new HashSet<>();
        for (int k = 0; k < 9; k++) {
            char rowNum = board[row][k];
            char colNum = board[k][col];
            int r = (row / 3) * 3 + k / 3;
            int c = (col / 3) * 3 + k % 3;
            char suduNum = board[r][c];
            if (rowNum != '.') {
                exist.add(rowNum);
            }
            if (colNum != '.') {
                exist.add(colNum);
            }
            if (suduNum != '.') {
                exist.add(suduNum);
            }
        }
        set.removeAll(exist);
        return set;
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
