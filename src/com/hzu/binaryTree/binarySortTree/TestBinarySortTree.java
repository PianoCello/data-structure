package com.hzu.binaryTree.binarySortTree;

/**
 * @author zhanghuihong
 * @since 2019-08-29
 */
public class TestBinarySortTree {

    public static void main(String[] args) {

        int[] arr = {7,3,10,5,1,12,9};

        //创建一棵树
        BinarySortTree sortTree = new BinarySortTree();

        for (int i : arr) {
            sortTree.add(new Node(i));
        }

        sortTree.middleShow();
        //查找
//        System.out.println(sortTree.search(12));

        System.out.println("=============");

        //删除节点
        sortTree.delete(7);
        sortTree.delete(3);
        sortTree.delete(1);
        sortTree.delete(12);
        sortTree.delete(9);
        sortTree.middleShow();


    }


}
