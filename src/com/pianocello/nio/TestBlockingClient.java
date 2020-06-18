package com.pianocello.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author PianoCello
 * @date 2019-09-21
 */
public class TestBlockingClient {

    /**
     * 阻塞式NIO  客户端Client
     *
     * @param args
     */
    public static void main(String[] args) {

        try (
                //创建 socket 通道
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
                //读取的文件通道
                FileChannel fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        ) {
            //创建缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //将数据读到缓冲区
            while (fileChannel.read(buf) != -1) {
                //转换成写模式
                buf.flip();
                //使用socketChannel将数据发送到服务器
                socketChannel.write(buf);
                buf.clear();
            }

            //发送完毕
            socketChannel.shutdownOutput();

            //接收服务端的反馈
            int len;
            while ((len = socketChannel.read(buf)) != -1) {
                buf.flip();
                System.out.println(new String(buf.array(), 0, len));
                buf.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
