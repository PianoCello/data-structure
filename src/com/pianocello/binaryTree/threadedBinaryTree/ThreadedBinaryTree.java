package com.pianocello.binaryTree.threadedBinaryTree;

/**
 * 数据结构之二叉树(链式存储结构实现)
 * 线索化二叉树
 *
 * @author PianoCello
 * @date 2019-08-14
 */
public class ThreadedBinaryTree {

    //树的根节点
    private ThreadedTreeNode rootNode;

    //临时保存当前节点的前驱节点
    private ThreadedTreeNode pre;


    //设置根节点
    public void setRootNode(ThreadedTreeNode rootNode) {
        this.rootNode = rootNode;
    }

    //前序遍历
    public void frontShow() {
        if (rootNode != null) {
            rootNode.frontShow();
        }
    }

    //中序遍历
    public void middleShow() {
        if (rootNode != null) {
            rootNode.middleShow();
        }
    }

    //后序遍历
    public void backShow() {
        if (rootNode != null) {
            rootNode.backShow();
        }
    }

    //前序查找
    public ThreadedTreeNode frontSearch(int i) {
        return rootNode.frontSearch(i);
    }

    //删除节点
    public void remove(int i) {

        //没有节点
        if (rootNode == null) {
            return;
        }

        //如果要删除的是根节点
        if (rootNode.getValue() == i) {
            rootNode = null;
            return;
        }

        //删除的是子节点
        rootNode.remove(i);

    }

    //中序线索化二叉树
    public void threadNodes() {
        threadNodes(rootNode);
    }

    //中序线索化二叉树,递归调用
    private void threadNodes(ThreadedTreeNode node) {
        //如果当前节点为空,返回上一层
        if (node == null) {
            return;
        }

        //处理左边
        threadNodes(node.leftNode);

        //--------------------核心步骤-----------------
        //处理前驱节点
        if (node.leftNode == null) {
            node.leftNode = pre;
            node.leftType = 1;
        }
        //处理前驱节点的右节点
        if (pre != null && pre.rightNode == null) {
            pre.rightNode = node;
            pre.rightType = 1;
        }
        //每处理一次,当前节点是下一个节点的前驱节点
        pre = node;
        //--------------------------------------------

        //处理右边
        threadNodes(node.rightNode);
    }

    //中序线索化二叉树遍历
    public void threadIterate() {

        //用于临时储存当前遍历的节点
        ThreadedTreeNode node = rootNode;

        //循环遍历
        while (node != null) {

            //找到最开始的节点
            while (node.leftType == 0) {
                node = node.leftNode;
            }
            //打印当前节点
            System.out.println(node.value);

            //如果当前节点的右节点是后继节点,可能后继节点还有后继节点
            while (node.rightType == 1) {
                node = node.rightNode;
                System.out.println(node.value);
            }
            //替换遍历的节点
            node = node.rightNode;
        }
    }


}
