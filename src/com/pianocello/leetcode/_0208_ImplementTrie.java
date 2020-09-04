package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 *
 * @author PianoCello
 * @date 2020-07-25
 */
class Trie {

    /**
     * 解法一：使用 HashMap 存储子节点
     */
    private class TrieNode{
        // 当前节点是否代表一个单词
        public boolean isWord;

        // 子节点集合
        public Map<Character, TrieNode> nodeMap = new HashMap<>();

        // 判断当前节点是否存在目标子节点
        public boolean exist(Character c) {
            return nodeMap.containsKey(c);
        }
    }

    // 前缀树的根节点
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.exist(c)) {
                cur.nodeMap.put(c, new TrieNode());
            }
            cur = cur.nodeMap.get(c);
        }
        // 将节点标记为单词
        cur.isWord = (cur != root);
    }

    public boolean search(String word) {
        return searchHelp(word, true);
    }

    public boolean startsWith(String prefix) {
        return searchHelp(prefix, false);
    }

    /**
     * @param str 要搜索的字符串
     * @param isFindWord 是否是单词
     */
    private boolean searchHelp(String str, boolean isFindWord) {
        TrieNode cur = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!cur.exist(c)) {
                return false;
            }
            cur = cur.nodeMap.get(c);
        }
        return isFindWord ? cur.isWord : true;
    }
}

/**
 * 解法二：使用 数组 存储子节点
 */
class Trie2 {

    private class TrieNode{
        // 当前节点是否代表一个单词
        public boolean isWord;

        // 子节点集合
        public TrieNode[] trieNodes = new TrieNode[26];

        // 判断当前节点是否存在目标子节点
        public boolean exist(char c) {
            return trieNodes[c-'a'] != null;
        }
    }

    // 前缀树的根节点
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        // 临时变量
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.exist(c)) {
                cur.trieNodes[c-'a'] =  new TrieNode();
            }
            cur = cur.trieNodes[c-'a'];
        }
        // 将节点标记为单词
        cur.isWord = (cur != root);
    }

    public boolean search(String word) {
        return searchHelp(word, true);
    }

    public boolean startsWith(String prefix) {
        return searchHelp(prefix, false);
    }

    /**
     * @param str 要搜索的字符串
     * @param isFindWord 是否是单词
     */
    private boolean searchHelp(String str, boolean isFindWord) {
        TrieNode cur = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!cur.exist(c)) {
                return false;
            }
            cur = cur.trieNodes[c - 'a'];
        }
        return isFindWord ? cur.isWord : true;
    }
}