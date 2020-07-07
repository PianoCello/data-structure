package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author PianoCello
 * @date 2020-07-07
 */
public class _0283_MoveZeroes {

    /**
     * 解法一：双指针一次遍历
     */
    public static void moveZeroes(int[] nums) {
        for (int i = 0, j = 0; i < nums.length; i++) {
            //当前元素 !=0，就把其交换到左边，等于 0 的交换到右边
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }

    /**
     * 解法二：对解法一的优化
     */
    public void moveZeroes2(int[] nums) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return;
        }
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] != 0) {
                //避免了数组开头是非零元素的交换也就是阻止（i==j）时交换。
                if (i > j) {
                    //当i > j 时，只需要把 i 的值赋值给 j 并把原位置的值置0。
                    //同时这里也把交换操作换成了赋值操作，减少了一条操作语句，理论上能更节省时间。
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = {1, 0, 2, 3};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
