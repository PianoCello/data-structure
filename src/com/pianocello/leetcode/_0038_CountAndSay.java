package com.pianocello.leetcode;

/**
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * 注意：整数序列中的每一项将表示为一个字符串。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 6.     312211
 * 7.     13112221
 * 8.     1113213211
 * 9.     31131211131221
 * <p>
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “一个 1 ”，记作 11
 * 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21
 * 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211
 * 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
 *
 * @author PianoCello
 * @date 2020-08-09
 */
public class _0038_CountAndSay {

    /**
     * 解法一：n 次循环
     */
    public static String countAndSay(int n) {
        StringBuilder res = new StringBuilder("1");
        if (n == 1) {
            return res.toString();
        }
        for (int i = 1; i < n; i++) {
            StringBuilder temp = new StringBuilder();
            int count = 1;
            for (int j = 0; j < res.length(); j++) {
                char cur = res.charAt(j);
                if (j + 1 < res.length() && res.charAt(j + 1) == cur) {
                    count++;
                } else {
                    temp.append(count).append(cur);
                    count = 1;
                }
            }
            res = temp;
        }
        return res.toString();
    }

    /**
     * 解法二：递归
     */
    public static String countAndSay2(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder res = new StringBuilder();
        String str = countAndSay2(n - 1);
        int length = str.length();
        int a = 0;
        for (int i = 1; i < length + 1; i++) {
            if (i == length) {
                return res.append(i - a).append(str.charAt(a)).toString();
            } else if (str.charAt(i) != str.charAt(a)) {
                res.append(i - a).append(str.charAt(a));
                a = i;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String say = countAndSay2(4);
        System.out.println(say);
    }
}
