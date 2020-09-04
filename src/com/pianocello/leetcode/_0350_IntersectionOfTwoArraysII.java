package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * <p>
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * <p>
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 进阶:
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author PianoCello
 * @date 2020-07-10
 */
public class _0350_IntersectionOfTwoArraysII {

    /**
     * 解法一：使用 map 存储每个元素和出现次数
     * 时间复杂度：O(n+m)。其中 nn，mm 分别代表了数组的大小。
     * 空间复杂度：O(min(n,m))，我们对较小的数组进行哈希映射使用的空间。
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        // key --> val(出现次数)
        Map<Integer, Integer> m = new HashMap<>((int)(nums1.length/0.75));
        for (int n : nums1) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        int k = 0;
        for (int num : nums2) {
            int count = m.getOrDefault(num, 0);
            if (count > 0) {
                //使用原来的 nums 数组，降低空间复杂度
                nums1[k++] = num;
                //因为会存在重复元素需要再次加入
                m.put(num, count - 1);
            }
        }
        return Arrays.copyOf(nums1, k);
    }

    /**
     * 解法二：先排序，再求交集
     * 时间复杂度：O(nlogn+mlogm)。其中 n，m 分别代表了数组的大小。我们对数组进行了排序然后进行了线性扫描。
     * 空间复杂度：O(1)
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        //确保第一个数组的长度最小
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        //对数组进行排序 （偷懒）
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] res = new int[nums1.length];
        int index = 0;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            //相等就加入目标数组
            if (nums1[i] == nums2[j]) {
                res[index++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return Arrays.copyOf(res, index);
    }

}
