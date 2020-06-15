package com.hzu.linkedList;

/**
 * @author PianoCello
 * @date 2019-08-11
 */
public class TestLoopNode {
    public static void main(String[] args) {


        LoopNode loopNode = new LoopNode(1);
        LoopNode loopNode2 = new LoopNode(2);
        LoopNode loopNode3 = new LoopNode(3);
        LoopNode loopNode4 = new LoopNode(4);

        loopNode.after(loopNode2);
        loopNode2.after(loopNode3);
        loopNode3.after(loopNode4);

        loopNode4.after(loopNode);


        System.out.println(loopNode.next().getData());
        System.out.println(loopNode2.next().getData());
        System.out.println(loopNode3.next().getData());
        System.out.println(loopNode4.next().getData());


    }
}
