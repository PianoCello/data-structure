package com.pianocello.leetcode;

/**
 * 不使用任何内建的哈希表库设计一个哈希集合
 * 具体地说，你的设计应该包含以下的功能
 * <p>
 * add(value)：向哈希集合中插入一个值。
 * contains(value) ：返回哈希集合中是否存在这个值。
 * remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * @author PianoCello
 * @date 2020-07-08
 */
class MyHashSet {

    private class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    //容量
    private int capacity = 1 << 4;

    //负载因子
    private float loadFactor = 0.75f;

    // rehash 阈值
    private int threshold = (int) (loadFactor * capacity);

    //存储数量
    private int size = 0;

    //实际存储数据的数组
    private Node[] arr;


    private int hash(Node node) {
        return node.val % capacity;
    }

    public MyHashSet() {
        arr = new Node[capacity];
    }

    /**
     * 将原有数据复制到新的数组
     */
    private void rehash() {
        capacity = capacity << 1;
        threshold = (int) (loadFactor * capacity);
        Node[] newArr = new Node[capacity];

        for (int i = 0; i < arr.length; i++) {
            Node node = arr[i];
            while (node != null) {
                //备份节点
                Node temp = node.next;
                //清空后继节点
                node.next = null;

                int hash = hash(node);
                if (newArr[hash] == null) {
                    newArr[hash] = node;
                } else {
                    node.next = newArr[hash];
                    newArr[hash] = node;
                }

                node = temp;
            }
        }

        //更新数组
        arr = newArr;
    }

    public void add(int key) {
        if (contains(key)) {
            return;
        }
        //启动 rehash
        if (size >= threshold) {
            rehash();
        }
        Node node = new Node(key);
        //使用哈希函数求键的哈希值
        int hash = hash(node);
        //这个桶还没有元素就直接加入
        if (arr[hash] == null) {
            arr[hash] = node;
            size++;
            return;
        }
        //在链表的头部插入
        node.next = arr[hash];
        arr[hash] = node;
        size++;
    }

    public void remove(int key) {
        Node node = new Node(key);
        //使用哈希函数求键的哈希值
        int hash = hash(node);
        //要删除的值不存在
        if (arr[hash] == null) {
            return;
        }
        Node temp = arr[hash];
        //如果要删除的是第一个节点
        if (temp.val == node.val) {
            arr[hash] = temp.next;
            size--;
            return;
        }
        //遍历链表删除节点
        while (temp.next != null) {
            if (temp.next.val == node.val) {
                temp.next = temp.next.next;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    public boolean contains(int key) {
        Node node = new Node(key);
        //使用哈希函数求键的哈希值
        int hash = hash(node);
        if (arr[hash] == null) {
            return false;
        }
        Node temp = arr[hash];
        while (temp != null) {
            if (temp.val == node.val) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public static void main(String[] args) {

        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(5);
        hashSet.add(4156);
        boolean contains = hashSet.contains(1);// 返回 true
        boolean contains1 = hashSet.contains(3);// 返回 false (未找到)
        hashSet.add(2);
        boolean contains2 = hashSet.contains(2);// 返回 true
        hashSet.remove(2);
        boolean contains3 = hashSet.contains(2);// 返回  false (已经被删除)

    }
}