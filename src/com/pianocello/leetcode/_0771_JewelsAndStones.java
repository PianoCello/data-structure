package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定字符串 J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。
 * S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * J 中的字母不重复，J 和 S 中的所有字符都是字母。字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 *
 * 示例 1:
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 *
 * 示例 2:
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 *
 * 注意:
 * S 和 J 最多含有50个字母。
 *  J 中的字符不重复。
 *
 * @author PianoCello
 * @date 2020-07-14
 */
public class _0771_JewelsAndStones {

    /**
     * 解法一：使用 HashSet 判断是否存在
     */
    public int numJewelsInStones(String J, String S) {
        //储存宝石的类型
        Set<Character> jewels = new HashSet<>((int)(J.length()/0.75));
        for (char c : J.toCharArray()) {
            jewels.add(c);
        }
        int num = 0;
        for (char c : S.toCharArray()) {
            if (jewels.contains(c)) {
                ++num;
            }
        }
        return num;
    }

    /**
     * 解法二：使用 数组 判断是否存在
     */
    public int numJewelsInStones2(String J, String S) {
        byte[] arr = new byte[58];
        for (char ch : J.toCharArray()) {
            arr[ch - 65] = 1;
        }
        int count = 0;
        for (char ch : S.toCharArray()) {
            if (arr[ch - 65] == 1) {
                count++;
            }
        }
        return count;
    }
}
