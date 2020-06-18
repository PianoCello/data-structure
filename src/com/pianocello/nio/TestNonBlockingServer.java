package com.pianocello.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author PianoCello
 * @date 2019-09-21
 */
public class TestNonBlockingServer {

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
    public static void main(String[] args) throws IOException {
        //1. 获取服务端通道,并且绑定连接
        ServerSocketChannel ssChannel = ServerSocketChannel.open().bind(new InetSocketAddress(8080));

        //2. 切换非阻塞模式
        ssChannel.configureBlocking(false);

        //3. 创建选择器
        Selector selector = Selector.open();

        //4. 将通道注册到选择器中,指定监听"接收事件"
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //5. 轮训获取选择器中已经"准备就绪"的事件
        while (selector.select() > 0) {

            //6. 获取当前选择器中所有注册的"选择键"(已经注册的事件)
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {

                //7. 获取就绪事件
                SelectionKey selectKey = iterator.next();

                //8. 判断具体是什么事件准备就绪了
                if (selectKey.isAcceptable()) {
                    //9. 接收就绪,获取客户端连接通道
                    SocketChannel socketChannel = ssChannel.accept();

                    //10. 切换非阻塞模式
                    socketChannel.configureBlocking(false);

                    //11. 将该通道注册到选择器中
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectKey.isReadable()) {
                    //12. 获取当前选择器上"读就绪"的通道
                    SocketChannel socketChannel = (SocketChannel) selectKey.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int read;
                    //13. 读取数据到缓冲区
                    while ((read = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, read));
                        buffer.clear();
                    }
                }
                //14. 移除选择键
                iterator.remove();
            }
        }
    }

}
