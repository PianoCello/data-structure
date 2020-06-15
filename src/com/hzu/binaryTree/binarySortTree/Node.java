package com.hzu.binaryTree.binarySortTree;

/**
 * 二叉排序树的节点,中序遍历后就是从小到大排好序的节点,(也是二叉搜索树)
 *
 * @author PianoCello
 * @date 2019-08-29
 */
public class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        //如果要添加的节点的值小于当前节点的值
        if (node.value < this.value) {
            //如果左节点为空
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
            //如果要添加的节点的值不小于当前节点的值
        } else {
            //如果右节点为空
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历(左节点--根节点--右节点)
     */
    public void middleShow(Node node) {
        if (node == null) {
            return;
        }
        middleShow(node.left);
        System.out.println(node.value);
        middleShow(node.right);
    }

    /**
     * 查找
     *
     * @param i
     * @return
     */
    public Node search(int i) {
        if (value == i) {
            return this;
        }
        if (left != null && i < value) {
            return left.search(i);
        } else if (right != null && i > value) {
            return right.search(i);
        }
        return null;
    }

    /**
     * 查找节点的父节点
     *
     * @param i
     * @return
     */
    public Node searchParent(int i) {

        //i等于左节点或者右节点
        if ((left != null && left.value == i) || (right != null && right.value == i)) {
            return this;
        } else {
            //如果i小于value
            if (i < value && left != null) {
                return left.searchParent(i);
            }
            //如果i大于value
            if (i > value && right != null) {
                return right.searchParent(i);
            }
        }
        return null;
    }
}
