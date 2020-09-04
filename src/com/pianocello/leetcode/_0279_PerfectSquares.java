package com.pianocello.leetcode;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * <p>
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * @author PianoCello
 * @date 2020-06-21
 */
public class _0279_PerfectSquares {

    /**
     * 解法一
     * 拉格朗日 四平方数和定理 ：
     * 任何正整数都可以拆分成不超过4个数的平方和 ---> 答案只可能是1,2,3,4
     * <p>
     * 如果一个数是4个数的平方和，则这个数还满足 n = (4^a)*(8b+7)
     * ---> 因此可以先看这个数是否满足上述公式，如果不满足，答案就是1,2,3了
     * <p>
     * 如果这个数本来就是某个数的平方，那么答案就是1，否则答案就只剩2,3了
     * 如果答案是2，即n=a^2+b^2，那么我们可以枚举a，来验证，如果验证通过则答案是2
     * 否则答案是3
     */
    public static int numSquares2(int n) {
        int temp = n;
        //先根据上面提到的公式来缩小n
        while (temp % 4 == 0) {
            temp /= 4;
        }
        //如果满足公式 则返回4
        if (temp % 8 == 7) {
            return 4;
        }
        //如果能枚举出 a 和 b 的话
        int a = 0;
        while (a * a <= n) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                if (a != 0 && b != 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
            ++a;
        }
        return 3;
    }

    private static boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }

    /**
     * 解法二
     * 对解法一代码的简化
     */
    public static int numSquares3(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }

        if (isSquare(n)) {
            return 1;
        }
        for (int i = 1; i * i <= n; ++i) {
            if (isSquare(n - i * i)) {
                return 2;
            }
        }
        return 3;
    }

    /**
     * 解法三
     * BFS 广度优先搜索
     */
    public static int numSquares(int n) {
        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.addLast(new Pair<>(n, 0));

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> front = queue.removeFirst();
            int num = front.getKey();
            int step = front.getValue();

            if (num == 0)
                return step;

            for (int i = 1; num - i * i >= 0; i++) {
                int a = num - i * i;
                if (!visited[a]) {
                    if (a == 0) return step + 1;
                    queue.addLast(new Pair<>(num - i * i, step + 1));
                    visited[num - i * i] = true;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        int n = 12;
        int j = numSquares3(n);
        System.out.println(j);
//        int i = numSquares(n);
//        System.out.println(i);

//        System.out.println(Math.pow());
    }
}
