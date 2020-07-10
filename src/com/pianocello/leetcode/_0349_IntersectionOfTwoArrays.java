package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * <p>
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *  
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * @author PianoCello
 * @date 2020-07-10
 */
public class _0349_IntersectionOfTwoArrays {

    /**
     * 使用两个 Set 去重
     * 时间复杂度：O(m+n)，其中 n 和 m 是数组的长度。
     * 将 nums1 转换为集合需要 O(n) 的时间，类似地，将 nums2 转换为集合需要 O(m) 的时间。
     * 而在平均情况下，集合的 in/contains 操作只需要 O(1) 的时间。
     * 空间复杂度：O(m+n)，最坏的情况是数组中的所有元素都不同。
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
            }
        }
        int[] ints = new int[res.size()];
        int i = 0;
        for (Integer integer : res) {
            ints[i++] = integer;
        }
        return ints;
    }

}
