package com.hzu.binaryTree.huffmanTree;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼编码压缩与解码
 *
 * @author PianoCello
 * @date 2019-08-25
 */
public class HuffmanZip {

    public static void main(String[] args) throws Exception {

/*
        //待编码信息
        String msg = "can you can a can as a can canner can a can.";
        byte[] bytes = msg.getBytes();
        System.out.println(Arrays.toString(bytes));
        System.out.println("压缩前:" + bytes.length);

        //调用哈夫曼压缩
        byte[] zipBytes = huffmanZip(bytes);
        System.out.println(Arrays.toString(zipBytes));

        System.out.println("压缩后:" + zipBytes.length);

        byte[] unZip = huffmanUnZip(huffCodes, zipBytes);
        System.out.println(Arrays.toString(unZip));

        System.out.println("解压后:" + new String(unZip));
*/

        String src = "C:/Users/zhh/Desktop/001.jpg";
        String dst = "C:/Users/zhh/Desktop/002.zip";
        zipFile(src, dst);

        String src2 = dst;
        String dst2 = "C:/Users/zhh/Desktop/003.jpg";
        unzipFile(src2, dst2);

    }


    /**
     * 使用哈夫曼编码压缩文件
     *
     * @param src 文件原路径
     * @param dst 压缩后存放路径
     * @throws IOException
     */
    public static void zipFile(String src, String dst) throws IOException {

        //创建一个输入流
        InputStream is = new FileInputStream(src);
        //创建一个和输入流指向的文件大小的byte数组
        byte[] inputBytes = new byte[is.available()];
        //将文件内容读到字节数组中
        int read = is.read(inputBytes);
        System.out.println("共" + read + "个字节.");
        is.close();//关闭流

        //进行哈夫曼压缩,返回压缩后的字节数组
        byte[] zipBytes = huffmanZip(inputBytes);
        System.out.println("压缩后共" + zipBytes.length + "个字节.");

        //创建一个输出流
        OutputStream os = new FileOutputStream(dst);
        //创建对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(os);
        //将压缩后的字节数组写出
        oos.writeObject(zipBytes);
        //同时写出哈夫曼编码表
        oos.writeObject(huffCodes);

        //关闭流
        oos.close();
        os.close();
    }

    /**
     * 使用哈夫曼编码解压文件
     *
     * @param src 被压缩的文件路径
     * @param dst 解压后的存放路径
     * @throws IOException
     */
    public static void unzipFile(String src, String dst) throws Exception {

        //创建输入流
        InputStream is = new FileInputStream(src);
        //对象输入流
        ObjectInputStream ois = new ObjectInputStream(is);

        //读取文件为字节码
        byte[] bytes = (byte[]) ois.readObject();
        //读取哈夫曼编码
        Map<Byte, String> huffCodes = (Map<Byte, String>) ois.readObject();
        //关闭流
        ois.close();
        is.close();

        //调用哈夫曼解压文件成字节数组
        byte[] unZip = huffmanUnZip(huffCodes, bytes);

        //创建输出流
        OutputStream os = new FileOutputStream(dst);
        //将解压后的字节数组写出
        os.write(unZip);
        //关闭流
        os.close();
    }

    /**
     * 调用哈夫曼编码表解压
     *
     * @param huffCodes 哈夫曼编码表
     * @param zipBytes  待压缩字节数组
     * @return
     */
    public static byte[] huffmanUnZip(Map<Byte, String> huffCodes, byte[] zipBytes) {
        //还原二进制字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zipBytes.length; i++) {
            /*
             * 这里有个bug,在编码时存哈夫曼编码的时候如果最后的字符串是011
             * 装进byte数组就是3了,取出来就是11,这样就丢失了一个0...
             * */
            //如果是最后一个数,并且是正数的话需要特殊处理
            if (i == zipBytes.length - 1 && zipBytes[i] > 0) {
                sb.append(Integer.toBinaryString(zipBytes[i]));
            } else {
                sb.append(byteToBitString(zipBytes[i]));
            }
        }
//        System.out.println(sb);

        //将哈夫曼编码的键值对调换
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> code : huffCodes.entrySet()) {
            map.put(code.getValue(), code.getKey());
        }
        //储存临时byte数组
        List<Byte> list = new ArrayList<>();
        //处理字符串
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = sb.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            //将取出的字节数存入集合中
            list.add(b);
            //下一次遍历的位置
            i += count;
        }

        byte[] unZip = new byte[list.size()];
        //将集合的数据转存到byte数组中
        for (int j = 0; j < list.size(); j++) {
            unZip[j] = list.get(j);
        }

        return unZip;
    }

    /**
     * 将byte类型的数字转换成8位二进制的字符串
     *
     * @return
     */
    private static String byteToBitString(byte b) {
        int temp = b;
        temp |= 256;
        String binStr = Integer.toBinaryString(temp);
        return binStr.substring(binStr.length() - 8);
    }


    /**
     * 进行哈夫曼编码压缩的方法
     *
     * @param bytes
     */
    private static byte[] huffmanZip(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        //先统计每一个byte出现的次数,并放入一个集合中
        List<Node> nodes = getNodes(bytes);

        //创建哈夫曼树
        Node tree = createHuffmanTree(nodes);

        //创建哈夫曼编码表
        Map<Byte, String> huffmanCode = getHuffmanCode(tree);

        //编码成字节数组
        byte[] zip = huffZip(bytes, huffmanCode);

        return zip;
    }

    //进行哈夫曼编码
    private static byte[] huffZip(byte[] bytes, Map<Byte, String> huffmanCode) {
        StringBuilder binStr = new StringBuilder();
        //将要压缩的byte数组处理成二进制的字符串
        for (byte b : bytes) {
            binStr.append(huffmanCode.get(b));
        }
//        System.out.println(binStr);

        //压缩后字节数组的长度
        int len;
        if (binStr.length() % 8 == 0) {
            len = binStr.length() / 8;
        } else {
            len = binStr.length() / 8 + 1;
        }
        //压缩后的字节数组
        byte[] zip = new byte[len];

        String strByte;
        int index = 0;
        //遍历二进制的字符串,每8位截取,最后不满8位时直接取
        for (int i = 0; i < binStr.length(); i += 8) {
            if (i + 8 < binStr.length()) {
                strByte = binStr.substring(i, i + 8);
            } else {
                /*
                 * 这里有个bug,在编码时存哈夫曼编码的时候如果最后的字符串是011
                 * 装进byte数组就是3了,取出来就是11,这样就丢失了一个0...
                 * */
                strByte = binStr.substring(i);
//                System.out.println("最后一个字符串是: "+strByte);
            }
            //将截取的8位二进制字符串装换成byte类型数字
            zip[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return zip;
    }

    //用于储存哈夫曼编码
    private static Map<Byte, String> huffCodes = new HashMap<>();

    //创建哈夫曼编码表
    private static Map<Byte, String> getHuffmanCode(Node tree) {
        if (tree == null) {
            return null;
        }
        //用于临时储存遍历路径的变量
        StringBuilder route = new StringBuilder();
        //遍历左边
        getHuffmanCode(tree.left, "0", route);
        //遍历右边
        getHuffmanCode(tree.right, "1", route);

        return huffCodes;
    }

    //用于递归遍历
    private static void getHuffmanCode(Node node, String str, StringBuilder route) {
        if (node == null) {
            return;
        }
        //路径增加
        StringBuilder newRoute = new StringBuilder(route);
        newRoute.append(str);
        if (node.data == null) {
            getHuffmanCode(node.left, "0", newRoute);
            getHuffmanCode(node.right, "1", newRoute);
        } else {
            huffCodes.put(node.data, newRoute.toString());
        }
    }

    //创建哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {

        if (nodes == null) {
            return null;
        }

        while (nodes.size() > 1) {
            //倒序排序
            Collections.sort(nodes);

            //取出权值最小的节点
            Node left = nodes.get(nodes.size() - 1);
            //取出权值次小的节点
            Node right = nodes.get(nodes.size() - 2);
            //创建一棵新的二叉树
            Node parent = new Node(left.weight + right.weight, left, right);

            nodes.add(parent);
            //将取出来的两棵树移除
            nodes.remove(left);
            nodes.remove(right);
        }

        return nodes.get(0);
    }

    //统计每一个byte出现的次数,并放入一个集合中
    private static List<Node> getNodes(byte[] bytes) {

        if (bytes == null) {
            return null;
        }

        Map<Byte, Integer> map = new HashMap<>();
        //统计每一个byte出现的次数,装到map中
        for (byte key : bytes) {
            //key对应的值为空
            map.merge(key, 1, (a, b) -> a + b);
        }

        //放入一个集合中
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> set : map.entrySet()) {
            Node node = new Node(set.getValue(), set.getKey());
            nodes.add(node);
        }

        return nodes;
    }

}
