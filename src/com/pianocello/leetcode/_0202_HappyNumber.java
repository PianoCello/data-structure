package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 * 示例：
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * @author PianoCello
 * @date 2020-07-10
 */
public class _0202_HappyNumber {

    private static Set<Integer> set = new HashSet<>();

    /**
     * 解法一：用 HashSet 检测循环 递归求解
     */
    public static boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        String s = String.valueOf(n);
        int length = s.length();
        int sum = 0;
        for (int i = 0; i < length; i++) {
            int c = s.charAt(i) - '0';
            sum += c * c;
        }
        if (set.contains(sum)) {
            return false;
        }
        set.add(sum);
        return isHappy(sum);
    }

    /**
     * 解法二：用 HashSet 检测循环 非递归求解
     * 时间复杂度：O(logn)
     * 空间复杂度：O(logn)
     */
    public boolean isHappy2(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = nextSum(n);
        }
        return n == 1;
    }

    /**
     * 解法三：快慢指针法
     * 不快乐的数一定会形成死循环 快慢指针可以检测是否存在循环
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public static boolean isHappy3(int n) {
        int slow = n;
        int fast = nextSum(n);
        while (fast != 1 && fast != slow) {
            slow = nextSum(slow);
            fast = nextSum(nextSum(fast));
        }
        return fast == 1;
    }

    private static int nextSum(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println(isHappy(4156));
    }

}
