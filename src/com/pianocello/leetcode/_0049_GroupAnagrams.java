package com.pianocello.leetcode;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * @author PianoCello
 * @date 2020-07-12
 */
public class _0049_GroupAnagrams {

    /**
     * 解法一：将字符串按字符排序 返回值作为 HashMap 的键
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            //对字符串按字符进行排序 返回值做键
            String key = sortStr(str);
            List<String> list;
            if ((list = map.get(key)) == null) {
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    private static String sortStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * 小写字母 ACSII 码 - 97 以后和质数的对应规则
     */
    private static int[] primes = {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
            31, 37, 41, 43, 47, 53, 59, 61, 67,
            71, 73, 79, 83, 89, 97, 101};

    /**
     * 解法二：自定义字符串的哈希规则，使用质数作为乘法因子
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        // key 是字符串自定义规则下的哈希值
        for (String str : strs) {
            int hashValue = 1;
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                hashValue *= primes[c - 'a'];
            }
            //提前定义变量可以减少操作
            List<String> list;
            if ((list = map.get(hashValue)) == null) {
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(hashValue, list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strs);

        System.out.println(lists);
    }
}
