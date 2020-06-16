package com.hzu.binaryTree;

/**
 * 树的节点
 *
 * @author PianoCello
 * @date 2019-08-14
 */
public class TreeNode {

    //树的权值
    private int value;
    //树的左节点
    private TreeNode leftNode;
    //树的右节点
    private TreeNode rightNode;

    //设置左节点
    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    //设置右节点
    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public TreeNode(int value) {
        this.value = value;
    }

    //获取权值
    public int getValue() {
        return value;
    }

    //前序遍历
    public void frontShow() {
        //1.先取根节点
        System.out.println(value);

        //2.再取左节点
        if (leftNode != null) {
            leftNode.frontShow();
        }
        //3.再取右节点
        if (rightNode != null) {
            rightNode.frontShow();
        }
    }

    //中序遍历
    public void middleShow() {

        //1.先取左节点
        if (leftNode != null) {
            leftNode.middleShow();
        }

        //2.再取根节点
        System.out.println(value);

        //3.再取右节点
        if (rightNode != null) {
            rightNode.middleShow();
        }
    }

    //后序遍历
    public void backShow() {

        //1.先取左节点
        if (leftNode != null) {
            leftNode.backShow();
        }

        //2.再取右节点
        if (rightNode != null) {
            rightNode.backShow();
        }

        //3.再取根节点
        System.out.println(value);
    }

    //前序查找
    public TreeNode frontSearch(int i) {
        //临时变量
        TreeNode target = null;

        //如果查找的值与当前的值相同,直接返回
        if (value == i) {
            return this;
        } else {
            //查找左节点
            if (leftNode != null) {
                target = leftNode.frontSearch(i);
            }
            //如果target不为空,说明在左节点已经找到
            if (target != null) {
                return target;
            }
            //查找右节点
            if (rightNode != null) {
                target = rightNode.frontSearch(i);
            }
        }

        return target;
    }

    //删除节点
    public void remove(int i) {

        TreeNode parent = this;

        //删除的是左节点
        if (leftNode != null && leftNode.value == i) {
            leftNode = null;
            return;
        }

        //删除的是右节点
        if (rightNode != null && rightNode.value == i) {
            rightNode = null;
            return;
        }

        //递归删除左子节点
        parent = leftNode;
        if (parent!=null) {
            parent.remove(i);
        }

        //递归删除右子节点
        parent = rightNode;
        if (parent!=null) {
            parent.remove(i);
        }
    }
}
