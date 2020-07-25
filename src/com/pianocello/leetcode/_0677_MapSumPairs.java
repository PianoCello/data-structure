package com.pianocello.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。
 * 如果键已经存在，那么原来的键值对将被替代成新的键值对。
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 *
 * 示例 1:
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 *
 * @author PianoCello
 * @date 2020-07-25
 */
class MapSum {

    private class TrieNode {
        public int data;
        public TrieNode[] trieNodes = new TrieNode[26];
        private boolean exist(char c) {
            return trieNodes[c - 'a'] != null;
        }
    }

    private TrieNode root;

    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!cur.exist(c)) {
                cur.trieNodes[c - 'a'] = new TrieNode();
            }
            cur = cur.trieNodes[c - 'a'];
        }
        cur.data = cur == root ? 0 : val;
    }

    public int sum(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.exist(c)) {
                return 0;
            }
            cur = cur.trieNodes[c - 'a'];
        }
        int sum = 0;
        // 使用 栈 迭代所有子节点
        Deque<TrieNode> stack = new LinkedList<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            TrieNode node = stack.remove();
            sum += node.data;
            for (TrieNode trieNode : node.trieNodes) {
                if (trieNode != null)
                    stack.push(trieNode);
            }
        }
        return sum;
    }
}
