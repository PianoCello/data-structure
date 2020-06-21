package com.pianocello.leetcode;

import java.util.*;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * <p>
 * 实例：
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 * @author PianoCello
 * @date 2020-06-20
 */
public class _0752_OpenTheLock {

    public static int openLock2(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        queue.offer(null);

        Set<String> visited = new HashSet<>();
        visited.add("0000");

        int step = 0;
        while (!queue.isEmpty()) {
            String node = queue.poll();
            //node = null 说明这一层遍历完了
            if (node == null) {
                step++;
                if (queue.peek() != null)
                    queue.offer(null);
            } else if (node.equals(target)) {
                return step;
            } else if (!dead.contains(node)) {
                for (int i = 0; i < 4; ++i) {
                    for (int d = -1; d <= 1; d += 2) {
                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
                        String nei = node.substring(0, i) + ("" + y) + node.substring(i + 1);
                        if (!visited.contains(nei)) {
                            visited.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * BFS 实现
     */
    public static int openLock(String[] deadends, String target) {
        //步数即是最短路径
        int step = 0;
        //死亡列表
        Set<String> ends = new HashSet<>(Arrays.asList(deadends));

        //记录已经访问过的节点（deadends 也可以看看作是访问过了）
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        //将根节点加入队列中
        queue.add("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {

            int size = queue.size();
            //将当前队列中的所有节点向四周扩散
            for (int i = 0; i < size; i++) {
                String top = queue.poll();
                //忽略死亡列表
                if (ends.contains(top)) {
                    continue;
                }
                //找到目标 直接返回
                if (top.equals(target)) {
                    return step;
                }
                //找出 top 的相邻节点 只能向上和向下转4次
                for (int j = 0; j < 4; j++) {
                    String minusOne = minusOne(top, j);
                    if (!visited.contains(minusOne)) {
                        visited.add(minusOne);
                        queue.add(minusOne);
                    }
                    String plusOne = plusOne(top, j);
                    if (!visited.contains(plusOne)) {
                        visited.add(plusOne);
                        queue.add(plusOne);
                    }
                }
            }
            ++step;
        }
        return -1;
    }

    //将 top[j] 向下拨动一次
    private static String minusOne(String top, int j) {
        char[] chars = top.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }
        return new String(chars);
    }

    //将 top[j] 向上拨动一次
    private static String plusOne(String top, int j) {
        char[] chars = top.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }
        return new String(chars);
    }

    public static void main(String[] args) {

        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        int i = openLock(deadends, target);

        System.out.println(i);
    }

}
