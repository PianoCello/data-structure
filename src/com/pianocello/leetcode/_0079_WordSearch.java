package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
     * 解法一：BFS
     */
    public static boolean exist(char[][] board, String word) {
        // 记录已经使用过的字符的坐标
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        int row = board.length;
        int col = board[0].length;
        int len = word.length();

        char nextChar = word.charAt(0);
        for (int j = 0; j < row; j++) {
            for (int k = 0; k < col; k++) {
                // 找到第一个匹配的字符启动 BFS
                if (board[j][k] == nextChar) {
                    queue.offer(j * col + k);
                    int tempLen = 0;
                    while (!queue.isEmpty()) {
                        // 这一轮要匹配的字符
                        char curChar = nextChar;
                        int size = queue.size();
                        for (int i = 0; i < size; i++) {
                            Integer cur = queue.remove();
                            int x = cur / col;
                            int y = cur % col;
                            if (board[x][y] == curChar) {
                                if (tempLen == len - 1) {
                                    return true;
                                }
                                // 下一轮要找的字符
                                nextChar = word.charAt(++tempLen);
                                // 将已经使用过的坐标加入 set
                                set.add(x * col + y);
                                // 添加相邻坐标
                                if (x - 1 >= 0 && !set.contains((x - 1) * col + y)) {
                                    queue.offer((x - 1) * col + y);
                                }
                                if (x + 1 < row && !set.contains((x + 1) * col + y)) {
                                    queue.offer((x + 1) * col + y);
                                }
                                if (y - 1 >= 0 && !set.contains(x * col + y - 1)) {
                                    queue.offer(x * col + y - 1);
                                }
                                if (y + 1 < col && !set.contains(x * col + y + 1)) {
                                    queue.offer(x * col + y + 1);
                                }
                            }
                        }
                    }
                    // 上一轮的结果不匹配 不需要继续比较了 重置为初始条件
                    queue.clear();
                    set.clear();
                    nextChar = word.charAt(0);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };

//        boolean b1 = exist(board, "ABCCED");
//        boolean b2 = exist(board, "SEE");
//        boolean b3 = exist(board, "FCEDASABCESE");
//        boolean b4 = exist(board, "ADECCBAS");
        boolean b5 = exist(board, "ABCESEEEFS");
//        System.out.println(b1);
//        System.out.println(b2);
//        System.out.println(b3);
//        System.out.println(b4);
        System.out.println(b5);
    }

}
