package com.pianocello.binaryTree.avlTree;

/**
 * 平衡二叉树
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
        //左左情况,需要进行右旋转
        if (leftHeight() - rightHeight() >= 2) {
            //双旋转
            if (left != null && left.leftHeight() < left.rightHeight()) {
                //先左旋转后右旋转
                left.leftRotate();
                rightRotate();
            } else {
                //只需要右旋转
                rightRotate();
            }
        }
        //右右情况,需要进行左旋转
        if (rightHeight() - leftHeight() >= 2) {
            //双旋转
            if (right != null && right.rightHeight() < right.leftHeight()) {
                //先右旋转后左旋转
                right.rightRotate();
                leftRotate();
            } else {
                //只需要左旋转
                leftRotate();
            }
        }
    }

    /**
     * 左旋转(和右旋转一致)
     */
    private void leftRotate() {
        Node newLeft = new Node(value);
        newLeft.left = left;
        newLeft.right = right.left;
        this.value = right.value;
        this.right = right.right;
        this.left = newLeft;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {

        //创建一个新节点,值等于当前节点的值
        Node newRight = new Node(value);
        //把新节点的右子树设置为当前节点的右子树
        newRight.right = right;
        //把新节点的左子树设置为当前节点的左子树的右子树
        newRight.left = left.right;
        //把当前节点的值替换成左子节点的值
        this.value = left.value;
        //把当前节点的左子树设置为左子树的左子树
        this.left = left.left;
        //把当前节点的右子树设置为新节点
        this.right = newRight;

    }

    /**
     * 获取当前节点的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 获取左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    /**
     * 获取右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
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
