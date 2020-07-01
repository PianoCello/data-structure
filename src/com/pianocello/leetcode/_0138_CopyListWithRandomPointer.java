package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的 深拷贝。 
 * <p>
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *
 * @author PianoCello
 * @date 2020-06-30
 */
public class _0138_CopyListWithRandomPointer {

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 解法一：使用哈希表保存 原节点 和 克隆节点 的关系
     * <p>
     * 时间复杂度：O(N) 。需要将原链表逐一遍历。
     * 空间复杂度：O(N) 。需要维护一个字典，保存旧的节点和新的节点的对应，因此总共需要 N 个节点
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node sentinel = head;
        Map<Node, Node> map = new HashMap<>();

        while (head != null) {
            //处理当前节点
            if (!map.containsKey(head)) {
                map.put(head, new Node(head.val));
            }
            //处理当前节点的下一个节点
            if (head.next != null && !map.containsKey(head.next)) {
                map.put(head.next, new Node(head.next.val));
            }
            //处理当前节点的随机节点
            if (head.random != null && !map.containsKey(head.random)) {
                map.put(head.random, new Node(head.random.val));
            }

            map.get(head).next = map.get(head.next);
            map.get(head).random = map.get(head.random);

            head = head.next;
        }
        return map.get(sentinel);
    }

}
