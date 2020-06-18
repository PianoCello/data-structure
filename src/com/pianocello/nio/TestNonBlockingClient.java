package com.pianocello.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author PianoCello
 * @date 2019-09-21
 */
public class TestNonBlockingClient {

    /**
     * 通道:     java.nio.channels.Channel接口：
     * SelectableChannel 抽象类
     * |-- SocketChannel
     * |-- ServerSocketChannel
     * |-- DatagramChannel
     * 缓冲区:   负责数据的存取
     * 选择器:   是SelectableChannel的多路复用.用于SelectableChannel的IO状况
     *
     * @param args
     */
    public static void main(String[] args) {

        try (
                //1. 获取通道
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080))
        ) {
            //2. 切换为非阻塞模式
            socketChannel.configureBlocking(false);

            //3. 准备数据,并且发送到服务端
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String next = scanner.next();

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put((LocalDateTime.now() + "\n" + next).getBytes());
                buffer.flip();

                socketChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
