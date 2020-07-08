package com.pianocello.leetcode;

/**
 * 不使用任何内建的哈希表库设计一个哈希映射
 * 具体地说，你的设计应该包含以下的功能
 * <p>
 * put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
 * get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
 * remove(key)：如果映射中存在这个键，删除这个数值对。
 *
 * @author PianoCello
 * @date 2020-07-08
 */
class MyHashMap {

    private class Entry {
        int key;
        int value;
        Entry next;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Entry(int key) {
            this.key = key;
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
    private Entry[] arr;


    private int hash(Entry entry) {
        return entry.key % capacity;
    }

    public MyHashMap() {
        arr = new Entry[capacity];
    }

    /**
     * 将原有数据复制到新的数组
     */
    private void rehash() {
        capacity = capacity << 1;
        threshold = (int) (loadFactor * capacity);
        Entry[] newArr = new Entry[capacity];

        for (int i = 0; i < arr.length; i++) {
            Entry entry = arr[i];
            while (entry != null) {
                //备份节点
                Entry temp = entry.next;
                //清空后继节点
                entry.next = null;

                int hash = hash(entry);
                if (newArr[hash] == null) {
                    newArr[hash] = entry;
                } else {
                    entry.next = newArr[hash];
                    newArr[hash] = entry;
                }

                entry = temp;
            }
        }

        //更新数组
        arr = newArr;
    }

    public void put(int key, int value) {
        //启动 rehash
        if (size >= threshold) {
            rehash();
        }
        Entry entry = new Entry(key, value);
        //使用哈希函数求键的哈希值
        int hash = hash(entry);

        int oldValue = -1;
        Entry temp = arr[hash];
        //如果存在 key 的话，就更新数据
        while (temp != null) {
            if (temp.key == entry.key) {
                oldValue = temp.value;
                temp.value = value;
                break;
            }
            temp = temp.next;
        }

        //不存在 key，执行插入
        if (oldValue == -1) {
            //在链表的头部插入
            entry.next = arr[hash];
            arr[hash] = entry;
            size++;
        }
    }

    public int get(int key) {
        Entry entry = new Entry(key);
        //使用哈希函数求键的哈希值
        int hash = hash(entry);

        Entry temp = arr[hash];
        while (temp != null) {
            if (temp.key == entry.key) {
                return temp.value;
            }
            temp = temp.next;
        }
        return -1;
    }

    public void remove(int key) {
        Entry entry = new Entry(key);
        //使用哈希函数求键的哈希值
        int hash = hash(entry);
        //要删除的键值对不存在
        if (arr[hash] == null) {
            return;
        }
        Entry temp = arr[hash];
        //如果要删除的是第一个节点
        if (temp.key == entry.key) {
            arr[hash] = temp.next;
            size--;
            return;
        }
        //遍历链表删除节点
        while (temp.next != null) {
            if (temp.next.key == entry.key) {
                temp.next = temp.next.next;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    public boolean containsKey(int key) {
        Entry entry = new Entry(key);
        //使用哈希函数求键的哈希值
        int hash = hash(entry);
        if (arr[hash] == null) {
            return false;
        }
        Entry temp = arr[hash];
        while (temp != null) {
            if (temp.key == entry.key) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public static void main(String[] args) {

        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        int i = hashMap.get(1);// 返回 1
        int i1 = hashMap.get(3);// 返回 -1 (未找到)
        hashMap.put(2, 1); // 更新已有的值
        int i2 = hashMap.get(2);// 返回 1
        hashMap.remove(2);// 删除键为2的数据
        int i3 = hashMap.get(2);// 返回 -1 (未找到)

    }

}