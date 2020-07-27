package com.pianocello.leetcode;

import java.util.*;

/**
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例:
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 * <p>
 * 提示:
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。
 * 什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？
 * 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现 Trie（前缀树）。
 *
 * @author PianoCello
 * @date 2020-07-27
 */
public class _0212_WordSearchII {

    private static class TrieNode {
        boolean isWord = false;
        String val;
        TrieNode[] nodes = new TrieNode[26];
    }

    private static TrieNode root = new TrieNode();

    /**
     * 将字符串存进前缀树中
     */
    private static void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.nodes[c - 'a'] == null) {
                cur.nodes[c - 'a'] = new TrieNode();
            }
            cur = cur.nodes[c - 'a'];
        }
        cur.isWord = true;
        cur.val = word;
    }

    /**
     * 解法一：先对单词列表构建前缀树, 然后递归搜索二维数组的字符是否匹配
     */
    public static List<String> findWords(char[][] board, String[] words) {
        // 将字符串插入前缀树
        for (String word : words) {
            insert(word);
        }
        int row = board.length;
        int col = board[0].length;
        // 已访问过的坐标
        boolean[][] visited = new boolean[row][col];
        Set<String> res = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                findWords(board, visited, i, j, res, root);
            }
        }
        return new LinkedList<>(res);
    }

    private static void findWords(char[][] board, boolean[][] visited, int i, int j, Set<String> res, TrieNode cur) {
        // 边界以及是否已经访问判断
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return;
        }
        cur = cur.nodes[board[i][j] - 'a'];
        // 没有子节点了，回退
        if (cur == null) {
            return;
        }
        //找到单词后不能回退，因为可能是“ad” “addd”这样的单词得继续查找
        if (cur.isWord) {
            res.add(cur.val);
        }
        visited[i][j] = true;
        findWords(board, visited, i - 1, j, res, cur);
        findWords(board, visited, i + 1, j, res, cur);
        findWords(board, visited, i, j - 1, res, cur);
        findWords(board, visited, i, j + 1, res, cur);
        //最后要回退，因为下一个起点可能会用到上一个起点的字符
        visited[i][j] = false;
    }

    public static void main(String[] args) {

        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"eat", "eata", "oath", "oath"};

        List<String> list = findWords(board, words);
        System.out.println(list);
    }
}
