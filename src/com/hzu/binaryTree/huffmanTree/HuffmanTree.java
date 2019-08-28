package com.hzu.binaryTree.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 哈夫曼树
 * @author zhanghuihong
 * @since 2019-08-24
 */
public class HuffmanTree {

    public static void main(String[] args) {

        int[] arr = {3,11,29,5,7,8,23,14};

        Node tree = createHuffmanTree(arr);

        System.out.println(tree);
    }

    //创建哈夫曼树
    public static Node createHuffmanTree(int[] arr) {

        List<Node> list = new ArrayList<>();

        //使用数组元素创建若干颗二叉树(只有一个节点)
        for (int val : arr) {
            list.add(new Node(val));
        }

        while (list.size() > 1) {
            //将集合排序(倒序)
            Collections.sort(list);
            //取出权值最小的一棵树
            Node left = list.get(list.size() - 1);
            //取出权值次小的一棵树
            Node right = list.get(list.size() - 2);
            //创建一棵新的二叉树
            Node parent = new Node(left.weight + right.weight, left, right);
            //将父节点加入list中
            list.add(parent);
            //将取出来的两棵树移除
            list.remove(left);
            list.remove(right);
        }
        Node huffman = list.get(0);

        return huffman;
    }



}
