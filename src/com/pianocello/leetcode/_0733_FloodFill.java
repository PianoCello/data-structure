package com.pianocello.leetcode;

import java.util.*;

/**
 *  有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 *
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 *
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
 * 接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。
 * 将所有有记录的像素点的颜色值改为新的颜色值。最后返回经过上色渲染后的图像。
 *
 * 示例 1:
 * 输入:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 *
 * 解析:
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点。
 *
 * @author PianoCello
 * @date 2020-06-26
 */
public class _0733_FloodFill {

    static int row;
    static int col;
    static int old;

    /**
     * DFS 实现
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //新像素和原像素相同 直接返回
        if (image[sr][sc] == newColor) {
            return image;
        }
        //原来的像素值
        old = image[sr][sc];
        row = image.length;
        col = image[0].length;

        //深度优先搜索
        dfs(image,sr,sc,newColor);

        return image;
    }

    private static void dfs(int[][] image, int sr, int sc, int newColor) {
        //坐标不在图像之内
        if (sr < 0 || sr >= row || sc < 0 || sc >= col) {
            return;
        }
        if (image[sr][sc] != old) {
            return;
        }
        //重新赋值
        image[sr][sc] = newColor;
        dfs(image,sr+1,sc,newColor);
        dfs(image,sr-1,sc,newColor);
        dfs(image,sr,sc+1,newColor);
        dfs(image,sr,sc-1,newColor);
    }

    /**
     * BFS 实现
     */
    public static int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        //新像素和原像素相同 直接返回
        if (image[sr][sc] == newColor) {
            return image;
        }
        //原来的像素值
        int old = image[sr][sc];
        int row = image.length;
        int col = image[0].length;
        image[sr][sc] = newColor;

        //将根节点加入到队列中
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(sr * col + sc);

        //用于表示上下左右的四个点
        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            int r = node / col;//横坐标
            int c = node % col;//纵坐标
            //遍历上下左右四个坐标
            for (int i = 0; i < 4; i++) {
                int x = r + dx[i];
                int y = c + dy[i];
                if (x >= 0 && x < row && y >= 0 && y < col && image[x][y] == old) {
                    queue.offer(x * col + y);
                    image[x][y] = newColor;
                }
            }
        }
        return image;
    }


    public static void main(String[] args) {

        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int[][] image2 = {
                {0,0,0},
                {0,1,1}
        };

        int[][] ints = floodFill2(image,1,1,2);

        System.out.println(Arrays.deepToString(ints));
    }
}
