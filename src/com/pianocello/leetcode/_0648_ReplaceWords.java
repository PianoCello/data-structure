package com.pianocello.leetcode;

import java.util.List;

/**
 * 在英语中，我们有一个叫做 词根 (root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为  (successor)。
 * 例如，词根 an，跟随着单词 other (其他)，可以形成新的单词 another (另一个)。
 * <p>
 * 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。
 * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 * <p>
 * 示例：
 * 输入：dict(词典) = ["cat", "bat", "rat"] sentence(句子) = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 *
 * @author PianoCello
 * @date 2020-07-25
 */
public class _0648_ReplaceWords {

    /**
     * 构建前缀树
     */
    private class Trie {

        private class TrieNode {
            // 当前节点是否代表一个单词
            boolean isWord;

            // 子节点集合
            TrieNode[] trieNodes = new TrieNode[26];

            // 判断当前节点是否存在目标子节点
            boolean exist(char c) {
                return trieNodes[c - 'a'] != null;
            }
        }

        // 前缀树的根节点
        private TrieNode root = new TrieNode();

        private void insert(String word) {
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

        private String getPrefix(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.exist(c)) {
                    // 存在此单词的词根
                    if (cur.trieNodes[c - 'a'].isWord) {
                        return word.substring(0, i + 1);
                    }
                } else {
                    // 不存在此单词的词根
                    return word;
                }
                cur = cur.trieNodes[c - 'a'];
            }
            return word;
        }
    }

    private Trie trie = new Trie();

    /**
     * 解法一：构建前缀树 然后根据前缀树替换单词
     */
    public String replaceWords(List<String> dict, String sentence) {
        // 将词根放进前缀树中
        for (String word : dict) {
            trie.insert(word);
        }
        // 在前缀树查找是否有此单词的词根
        StringBuilder builder = new StringBuilder();
        String[] words = sentence.split(" ");
        for (String s : words) {
            builder.append(trie.getPrefix(s)).append(" ");
        }
        // 去掉最后的空格
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

}
