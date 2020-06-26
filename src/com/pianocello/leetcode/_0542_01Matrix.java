package com.pianocello.leetcode;

import java.util.*;

/**
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 *
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * @author PianoCello
 * @date 2020-06-26
 */
public class _0542_01Matrix {

    /**
     * 解法一：
     * 多源最短路径问题（使用多源广度优先搜索），通过添加超级源点转化为单源广度优先搜搜
     *
     * 从 1 到 0 的距离也是从 0 到 1 的距离，把所有的 0 看成一个整体（超级零）
     * 任意一个 1 到它最近的 0 的距离，会等于这个 1 到「超级零」的距离减去一
     * 由于我们只有一个「超级零」，我们就以它为起点进行广度优先搜索。
     *
     * 这个「超级零」只和矩阵中的 0 相连，所以在广度优先搜索的第一步中
     * 超级零」会被弹出队列，而所有的 0 会被加入队列，它们到「超级零」的距离为 1
     *
     */
    public static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        //首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是未被访问过的 1
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(i * col + j);
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        //用于方便遍历上下左右的四个点
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            int x = node / col;
            int y = node % col;
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= 0 && newX < row && newY >= 0 && newY < col
                        && matrix[newX][newY] == -1) {
                    //这个点到 0 的距离就可以更新成 matrix[x][y] + 1
                    matrix[newX][newY] = matrix[x][y] + 1;
                    queue.offer(newX * col + newY);
                }
            }
        }
        return matrix;
    }

    public static int[][] updateMatrix3(int[][] matrix) {
        // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        //用于表示上下左右的四个点
        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 如果四邻域的点是 -1，表示这个点是未被访问过的 1
                // 所以这个点到 0 的距离就可以更新成 matrix[x][y] + 1。
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[newX][newY] == -1) {
                    matrix[newX][newY] = matrix[x][y] + 1;
                    queue.offer(new int[] {newX, newY});
                }
            }
        }

        return matrix;
    }

    /**
     * 解法二：自己写的
     * BFS 对每一个 1 执行 BFS，找出最短的距离
     * 缺点：由于每个 1 都计算，存在重复计算，不能很好的使用邻居已经得到的结果
     */
    public static int[][] updateMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i * col + j);
                Set<Integer> visited = new HashSet<>();
                visited.add(i * col + j);
                //步长就是最短距离
                int step = 0;
                outer:
                while (!queue.isEmpty()) {
                    ++step;
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {

                        Integer node = queue.poll();
                        int r = node / col;//横坐标
                        int c = node % col;//纵坐标

                        if (r - 1 >= 0) {
                            if (matrix[r - 1][c] == 0) {
                                break outer;
                            } else {
                                if (!visited.contains((r - 1) * col + c)) {
                                    queue.offer((r - 1) * col + c);
                                }
                            }
                        }

                        if (r + 1 < row) {
                            if (matrix[r + 1][c] == 0) {
                                break outer;
                            } else {
                                if (!visited.contains((r + 1) * col + c)) {
                                    queue.offer((r + 1) * col + c);
                                }
                            }
                        }

                        if (c - 1 >= 0) {
                            if (matrix[r][c - 1] == 0) {
                                break outer;
                            } else {
                                if (!visited.contains(r * col + c - 1)) {
                                    queue.offer(r * col + c - 1);
                                }
                            }
                        }

                        if (c + 1 < col) {
                            if (matrix[r][c + 1] == 0) {
                                break outer;
                            } else {
                                if (!visited.contains(r * col + c + 1)) {
                                    queue.offer(r * col + c + 1);
                                }
                            }
                        }
                    }
                }

                //将最短距离赋值到对应坐标上
                matrix[i][j] = step;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        int[][] ints = updateMatrix(matrix);

        System.out.println(Arrays.deepToString(ints));
    }

}
