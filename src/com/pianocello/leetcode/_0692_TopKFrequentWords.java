package com.pianocello.leetcode;

import java.util.*;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 示例 1：
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 *
 * @author PianoCello
 * @date 2020-07-16
 */
public class _0692_TopKFrequentWords {

    /**
     * 解法一：使用 HashMap 记录元素和出现次数 再用优先级队列过滤元素
     * 时间复杂度：O(Nlog(k))。Counter 方法的复杂度是 O(N)，建堆和输出的复杂度是 O(Nlog(k))。
     * 空间复杂度：O(N)，存储哈希表的开销。
     */
    public static List<String> topKFrequent(String[] words, int k) {
        // key 为元素   val 为频次
        Map<String, Integer> map = new HashMap<>();
        for (String num : words) {
            map.merge(num, 1, (a, b) -> a + b);
        }
        //使用优先级队列 并且及时删除优先级最高的
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int temp = map.get(s1) - map.get(s2);
                if (temp == 0) {
                    return s2.compareTo(s1);
                } else {
                    return temp;
                }
            }
        };
        // 维持topK频率的单词
        PriorityQueue<String> priority = new PriorityQueue<>(comparator);
        for (String key: map.keySet()) {
            priority.add(key);
            if (priority.size() > k)
                priority.poll();
        }
        //利用栈的特性
        LinkedList<String> list = new LinkedList<>();
        while (!priority.isEmpty()) {
            list.push(priority.poll());
        }

        return list;
    }

    public static void main(String[] args) {

//        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
//        String[] words = {"a","aa","aaa"};
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};

        List<String> list = topKFrequent(words, 4);
        System.out.println(list);

    }

}
