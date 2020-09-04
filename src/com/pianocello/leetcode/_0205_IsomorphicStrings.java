package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 *
 * @author PianoCello
 * @date 2020-07-11
 */
public class _0205_IsomorphicStrings {

    /**
     * 解法一：使用 HashMap 求两遍
     */
    public static boolean isIsomorphic(String s, String t) {
        return isIsomorphicHelp(s, t) && isIsomorphicHelp(t, s);
    }

    private static boolean isIsomorphicHelp(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(c2)) {
                    return false;
                }
            } else {
                map.put(c, c2);
            }
        }
        return true;
    }


    /**
     * 解法二：使用 HashMap 求一遍
     */
    public static boolean isIsomorphic2(String s, String t) {
        //关于 s -> t 的映射
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            //保证 s 中当前元素未建立映射
            if (map.get(sc) == null) {
                //保证 t 中当前元素未建立映射
                if (map.containsValue(tc)) {
                    return false;
                }
                //建立 s 中当前元素与 t 中当前元素一一映射关系
                map.put(sc, tc);
            } else if (map.get(sc) != tc) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解法三：用两个数组记录字符串出现的位置
     */
    public static boolean isIsomorphic3(String s, String t) {
        int[] indexs = new int[128];
        int[] indext = new int[128];

        for (int i = 0; i < s.length(); i++) {
            char chs = s.charAt(i);
            char cht = t.charAt(i);
            if (indexs[chs] != indext[cht])
                return false;
            //修改过就不用修改了
            if (indexs[chs] == 0) {
                indexs[chs] = i + 1;
                indext[cht] = i + 1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "add";
        String t = "szz";

//        String s = "egg";
//        String t = "add";

//        String s = "foo";
//        String t = "bar";

//        String s = "absdf";
//        String t = "cdghj";

        boolean isomorphic = isIsomorphic3(s, t);
        System.out.println(isomorphic);
    }

}
