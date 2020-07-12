package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 提示：你可以假定该字符串只包含小写字母。
 *
 * 示例：
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 * @author PianoCello
 * @date 2020-07-12
 */
public class _0387_FirstUniqueCharacterInAString {

    /**
     * 解法一：使用 HashMap 记录字符和出现次数
     */
    public static int firstUniqChar(String s) {
        int len = s.length();
        // 定义时指定初始容量 避免 rehash
        Map<Character, Integer> map = new HashMap<>((int)(len/0.75));
        for (int i = 0; i < len; i++) {
            // 初始值为 1，重复则累加
            map.merge(s.charAt(i), 1, (a, b) -> a + b);
        }
        for (int i = 0; i < len; i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 解法二：使用 数组 记录字符和出现次数
     */
    public static int firstUniqChar2(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        // a-z 的 ascii 编码为 97 - 122
        int[] temp = new int[26];
        for (char c : chars) {
            temp[c-'a']++;
        }
        for (int i = 0; i < len; i++) {
            if (temp[chars[i]-'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        String s = "leetcode";
//        String s = "loveleetcode";
        int i = firstUniqChar2(s);
        System.out.println(i);
    }
}
