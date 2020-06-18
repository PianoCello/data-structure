package com.pianocello.binaryTree.threadedBinaryTree;

/** 测试二叉树
 * @author PianoCello
 * @date 2019-08-14
 */
public class TestThreadedBinaryTree {

    public static void main(String[] args) {

        //创建一棵空树
        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();

        //创建一个根节点
        ThreadedTreeNode rootNode = new ThreadedTreeNode(1);
        //将根节点设置到树中
        binaryTree.setRootNode(rootNode);

        //创建根左右节点
        ThreadedTreeNode leftNode = new ThreadedTreeNode(2);
        ThreadedTreeNode rightNode = new ThreadedTreeNode(3);

        //将左右节点设置到根几点下面
        rootNode.setLeftNode(leftNode);
        rootNode.setRightNode(rightNode);

        //创建根左节点的左右节点
        ThreadedTreeNode node4 = new ThreadedTreeNode(4);
        leftNode.setLeftNode(node4);

        ThreadedTreeNode node5 = new ThreadedTreeNode(5);
        leftNode.setRightNode(node5);

        //创建根右节点的左右节点
        rightNode.setLeftNode(new ThreadedTreeNode(6));
        rightNode.setRightNode(new ThreadedTreeNode(7));

        //二叉树的前序遍历
//        binaryTree.frontShow();
//        System.out.println("==============================");

        //二叉树的中序遍历
//        binaryTree.middleShow();
//        System.out.println("==============================");

        //二叉树的后序遍历
//        binaryTree.backShow();
//        System.out.println("==============================");

        //前序查找
//        TreeNode result =  binaryTree.frontSearch(9);
//        System.out.println(result);

        //删除节点
//        binaryTree.remove(1);

        //中序遍历
        binaryTree.middleShow();
        System.out.println("==============================");

        //中序线索化
        binaryTree.threadNodes();
        //中序线索化遍历
        binaryTree.threadIterate();



    }
}
