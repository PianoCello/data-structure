package com.pianocello.leetcode;

import java.util.*;

/**
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。
 * 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
 * <p>
 * 示例 1:
 * 输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 *
 * @author PianoCello
 * @date 2020-07-12
 */
public class _0599_MinimumIndexSumOfTwoLists {

    /**
     * 解法一：使用 HashMap 记录餐厅和索引
     */
    public static String[] findRestaurant(String[] list1, String[] list2) {
        //保证使用最小的空间
        if (list1.length > list2.length) {
            return findRestaurant(list2, list1);
        }
        //避免重复计算
        int m = list1.length;
        int n = list2.length;

        // 定义 HashMap 时指定初始容量 避免 rehash
        Map<String, Integer> map = new HashMap<>((int)(list1.length/0.75));
        for (int i = 0; i < m; i++) {
            map.put(list1[i], i);
        }

        int min = Integer.MAX_VALUE;
        // 定义时指定列表的大小 避免频繁的扩容
        List<String> list = new ArrayList<>(map.size());
        for (int j = 0; j < n; j++) {
            Integer i;//提前定义变量，减少操作
            if ((i = map.get(list2[j])) != null) {
                if (i + j < min) {
                    list.clear();
                    min = i + j;
                    list.add(list2[j]);
                } else if (i + j == min) {
                    list.add(list2[j]);
                }
            }
        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args) {

        String[] list1 = {"s","a","b","c"};
        String[] list2 = {"c","b","a","s","d","f"};
        String[] restaurant = findRestaurant(list1, list2);

        System.out.println(Arrays.toString(restaurant));
    }

}
