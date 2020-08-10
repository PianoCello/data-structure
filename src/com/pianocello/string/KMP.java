package com.pianocello.string;

/**
 * KMP 算法模板
 * 时间复杂度 O(m+n)。m 是文本串的长度，n 是模式串的长度。
 * 空间复杂度 O(n)。
 *
 * @author PianoCello
 * @date 2020-07-05
 */
public class KMP {

    /**
     * KMP 匹配主方法
     *
     * @param str     文本串
     * @param pattern 模式串
     * @return 模式串第一次出现的索引，如果没有匹配到返回 -1
     */
    public static int match(String str, String pattern) {
        // 构造 next 表
        int[] next = buildNext(pattern);

        int sLen = str.length();
        int pLen = pattern.length();
        // i 和 j 分别是文本串和模式串的指针
        int i = 0, j = 0;
        while (i < sLen && j < pLen) {
            // 若匹配，或 pattern 已移除最左侧
            if (j < 0 || str.charAt(i) == pattern.charAt(j)) {
                // 则转到下一字符
                i++;
                j++;
            } else {
                // 模式串回退 （文本串不用回退）
                j = next[j];
            }
        }
        //没能成功匹配到字符串
        if (j != pLen) return -1;

        return i - j;
    }

    /**
     * 构造模式串 p 的 next 表
     */
    private static int[] buildNext(String p) {
        int m = p.length();
        int[] next = new int[m];

        // 最长公共前缀后缀长度 第0位为-1
        int t = next[0] = -1;
        // 模式串指针
        int j = 0;
        while (j < m - 1) {
            if (t < 0 || p.charAt(j) == p.charAt(t)) {
                t++;
                j++;
                // 此句可改进为 next[j] = (p.charAt(j) != p.charAt(t) ? t : next[t]);
                next[j] = t;
            } else {
                // 失配
                t = next[t];
            }
        }
        return next;
    }

    public static void main(String[] args) {

        String str = "asdszdvabcdabc";
        String pattern = "abcdabcsx";

        int index = match(str, pattern);

        System.out.println(index);
    }
}
