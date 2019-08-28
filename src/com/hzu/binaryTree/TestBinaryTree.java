package com.hzu.binaryTree;

/** 测试二叉树
 * @author zhanghuihong
 * @since 2019-08-14
 */
public class TestBinaryTree {

    public static void main(String[] args) {

        //创建一棵空树
        BinaryTree binaryTree = new BinaryTree();

        //创建一个根节点
        TreeNode rootNode = new TreeNode(1);
        //将根节点设置到树中
        binaryTree.setRootNode(rootNode);

        //创建根左右节点
        TreeNode leftNode = new TreeNode(2);
        TreeNode rightNode = new TreeNode(3);

        //将左右节点设置到根几点下面
        rootNode.setLeftNode(leftNode);
        rootNode.setRightNode(rightNode);

        //创建根左节点的左右节点
        leftNode.setLeftNode(new TreeNode(4));
        leftNode.setRightNode(new TreeNode(5));

        //创建根右节点的左右节点
        rightNode.setLeftNode(new TreeNode(6));
        rightNode.setRightNode(new TreeNode(7));

        //二叉树的前序遍历
        binaryTree.frontShow();
        System.out.println("==============================");

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
        binaryTree.remove(1);

        binaryTree.frontShow();


    }
}
