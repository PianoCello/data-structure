package com.hzu.binaryTree.arrayBinaryTree;

/**
 * 二叉树(顺序存储结构),这里只讨论完全二叉树
 * <p>
 * n为数组的下标
 * 第n个元素的父节点: (n-1)/2
 * 第n个元素的左子节点: 2n+1
 * 第n个元素的右子节点: 2n+2
 *
 * @author PianoCello
 * @date 2019-08-14
 */
public class ArrayBinaryTree {

    int[] data;

    public ArrayBinaryTree(int[] data) {
        this.data = data;
    }

    //前序遍历入口方法
    public void frontShow() {
        frontShow(0);
    }

    //前序遍历,传入遍历的开始位置
    public void frontShow(int index) {

        //如果数组为空--(空树)
        if (data == null || data.length == 0) {
            return;
        }

        //先取根节点
        System.out.println(data[index]);

        //再取左节点
        if (2 * index + 1 < data.length) {
            frontShow(2 * index + 1);
        }

        //再取右节点
        if (2 * index + 2 < data.length) {
            frontShow(2 * index + 2);
        }
    }

}
