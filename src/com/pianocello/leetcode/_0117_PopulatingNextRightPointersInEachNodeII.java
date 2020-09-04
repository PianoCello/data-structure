package com.pianocello.leetcode;

/**
 * 给定一个普通二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * @author PianoCello
 * @date 2020-07-20
 */
public class _0117_PopulatingNextRightPointersInEachNodeII {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 解法一：递归
     */
    public static Node connect(Node root) {
        if (root == null ) return null;
        // 左节点不为空
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = nextNode(root.next);
            }
        }
        // 右节点不为空
        if (root.right != null) {
            root.right.next = nextNode(root.next);
        }
        //必须要先处理右边再处理左边, 因为左边的结果需要用到右边的
        connect(root.right);
        connect(root.left);
        return root;
    }

    private static Node nextNode(Node next) {
        // 水平遍历 next
        Node cur = next;
        while (cur != null) {
            if (cur.left != null) {
                return cur.left;
            } else if(cur.right != null) {
               return cur.right;
            }
            cur = cur.next;
        }
        return null;
    }


    /**
     * 解法二：层次遍历
     * 在每一层都设置哨兵节点 然后遍历该层 最后跳入下一层
     */
    public static Node connect2(Node root) {
        Node cur = root;
        while (cur != null) {
            // 哨兵节点
            Node head = new Node();
            Node tail = head;
            //遍历 cur 的当前层
            for (Node p = cur; p != null; p = p.next) {
                if (p.left != null) {
                    tail.next = p.left;
                    tail = tail.next;
                }
                if (p.right != null) {
                    tail.next = p.right;
                    tail = tail.next;
                }
            }
            // 更新 cur 到下一层
            cur = head.next;
        }
        return root;
    }

    public static void main(String[] args) {

        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        root.left = node2;
        root.right = node3;

        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node2.left = node4;
        node2.right = node5;

        node3.right = new Node(7);

        Node connect = connect2(root);
        System.out.println(connect);
    }

}
