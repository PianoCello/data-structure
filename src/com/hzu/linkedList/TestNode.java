package com.hzu.linkedList;

/**
 * @author PianoCello
 * @date 2019-08-11
 */
public class TestNode {


    public static void main(String[] args) {

        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node.append(node2).append(node3).append(node4).append(node5);


        System.out.println(node);



    }
}
