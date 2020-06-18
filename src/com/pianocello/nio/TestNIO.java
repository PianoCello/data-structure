package com.pianocello.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * @author PianoCello
 * @date 2019-09-20
 */
public class TestNIO {

    public static void main(String[] args) throws Exception{
        String as = "asfsefsedfsf谁知道从不是的";
//        testBuffer();
//        testChannel("1.jpg", "2.jpg");
//        testChannelDirect("1.jpg", "3.jpg");
//        testChannelDirect2("1.jpg", "4.jpg");
//        testScatterGather("1.txt", "2.txt");
        testCharset();
    }

    /**
     * 缓冲区的四个属性 mark <= position <= limit <= capacity
     */
    public static void testBuffer() {

        //分配一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("-----------init----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byte[] bytes = "a 中".getBytes();
        byteBuffer.put(bytes);
        System.out.println("-----------put----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //转换成写模式
        byteBuffer.flip();
        System.out.println("-----------flip----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //读取数据
        byte[] in = new byte[byteBuffer.limit()];
        byteBuffer.get(in);
        System.out.println(Arrays.toString(in));
        System.out.println("-----------get----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //重复
        byteBuffer.rewind();
        System.out.println("-----------rewind----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //标记
        byteBuffer.mark();
        System.out.println("-----------mark----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //getOneByte
        byteBuffer.get();
        System.out.println("-----------getOneByte----------");
        System.out.println("remaining: " + byteBuffer.remaining());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //恢复到标记点
        byteBuffer.reset();
        System.out.println("-----------reset----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //清除(数据还在,只是被忘记了)
        byteBuffer.clear();
        System.out.println("-----------clear----------");
        System.out.println("remaining: " + byteBuffer.remaining());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }

    /**
     * java.nio.channels.Channel接口：
     * 用于本地数据传输：
     * ​ |-- FileChannel
     * 用于网络数据传输：
     * ​ |-- SocketChannel
     * ​ |-- ServerSocketChannel
     * ​ |-- DatagramChannel
     * 获取通道
     * Java 针对支持通道的类提供了一个 getChannel() 方法。
     * 本地IO操作
     * 1. FileInputStream/FileOutputStream
     * 2. RandomAccessFile
     * 3. 网络IO
     *    Socket
     *    ServerSocket
     *    DatagramSocket
     * 在JDK1.7中的NIO.2 针对各个通道提供了静态方法 open();
     * 在JDK1.7中的NIO.2 的Files工具类的 newByteChannel();
     */
    //复制文件(使用非直接缓冲区)
    public static void testChannel(String src, String dest) {
        try (
                FileInputStream fis = new FileInputStream(src);
                FileOutputStream fos = new FileOutputStream(dest);
                FileChannel inPutChannel = fis.getChannel();
                FileChannel outputChannel = fos.getChannel();
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //读取数据到缓冲区
            while (inPutChannel.read(byteBuffer) != -1) {
                //转换成写模式
                byteBuffer.flip();
                //将缓冲区中的数据写出去
                outputChannel.write(byteBuffer);
                //清除缓冲区
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用直接缓冲区完成文件复制(内存映射文件)
     */
    public static void testChannelDirect(String src, String dest) {
        try (
                //创建通道
                FileChannel inChannel = FileChannel.open(Paths.get(src), StandardOpenOption.READ);
                FileChannel outChannel = FileChannel.open(Paths.get(dest), StandardOpenOption.READ,
                        StandardOpenOption.WRITE,StandardOpenOption.CREATE)
        ) {
            //内存映射文件
            MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            byte[] bytes = new byte[inBuffer.limit()];
            //读取文件到直接内存
            inBuffer.get(bytes);
            //写到直接内存
            outBuffer.put(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通道之间的数据传输(直接缓冲区)
     */
    public static void testChannelDirect2(String src, String dest) {
        try (
                //创建通道
                FileChannel inChannel = FileChannel.open(Paths.get(src), StandardOpenOption.READ);
                FileChannel outChannel = FileChannel.open(Paths.get(dest), StandardOpenOption.READ,
                        StandardOpenOption.WRITE,StandardOpenOption.CREATE)
        ) {

//            inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分散(Scatter)与聚集(Gather)
     * 分散读取(Scattering Reads)：将通道中的数据分散到多个缓冲区中
     * 聚集写入(Gathering Writes)：将多个缓冲区中的数据聚集到通道中
     */
    public static void testScatterGather(String src, String dest) {
        try (
                //创建通道
                RandomAccessFile raf = new RandomAccessFile(src, "rw");
                FileChannel inChannel = raf.getChannel();

                RandomAccessFile raf2 = new RandomAccessFile(dest, "rw");
                FileChannel outChannel = raf2.getChannel()
        ) {
            //分散读取(将通道中的数据分散读到多个缓冲区中,依次读满)
            ByteBuffer buffer = ByteBuffer.allocate(100);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            ByteBuffer[] arr = {buffer,buffer2};
            inChannel.read(arr);

            for (ByteBuffer buf : arr) {
                buf.flip();
            }
            System.out.println(new String(buffer.array(),0,buffer.limit()));
            System.out.println("------------------------------------------------");
            System.out.println(new String(buffer2.array(),0,buffer2.limit()));

            //聚集写入(将多个缓冲区中的数据聚集写入到通道中)
            outChannel.write(arr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符集
     * 编码: 字符串 -> 字节数组
     * 解码: 字节数组 -> 字符串
     */
    public static void testCharset() throws CharacterCodingException {
        Charset charset = Charset.forName("GBK");

        CharBuffer cBuf = CharBuffer.allocate(100);
        cBuf.put("我是asd爱仕达多");
        cBuf.flip();

        ByteBuffer encode = charset.encode(cBuf);
        CharBuffer decode = charset.decode(encode);
        System.out.println(decode.toString());

    }

}
