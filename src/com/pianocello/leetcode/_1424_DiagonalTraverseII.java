package com.pianocello.leetcode;

import java.util.*;

/**
 * 给你一个列表 nums ，里面每一个元素都是一个整数列表。
 * 依次自左下到右上遍历
 * <p>
 * 示例 1：
 * 输入：nums = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,4,2,7,5,3,8,6,9]
 * <p>
 * <p>
 * 示例 3：
 * 输入：nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * 输出：[1,4,2,5,3,8,6,9,7,10,11]
 *
 * @author PianoCello
 * @date 2020-07-02
 */
public class _1424_DiagonalTraverseII {

    /**
     * 根据矩形的特点，设行的标号为i，列的标号为j。则对于每一条对角线而言，i + j的值是唯一的。
     * 可以按照对角线对 nums 中的值进行聚类。聚类完毕后，将所有的数值生成一个数组即可。
     */
    public static int[] findDiagonalOrder(List<List<Integer>> nums) {
        //总元素的个数
        int resLength = 0;
        //使用 LinkedHashMap 的自然排序
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();

        //外层循环次数
        int outer = nums.size();
        for (int i = 0; i < outer; i++) {
            //内层循环次数
            List<Integer> list = nums.get(i);
            int inner = list.size();
            resLength += inner;
            for (int j = 0; j < inner; j++) {
                if (map.containsKey(i + j)) {
                    map.get(i + j).add(list.get(j));
                } else {
                    List<Integer> list2 = new LinkedList<>();
                    list2.add(list.get(j));
                    map.put(i + j, list2);
                }
            }
        }
        int[] res = new int[resLength];

        int index = 0;
        for (Integer integer : map.keySet()) {
            List<Integer> list = map.get(integer);
            int size = list.size();
            for (int i = size - 1; i >= 0; i--) {
                res[index++] = list.get(i);
            }
        }
        return res;
    }

    /**
     *
     */
    public static int[] findDiagonalOrder2(List<List<Integer>> nums) {
        int m = 0;
        //估算有多少条对角线，对角线的个数不会超过行列的总和
        for (int i = 0; i < nums.size(); i++) {
            m = Math.max(m, nums.get(i).size());
        }
        //对角线元素起点对索引
        int[] index = new int[m + nums.size()];
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);
            count += row.size();
            for (int j = 0; j < row.size(); j++) {
                //计数，往后偏移1为方便后面对处理
                index[i + j + 1]++;
            }
        }
        for (int i = 1; i < index.length; i++) {
            index[i] = index[i] + index[i - 1];
        }
        int[] result = new int[count];
        //对角线元素是下面的元素在前面因此从最后一行遍历数组
        for (int i = nums.size() - 1; i >= 0; i--) {
            List<Integer> row = nums.get(i);
            for (int j = 0; j < row.size(); j++) {
                result[index[i + j]] = row.get(j);
                index[i + j]++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);


        list2.add(5);
        list2.add(6);
        list2.add(7);

        list3.add(8);
        list3.add(9);

        list4.add(11);
        list4.add(23);
        list4.add(44);
        list4.add(555);

        nums.add(list);
        nums.add(list2);
        nums.add(list3);
        nums.add(list4);

        int[] order = findDiagonalOrder2(nums);
        System.out.println(Arrays.toString(order));
    }
}
