package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author PianoCello
 * @date 2020-08-17
 */
public class _0046_Permutations {

    /**
     * 解法一：回溯法
     * 使用回溯应该注意
     * 1. 路径
     * 2. 选择列表
     * 3. 终止条件
     */
    private final List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        // 记录路径
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(nums, used, track);
        return res;
    }

    private void backTrack(int[] nums, boolean[] used, LinkedList<Integer> track) {
        // 结束条件 已经没有选择列表了
        if (nums.length == track.size()) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            // 跳过已经选择过的数字
            if (used[i]) {
                continue;
            }
            // 做出选择
            used[i] = true;
            track.add(cur);
            // 进入下一层决策树
            backTrack(nums, used, track);
            // 撤销选择
            used[i] = false;
            track.removeLast();
        }
    }

    /**
     * 解法二：LeetCode 题解
     */
    private final List<List<Integer>> ans = new LinkedList<>();
    public List<List<Integer>> permute2(int[] nums) {
        // 当前排列为 output
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        backTrack(nums.length, output, 0);
        return ans;
    }

    private void backTrack(int n, List<Integer> output, int first) {
        // 所有数都填完了
        if (first == n) {
            ans.add(new ArrayList<>(output));
            return;
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backTrack(n, output, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

}
