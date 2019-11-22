package com.hzu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author zhanghuihong
 * @since 2019-09-21
 */
public class TestBlockingServer {

    /**
     * 阻塞式NIO 服务器端Server
     * @param args
     */
    public static void main(String[] args) {

        try (
                //创建 serverSocket 通道
                ServerSocketChannel ssChannel = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
                //接收通道
                SocketChannel socketChannel = ssChannel.accept();
                //写文件通道
                FileChannel fileChannel = FileChannel.open(Paths.get("2.jpg"),
                        StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        ) {
            //创建缓冲区,并且接收数据
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (socketChannel.read(buf) != -1) {
                buf.flip();
                fileChannel.write(buf);
                buf.clear();
            }

            //给客服端发送反馈
            buf.put("Server收到...".getBytes());
            buf.flip();
            socketChannel.write(buf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
