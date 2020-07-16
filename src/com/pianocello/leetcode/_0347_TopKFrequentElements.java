package com.pianocello.leetcode;

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * @author PianoCello
 * @date 2020-07-16
 */
public class _0347_TopKFrequentElements {

    /**
     * 解法一：使用 HashMap 记录元素和出现次数 再用优先级队列过滤元素
     * 时间复杂度：O(Nlog(k))。Counter 方法的复杂度是 O(N)，建堆和输出的复杂度是 O(Nlog(k))。
     * 空间复杂度：O(N)，存储哈希表的开销。
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // key 为元素   val 为频次
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (a, b) -> a + b);
        }
        //使用优先级队列 并且及时删除优先级最高的
        PriorityQueue<Integer> priority = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        for (Integer key : map.keySet()) {
            priority.add(key);
            if (priority.size() > k)
                priority.poll();
        }
        int[] res = new int[k];
        k = 0; // 重复利用变量
        for (Integer key : priority) {
            res[k++] = key;
        }
        return res;
    }


    public static void main(String[] args) {

        int[] nums = {-1,-1};
        int[] ints = topKFrequent(nums, 1);
        System.out.println(Arrays.toString(ints));
    }
}
