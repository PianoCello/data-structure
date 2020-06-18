package com.pianocello.binaryTree;

/**
 * 数据结构之二叉树(链式存储结构实现)
 *
 * @author PianoCello
 * @date 2019-08-14
 */
public class BinaryTree {

    //树的根节点
    private TreeNode rootNode;

    //设置根节点
    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    //前序遍历
    public void frontShow() {
        if (rootNode!=null) {
            rootNode.frontShow();
        }
    }

    //中序遍历
    public void middleShow() {
        if (rootNode!=null) {
            rootNode.middleShow();
        }
    }

    //后序遍历
    public void backShow() {
        if (rootNode!=null) {
            rootNode.backShow();
        }
    }

    //前序查找
    public TreeNode frontSearch(int i) {
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
}
