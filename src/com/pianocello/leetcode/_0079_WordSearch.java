package com.pianocello.leetcode;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * @author PianoCello
 * @date 2020-07-26
 */
public class _0079_WordSearch {

    /**
     * 解法一：DFS
     */
    public static boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean b = exist(board, visited, i, j, 0, word);
                if (b) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean exist(char[][] board, boolean[][] visited, int i, int j, int index, String word) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return false;
        }
        // 当前字符不匹配 回溯
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        // 已经匹配上了 返回 true
        if (index == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        boolean b = exist(board, visited, i - 1, j, index + 1, word);
        if (b) {
            return true;
        }
        b = exist(board, visited, i + 1, j, index + 1, word);
        if (b) {
            return true;
        }
        b = exist(board, visited, i, j - 1, index + 1, word);
        if (b) {
            return true;
        }
        b = exist(board, visited, i, j + 1, index + 1, word);
        visited[i][j] = false;

        return b;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };

//        boolean b1 = exist(board, "ABCEED");
//        boolean b2 = exist(board, "SEE");
        boolean b3 = exist(board, "FDASABCESEEEE");
//        boolean b4 = exist(board, "ADECCBAS");
//        boolean b5 = exist(board, "ABCESEEEFS");
//        System.out.println(b1);
//        System.out.println(b2);
        System.out.println(b3);
//        System.out.println(b4);
//        System.out.println(b5);
    }

}
