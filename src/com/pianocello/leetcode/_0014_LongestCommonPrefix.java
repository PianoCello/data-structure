package com.pianocello.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * @author PianoCello
 * @date 2020-07-03
 */
public class _0014_LongestCommonPrefix {

    /**
     * 解法一：横向扫描法
     * 先找出前两个字符串的最长公共前缀，再查找和第三个字符串，以此类推...
     * 算法核心：LCP(s1...sn) = LCP(LCP(LCP(s1,s2),s3),...sn)
     * <p>
     * 时间复杂度：O(mn)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。
     * 最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
     * 空间复杂度：O(1)。使用的额外空间复杂度为常数。
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String lcp = strs[0];
        for (int i = 1; i < strs.length; i++) {
            lcp = commonPrefix(lcp, strs[i]);
            //不用继续找了 后面找不到了
            if (lcp.equals("")) {
                return lcp;
            }
        }
        return lcp;
    }

    /**
     * 找出两个字符串的最长公共前缀
     *
     * @param str1
     * @param str2
     */
    private static String commonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 解法二：纵向扫描法
     * 从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较
     * 如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
     * <p>
     * 时间复杂度：O(mn)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。
     * 最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
     * 空间复杂度：O(1)。使用的额外空间复杂度为常数。
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++) {
            char temp = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                //只要 i 大于等于要比较的字符串的长度 或对应位置的字符不相等
                if (i >= strs[j].length() || strs[j].charAt(i) != temp) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 解法三：分治法
     * 注意到 LCP 的计算满足结合律，有以下结论：
     * LCP(S1 ... Sn) = LCP(LCP(S1 ... Sk),LCP(Sk+1 ... Sn))
     * 其中 LCP(S1 ... Sn) 是字符串 S1 ... Sn 的最长公共前缀，1 < k < n
     * 基于上述结|论，可以使用分治法得到字符串数组中的最长公共前缀。
     * <p>
     * 时间复杂度：O(mn)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。
     * 空间复杂度：O(mlogn)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。
     */
    public static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    /**
     * 分治法 递归实现
     */
    private static String longestCommonPrefix(String[] strs, int begin, int end) {
        if (begin == end) {
            return strs[begin];
        } else {
            int mid = (begin + end) / 2;
            String lcpLeft = longestCommonPrefix(strs, begin, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    /**
     * 解法四：调用 api
     */
    public static String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(result) != 0) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * 解法五：使用前缀树
     */
    public static String longestCommonPrefix5(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        Trie trie = new Trie();
        // 将字符串加入前缀树
        for (String str : strs) {
            trie.insert(str);
        }

        Trie.TrieNode cur = trie.root;
        // 公共前缀一定存在于第一个字符串中
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            if (cur.triesNode[c - 'a'] != null && cur.triesNode[c - 'a'].count == strs.length) {
                cur = cur.triesNode[c - 'a'];
            } else {
                return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    /**
     * 实现前缀树
     */
    private static class Trie {
        private class TrieNode {
            // 记录字符出现的次数
            private int count;
            // 只包含小写字母
            private TrieNode[] triesNode = new TrieNode[26];
        }

        // 前缀树的根节点
        private TrieNode root = new TrieNode();

        public void insert(String str) {
            TrieNode cur = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (cur.triesNode[c - 'a'] == null) {
                    cur.triesNode[c - 'a'] = new TrieNode();
                }
                cur = cur.triesNode[c - 'a'];
                cur.count++;
            }
        }
    }

    public static void main(String[] args) {

        String[] strings = {"asdfvsd"};
//        String[] strings = {"flaaower", "flaaow", "qflaight"};
//        String[] strings = {"dog", "racecar", "car"};

        String s = longestCommonPrefix5(strings);

        System.out.println(s);
    }

}
