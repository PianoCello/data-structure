package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * @author PianoCello
 * @date 2020-06-17
 */

public class _0739_Daily_Temperatures {
    /**
     * 根据题意，从最后一天推到第一天，这样会简单很多。因为最后一天显然不会再有升高的可能，结果直接为0。
     * 再看倒数第二天的温度，如果比倒数第一天低，那么答案显然为1，如果比倒数第一天高，又因为倒数第一天
     * 对应的结果为0，即表示之后不会再升高，所以倒数第二天的结果也应该为0。
     * 自此我们容易观察出规律，要求出第i天对应的结果，只需要知道第i+1天对应的结果就可以：
     * - 若T[i] < T[i+1]，那么res[i]=1；
     * - 若T[i] > T[i+1]
     * - res[i+1]=0，那么res[i]=0;
     * - res[i+1]!=0，那就比较T[i]和T[i+1+res[i+1]]（即将第i天的温度与比第i+1天大的那天的温度进行比较）
     */
    //解法一
    public static int[] dailyTemperatures(int[] temp) {
        //返回列表
        int[] res = new int[temp.length];

        for (int i = temp.length - 2; i >= 0; i--) {
            int j = i + 1;
            //若T[i] < T[i+1]，那么res[i]=1；
            if (temp[i] < temp[j]) {
                res[i] = 1;
            } else {
                if (res[j] == 0) {
                    res[i] = 0;
                } else {
                    j = j + res[j];
                    while (j < temp.length) {
                        if (temp[i] >= temp[j]) {
                            //后面没有更大的了
                            if (res[j] == 0) {
                                break;
                            }
                            j = j + res[j];
                        } else {
                            res[i] = j - i;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    //解法二：解法一代码的优化
    public static int[] dailyTemperatures2(int[] temp) {
        int[] res = new int[temp.length];
        for (int i = temp.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < temp.length; j += res[j]) {
                if (temp[i] < temp[j]) {
                    res[i] = j - i;
                    break;
                } else if (res[j] == 0) {
                    res[i] = 0;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解法三：使用单调栈存储数组下标
     * for (遍历这个数组) {
     * while (栈不为空 && 栈顶元素小于当前元素) {
     * 栈顶元素出栈;
     * 更新结果;
     * }
     * 当前元素入栈;
     * }
     */
    public static int[] dailyTemperatures3(int[] temp) {
        //存储 length 加快速度
        int length = temp.length;
        int[] res = new int[length];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            int temperature = temp[i];//加快速度
            //栈不为空 && 栈顶元素小于当前元素
            while (!stack.isEmpty() && temp[stack.peek()] < temperature) {
                Integer pop = stack.pop();
                res[pop] = i - pop;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {

        int[] temperatures = {55, 38, 53, 81, 61, 93, 97, 32, 43, 78};
//        int[] ints = dailyTemperatures(temperatures);
//        int[] ints = dailyTemperatures2(temperatures);
        int[] ints = dailyTemperatures3(temperatures);

        System.out.println(Arrays.toString(ints));
    }
}