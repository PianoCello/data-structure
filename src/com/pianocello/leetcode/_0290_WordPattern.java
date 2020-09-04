package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 *
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 *
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 *
 * @author PianoCello
 * @date 2020-07-12
 */
public class _0290_WordPattern {

    /**
     * 使用 HashMap 将 key(a)-value(dog) 存在一起，每当遇到一个字母就去查看对应的单词。
     * 失败有两种情况：
     * 1.key 存在，经过查找字母对应的单词和这个单词不匹配；
     * 2.key 不存在，但是这个单词已经被存了；
     */
    public static boolean wordPattern(String pattern, String str) {
        int len = pattern.length();
        String[] s = str.split(" ");
        if (len != s.length) return false;

        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(s[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(s[i])) {
                    return false;
                }
                map.put(c, s[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {

//        String pattern = "abba";
//        String str = "dog dog dog dog";

        String pattern = "aaaa";
        String str = "sss sss sss sss";

        boolean b = wordPattern(pattern, str);
        System.out.println(b);
    }
}
