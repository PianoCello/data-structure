package com.hzu.graph;

import com.hzu.stack.Stack;

/**
 * 数据结构之图(无向图)
 *
 * @author PianoCello
 * @date 2019-09-01
 */
public class Graph {

    //存储顶点的数组
    private Vertex[] vertices;
    //当前顶点数组的大小
    private int currentSize;
    //存储邻接矩阵(边) adjacency matrix
    int[][] adjMatrix;
    //当前遍历的位置
    private int currentIndex;


    /** 初始化图
     * @param size
     */
    public Graph(int size) {
        this.vertices = new Vertex[size];
        adjMatrix = new int[size][size];
        //将自身的值设为1
        for (int i = 0; i < size; i++) {
            adjMatrix[i][i] = 1;
        }
    }

    /**
     * 添加顶点
     * @param value
     */
    public void addVertex(Vertex value) {
        vertices[currentSize++] = value;
    }

    /**
     * 添加边
     * @param val1
     * @param val2
     */
    public void addEdge(String val1 , String val2) {

        //取出顶点存储在数组中的索引
        int index1 = getIndex(val1);
        int index2 = getIndex(val2);
        if (index1 < 0 || index2 < 0) {
            throw new RuntimeException("输入的顶点不存在");
        } else if(index1 == index2) {
            throw new RuntimeException("输入的顶点相同");
        }

        //将邻接矩阵对应的位置设为1
        adjMatrix[index1][index2] = 1;
        adjMatrix[index2][index1] = 1;
    }

    /**
     * 根据顶点的值获取存储数组的索引
     * @param val1
     * @return
     */
    private int getIndex(String val1) {
        //没找到对应的索引返回-1
        int index = -1;
        for (int i = 0; i < currentSize; i++) {
            if (vertices[i].getValue().equals(val1)) {
                index = i;
                break;//找到就结束循环
            }
        }
        return index;
    }

    /**
     * 深度优先遍历图(DFS),使用栈辅助实现
     */
    public void depthFirstSearch() {

        //遍历第一个元素
        System.out.println(vertices[currentIndex]);
        //将已经遍历的元素设为已访问
        vertices[currentIndex].visited = true;
        //将已经遍历的元素的位置压入栈
        Stack stack = new Stack();
        stack.push(currentIndex);

        //栈不为空的时候一直遍历
        out: while (!stack.isEmpty()) {
            //遍历数组后面的元素
            for (int i = currentIndex + 1; i < vertices.length; i++) {
                //如果和下一个元素是通的,还未访问过的
                if (adjMatrix[currentIndex][i] == 1 && !vertices[i].visited) {
                    System.out.println(vertices[i].getValue());
                    vertices[i].visited = true;
                    stack.push(i);
                    //结束当前循环,开始下一次循环
                    continue out;
                }
            }
            //将栈顶元素弹出
            stack.pop();
            if (!stack.isEmpty()) {
                currentIndex = stack.top();
            }
        }
    }

    /**
     * 广度优先遍历图(BFS),使用队列辅助实现
     */
    public void broadFirstSearch() {

    }

}
