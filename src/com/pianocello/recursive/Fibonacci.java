package com.pianocello.recursive;

/**
 * 斐波那契数列 Fibonacci Sequence
 *
 * @author PianoCello
 * @date 2019-08-11
 */
public class Fibonacci {

    public static void main(String[] args) {

        //斐波那契数列 1 1 2 3 5 8 13 21 34 .....
        System.out.println(fibonacci(20));
//        System.out.println(fibonacci2(20));
    }

    //获取第n项(当n较大时,性能极差)
    public static int fibonacci(int i) {
        //第一项和第二项都是1
        if (i == 1 || i == 2) {
            return 1;
        } else {
            return fibonacci(i - 1) + fibonacci(i - 2);
        }
    }

    private static class Node {
        int n;
        int tag;
    }

/*    public static int fibonacci2(int n) {
        Stack<Node> stack = new Stack<>();
        Node node = new Node();
        int sum = 0;
        do {
            while (n > 1) {
                node.n = n;
                node.tag = 1;
                stack.push(node);
                n--;
            }
            sum = sum + n;
            while (!stack.isEmpty()) {
                Node node2 = stack.pop();
                if (node2.tag == 1) {
                    node2.tag = 2;
                    stack.push(node2);
                    n = node2.n - 2;
                    break;
                }
            }
        } while (!stack.isEmpty());

        return sum;
    }*/


}
