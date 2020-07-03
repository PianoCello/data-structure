package com.pianocello.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * @author PianoCello
 * @date 2020-07-03
 */
public class _0014LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {

        String res ="" + strs[0].charAt(0);
        for(String s : strs){

            if (s.startsWith(res)) {

            }

        }

        return "";
    }

}
