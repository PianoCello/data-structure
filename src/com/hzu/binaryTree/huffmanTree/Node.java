package com.hzu.binaryTree.huffmanTree;

/** 哈夫曼树节点
 * @author PianoCello
 * @date 2019-08-24
 */
public class Node implements Comparable<Node> {

    //节点的权值
    int weight;
    //节点的内容
    Byte data;

    Node left;
    Node right;

    public Node(int weight, Node left, Node right) {
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    public Node(int weight) {
        this.weight = weight;
    }

    public Node(int weight, Byte data) {
        this.weight = weight;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    //比较大小
    @Override
    public int compareTo(Node o) {
        return o.weight - this.weight;
    }
}
