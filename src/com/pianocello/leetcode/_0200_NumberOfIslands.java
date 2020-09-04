package com.pianocello.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 *
 * @author PianoCello
 * @date 2020-06-20
 */
public class _0200_NumberOfIslands {

    /**
     * DFS 查找
     * 扫描整个二维网格，如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。
     * 在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。
     * 最终岛屿的数量就是我们进行深度优先搜索的次数。
     * <p>
     * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
     * 空间复杂度：O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 MN。
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int num = 0;
        int m = grid.length;
        int n = grid[0].length;
        //顺序遍历二维数组 找到第一个 1 就启动 DFS
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                dfs(grid, i, j);
                num++;
            }
        }
        return num;
    }

    /**
     * 递归实现深度优先搜索  将访问过的坐标置 0
     */
    private static void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        //本身是 0 无须操作
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    /**
     * BFS 查找
     * 扫描整个二维网格，如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。
     * 在广度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。直到队列为空，搜索结束。
     * 最终岛屿的数量就是我们进行广度优先搜索的次数。
     * <p>
     * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
     * 空间复杂度：O(min(M,N))，在最坏情况下，整个网格均为陆地，队列的大小可以达到 min(M,N)。
     */
    public static int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int num = 0;
        int row = grid.length;
        int column = grid[0].length;

        //顺序遍历二维数组 找到第一个 1 就启动 BFS
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                if (grid[i][j] == '1') {
                    //广度优先搜索的次数
                    ++num;
                    grid[i][j] = '0';

                    Queue<Integer> queue = new LinkedList<>();
                    //用一个数存储二维数组的坐标 i*column+j
                    queue.add(i * column + j);
                    while (!queue.isEmpty()) {
                        Integer node = queue.remove();
                        int r = node / column;
                        int c = node % column;

                        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                            queue.add((r - 1) * column + c);
                            grid[r - 1][c] = '0';
                        }
                        if (r + 1 < row && grid[r + 1][c] == '1') {
                            queue.add((r + 1) * column + c);
                            grid[r + 1][c] = '0';
                        }

                        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                            queue.add(r * column + c - 1);
                            grid[r][c - 1] = '0';
                        }
                        if (c + 1 < column && grid[r][c + 1] == '1') {
                            queue.add(r * column + c + 1);
                            grid[r][c + 1] = '0';
                        }
                    }
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '1'},
                {'1', '1', '0', '1', '0'},
                {'0', '1', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
//        int i = numIslands(grid);
        int i = numIslands2(grid);
        System.out.println(i);
    }
}