package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 * @author PianoCello
 * @date 2020-07-09
 */
public class _0219_ContainsDuplicateII {

    /**
     * 使用 HashSet 实现伪 LRU 即可 (滑动窗口)
     *
     * 时间复杂度：O(n) 我们会做 nn 次 搜索，删除，插入 操作，每次操作都耗费常数时间。
     * 空间复杂度：O(min(n,k))
     * 开辟的额外空间取决于散列表中存储的元素的个数，也就是滑动窗口的大小 O(min(n,k))。
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            //加入最久的那个需要被删除
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

}
