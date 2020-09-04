package com.pianocello.leetcode;

/**
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。
 * val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * 如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。
 * -- 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
 * -- 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 * @author PianoCello
 * @date 2020-06-27
 */
//解法一： 自己写的单链表
class MyLinkedList {

    //链表的结点的个数
    private int size = 0;
    //链表的头结点
    private Node head;

    public MyLinkedList() {

    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node cur = head;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        //不做任何操作
        if (index > size) {
            return;
        }
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            //相当于在头结点之前添加节点
            if (index <= 0) {
                newNode.next = head;
                head = newNode;
            } else {
                //要临时存储要添加的位置的前后节点
                Node cur = head;
                Node pre = cur;
                for (int i = 0; i < index; i++) {
                    pre = cur;
                    cur = cur.next;
                }
                pre.next = newNode;
                newNode.next = cur;
            }
        }
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            head = head.next;
        } else {
            //要临时存储要删除的位置的前后节点
            Node cur = head;
            Node pre = cur;
            for (int i = 0; i < index; i++) {
                pre = cur;
                cur = cur.next;
            }
            pre.next = cur.next;
        }
        size--;
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "size=" + size +
                ", head=" + head +
                '}';
    }

    private class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {

        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(0);  //现在链表是1-> 3
        linkedList.get(1);            //返回3

        System.out.println(linkedList);
    }
}


/**
 * 解法二：单链表 使用哨兵结点充当伪头
 * 时间复杂度：
 * addAtHead： O(1)
 * addAtIndex，get，deleteAtIndex: O(k)，其中 k 指的是元素的索引。
 * addAtTail：O(N)，其中 N 指的是链表的元素个数。
 * 空间复杂度：所有的操作都是 O(1)。
 *
 */
class MyLinkedList2 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private int size;
    // 哨兵结点做伪头
    private ListNode head;

    public MyLinkedList2() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        ListNode curr = head;
        for(int i = 0; i < index + 1; ++i)
            curr = curr.next;

        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;

        if (index < 0) index = 0;

        ++size;
        ListNode pred = head;
        for(int i = 0; i < index; ++i)
            pred = pred.next;

        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        size--;
        ListNode pred = head;
        for(int i = 0; i < index; ++i)
            pred = pred.next;

        pred.next = pred.next.next;
    }
}


/**
 * 解法三：双链表 使用哨兵结点充当伪头和伪尾
 * 时间复杂度：
 * addAtHead，addAtTail： O(1)
 * get，addAtIndex，delete：O(min(k,N−k))，其中 k 指的是元素的索引。
 * 空间复杂度：所有的操作都是 O(1)。
 *
 */
class MyLinkedList3 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        ListNode(int x) { val = x; }
    }

    private int size;
    // 使用哨兵结点充当伪头和伪尾
    private ListNode head, tail;

    public MyLinkedList3() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        // choose the fastest way: to move from the head or to move from the tail
        ListNode curr = head;
        if (index + 1 < size - index)
            for(int i = 0; i < index + 1; ++i)
                curr = curr.next;
        else {
            curr = tail;
            for(int i = 0; i < size - index; ++i)
                curr = curr.prev;
        }

        return curr.val;
    }

    public void addAtHead(int val) {
        ListNode pred = head, succ = head.next;

        ++size;
        ListNode toAdd = new ListNode(val);
        //需要将四个引用重新连上
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    public void addAtTail(int val) {
        ListNode succ = tail, pred = tail.prev;

        ++size;
        ListNode toAdd = new ListNode(val);
        //需要将四个引用重新连上
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;

        if (index < 0) index = 0;

        // find predecessor and successor of the node to be added
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for(int i = 0; i < index; ++i)
                pred = pred.next;
            succ = pred.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index; ++i)
                succ = succ.prev;
            pred = succ.prev;
        }

        // insertion itself
        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        // find predecessor and successor of the node to be deleted
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for(int i = 0; i < index; ++i)
                pred = pred.next;
            succ = pred.next.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index - 1; ++i)
                succ = succ.prev;
            pred = succ.prev.prev;
        }

        --size;
        pred.next = succ;
        succ.prev = pred;
    }
}