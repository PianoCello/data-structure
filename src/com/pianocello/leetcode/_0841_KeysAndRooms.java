package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示
 * 其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 *
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 *
 * @author PianoCello
 * @date 2020-06-26
 */
public class _0841_KeysAndRooms {

    /**
     * DFS 实现
     * 当第一次访问房间时，查看房间内的所有钥匙。
     * 如果有任意一个未被使用的钥匙，将其加入待办任务列表（stack）中，等待使用。
     *
     * 时间复杂度：O(N+E)，其中 N 是房间数量，E 是钥匙数量。
     * 空间复杂度：O(N)，用来存储 stack 和 seen。
     */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {

        boolean[] booleans = new boolean[rooms.size()];
        booleans[0] = true;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            for (Integer key : rooms.get(node)) {
                if (!booleans[key]) {
                    booleans[key] = true;
                    stack.push(key);
                }
            }
        }

        for (boolean aBoolean : booleans) {
            if (!aBoolean) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> keys = new ArrayList<>();
        keys.add(1);
        keys.add(3);
        List<Integer> keys2 = new ArrayList<>();
        keys2.add(3);
        keys2.add(2);
        keys2.add(1);
        List<Integer> keys3 = new ArrayList<>();
        keys3.add(2);
        List<Integer> keys4 = new ArrayList<>();
        keys4.add(0);

        rooms.add(keys);
        rooms.add(keys2);
        rooms.add(keys3);
        rooms.add(keys4);

        boolean b = canVisitAllRooms(rooms);
        System.out.println(b);

    }
}
