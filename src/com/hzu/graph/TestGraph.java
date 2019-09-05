package com.hzu.graph;

import java.util.Arrays;

/** 测试图
 * @author zhanghuihong
 * @since 2019-09-01
 */
public class TestGraph {
    public static void main(String[] args) {

        //创建顶点
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");

        //创建无向图
        Graph graph = new Graph(5);

        //添加顶点到图中
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");

        int[][] matrix = graph.adjMatrix;
        for (int[] arr : matrix) {
            //打印矩阵
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("--------------------------------------");

        graph.depthFirstSearch();


    }
}
