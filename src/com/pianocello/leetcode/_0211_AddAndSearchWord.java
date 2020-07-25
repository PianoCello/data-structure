package com.pianocello.leetcode;

/**
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * <p>
 * 示例:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 *
 * @author PianoCello
 * @date 2020-07-25
 */
class WordDictionary {

    private class TrieNode {

        // 当前节点是否代表一个单词
        public boolean isWord;

        // 子节点集合
        public TrieNode[] trieNodes = new TrieNode[26];

        // 判断当前节点是否存在目标子节点
        public boolean exist(char c) {
            return trieNodes[c - 'a'] != null;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        // 临时变量
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.exist(c)) {
                cur.trieNodes[c - 'a'] = new TrieNode();
            }
            cur = cur.trieNodes[c - 'a'];
        }
        // 将节点标记为单词
        cur.isWord = (cur != root);
    }

    public boolean search(String word) {
        return searchHelp(word, root);
    }

    private boolean searchHelp(String word, TrieNode root) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 对于 . 递归的判断所有不为空的子节点
            if (c == '.') {
                for (int j = 0; j < 26; j++) {
                    if (cur.trieNodes[j] != null) {
                        if (searchHelp(word.substring(i + 1), cur.trieNodes[j])) {
                            return true;
                        }
                    }
                }
                return false;
            }
            // 不含有当前节点
            if (!cur.exist(c)) {
                return false;
            }
            cur = cur.trieNodes[c - 'a'];
        }
        // 当前节点是否为某个单词
        return cur.isWord;
    }

}
