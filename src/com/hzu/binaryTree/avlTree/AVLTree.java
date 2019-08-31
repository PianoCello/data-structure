package com.hzu.binaryTree.avlTree;

/**
 * 平衡二叉树
 *
 * 1.本身首先是一棵二叉搜索树。
 * 2.带有平衡条件：每个结点的左右子树的高度之差的绝对值（平衡因子）最多为1。
 *   也就是说，AVL树，本质上是带了平衡功能的二叉查找树（二叉排序树，二叉搜索树）。
 *
 * @author zhanghuihong
 * @since 2019-08-29
 */
public class AVLTree {

    //树的根节点
    Node root;


    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {

        //如果根节点为空
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void middleShow() {
        //根节点为空
        if (root == null) {
            System.out.println("null");
            return;
        }
        root.middleShow(root);
    }

    /**
     * 查找
     *
     * @param i
     * @return
     */
    public Node search(int i) {
        if (root == null) {
            return null;
        }
        return root.search(i);
    }

    /**
     * 删除节点
     *
     * @param i
     */
    public void delete(int i) {
        if (root != null) {
            //查找要删除的节点是否存在
            Node target = root.search(i);
            //不存在节点
            if (target == null) {
                return;
            }
            //查找节点的父节点
            Node parent = searchParent(i);

            //******要删除的节点为叶节点*******
            if (target.left == null && target.right == null) {
                //父节点为空,说明是根节点
                if (parent == null) {
                    root = null;
                    return;
                }
                //删除的是左节点
                if (parent.left == target) {
                    parent.left = null;
                }
                //删除的是右节点
                if (parent.right == target) {
                    parent.right = null;
                }
                //*****要删除的节点有一个子节点( ^ 为异或)*******
            } else if (target.left != null ^ target.right != null) {
                //有左子节点
                if (target.left != null) {
                    //要删除的节点是父节点的左子节点
                    if (parent.left.value == target.value) {
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                } else {
                    //有右子节点
                    //要删除的节点是父节点的右子节点
                    if (parent.right.value == target.value) {
                        parent.right = target.right;
                    } else {
                        parent.right = target.right;
                    }
                }
                //*****要删除的节点有两个节点********
            } else {
                //找到当前节点的后继节点,将之删除,返回其值(用于替换当前值)
                Node next = removeNext(target.right);
                //替换当前值
                target.value = next.value;
            }
        }
    }

    /**
     * 删除后继节点,返回其值
     *
     * @param right
     * @return
     */
    private Node removeNext(Node right) {
        Node node = right;
        //后继节点一定在左边(循环向左找)
        while (node.left != null) {
            node = node.left;
        }
        //删除后继节点并返回(调用已经实现的方法)
        delete(node.value);
        return node;
    }

    /**
     * 查找节点的父节点
     *
     * @param i
     * @return
     */
    public Node searchParent(int i) {

        if (root != null) {
            return root.searchParent(i);
        }
        return null;
    }
}
