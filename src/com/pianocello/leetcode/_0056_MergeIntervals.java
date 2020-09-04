package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @author PianoCello
 * @date 2020-07-02
 */
public class _0056_MergeIntervals {

    /**
     * 先对区间数组排序 再合并
     */
    public static int[][] merge(int[][] ints) {
        // 先按照区间起始位置排序
        Arrays.sort(ints, Comparator.comparingInt(v -> v[0]));
        // 遍历区间
        int[][] res = new int[ints.length][2];
        int idx = -1;
        for (int[] cur : ints) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || cur[0] > res[idx][1]) {
                res[++idx] = cur;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], cur[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

    /**
     *
     */
    public static int[][] merge2(int[][] intervals) {
        int p = 0, q = intervals.length;
        int np = p + 1, nq = q - 1;
        while (p < q) {
            while (np <= nq) {
                // 有交集
                // 此处hhf进行修改，将较长的逻辑运算变成计算运算，耗时不受影响，甚至降低
//                (intervals[np][0] <= intervals[p][1] && intervals[p][0] <= intervals[np][1])
//                        || (intervals[p][0] <= intervals[np][1] && intervals[np][0] <= intervals[p][1])
                if ((intervals[p][1] - intervals[np][0]) * (intervals[p][0] - intervals[np][1]) <= 0) {
                    intervals[p][1] = Math.max(intervals[p][1], intervals[np][1]);
                    intervals[p][0] = Math.min(intervals[p][0], intervals[np][0]);
                    q--;
                    intervals[np] = intervals[nq];
                    np = p + 1;
                    nq--;
                } else {
                    np++;
                }
            }
            p++;
            np = p + 1;
            nq = q - 1;
        }
        int[][] res = new int[q][];
        System.arraycopy(intervals, 0, res, 0, q);
        return res;
    }

    public static void main(String[] args) {

        int[][] ints = {
                {1, 4},
                {2, 3}
        };

        int[][] merge = merge2(ints);
        System.out.println(Arrays.deepToString(merge));
    }

}
