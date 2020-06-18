package com.pianocello.binaryTree.avlTree;

/**
 * @author PianoCello
 * @date 2019-08-29
 */
public class TestAVLTree {

    public static void main(String[] args) {

//        int[] arr = {8,9,6,7,5,4};
//        int[] arr = {2,1,4,3,5,6};
        int[] arr = {9,10,6,4,7,8,3,2,1,0,-1};

        //创建一棵树
        AVLTree avlTree = new AVLTree();

        for (int i : arr) {
            avlTree.add(new Node(i));
        }

        avlTree.middleShow();
        System.out.println("=============");

        System.out.println(avlTree.root.height());
        System.out.println(avlTree.root);


    }


}
