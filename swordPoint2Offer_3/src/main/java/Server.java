import sun.applet.AppletClassLoader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author 李振华
 * @Date 2020/7/12 13:32
 */
public class Server {

    public static void main(String[] args) {
        Selector selector = null;
        try {
            ServerSocketChannel ssc=ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1",8800));
            ssc.configureBlocking(false);
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put("hello client".getBytes());
            writeBuffer.flip();

            while (true){

                int readyNum = selector.select();
                if (readyNum==0){
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it=keys.iterator();

                while (it.hasNext()){
                    SelectionKey key = it.next();
                    if (key.isAcceptable()){
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    }
                    else if (key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuffer.clear();
                        socketChannel.read(readBuffer);
                        readBuffer.flip();
                        System.out.println("server receive :"+ new String(readBuffer.array()));
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    else if (key.isWritable()){
                        writeBuffer.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    it.remove();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (selector!=null){
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





}
