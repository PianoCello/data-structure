package com.pianocello.leetcode;

import java.util.*;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 *
 * @author PianoCello
 * @date 2020-06-21
 */
public class _0133_CloneGraph {

    //记录已经遍历过的节点
    private Map<Node, Node> visited = new HashMap<>();

    /**
     * DFS 实现
     * 时间复杂度：O(N)，每个节点只处理一次。
     * 空间复杂度：O(N)，存储克隆节点和原节点的 HashMap 需要 O(N) 的空间
     * 递归调用栈需要 O(H) 的空间，其中 H 是图的深度。总体空间复杂度为 O(N)。
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        //如果已访问过，直接返回 Value
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        //根据原有节点创建新的节点，并加入 visited 中
        Node clone = new Node(node.val);
        visited.put(node, clone);
        //遍历邻居节点并克隆
        for (Node nei : node.neighbors) {
            clone.neighbors.add(cloneGraph(nei));
        }
        return clone;
    }

    /**
     * BFS 实现
     * 时间复杂度：O(N)，每个节点只处理一次。
     * 空间复杂度：O(N)。visited 使用 O(N) 的空间。BFS 中的队列使用 O(W) 的空间
     * 其中 W 是图的宽度。总体空间复杂度为 O(N)。
     */
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }
        Node brandNew = new Node(node.val);
        //保存访问过的节点
        Map<Node, Node> visit = new HashMap<>();
        visit.put(node, brandNew);
        //将第一个节点放入队列中
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node old = queue.poll();

            for (Node nei : old.neighbors) {
                if (!visit.containsKey(nei)) {
                    //将未遍历的节点加入队列和
                    queue.offer(nei);
                    visit.put(nei, new Node(nei.val));
                }
                //为克隆的节点的 neighbors 赋值
                visit.get(old).neighbors.add(visit.get(nei));
            }
        }
        return brandNew;
    }

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {

        _0133_CloneGraph graph = new _0133_CloneGraph();

        Node node = graph.new Node(1);
        Node node2 = graph.new Node(2);

        node.neighbors.add(node2);
        node2.neighbors.add(node);

        Node cloneGraph = graph.cloneGraph(node);


    }
}
