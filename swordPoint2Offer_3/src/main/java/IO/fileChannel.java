package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author 李振华
 * @Date 2020/7/26 15:03
 */
public class fileChannel {

    public static void main(String[] args) throws IOException {


        RandomAccessFile randomAccessFile = null;
        FileChannel inChannel = null;
        try {
            randomAccessFile = new RandomAccessFile("C:\\Users\\振华\\Desktop\\test.txt", "rw");
            inChannel = randomAccessFile.getChannel();
            ByteBuffer writeBuf = ByteBuffer.allocate(256);
            writeBuf.put("好好学习，天天向上".getBytes());
            writeBuf.flip();
            inChannel.write(writeBuf);

            File file = new File("C:\\Users\\振华\\Desktop\\test.txt");
            System.out.println(file.getCanonicalFile());


        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        finally{
            if (inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (randomAccessFile!=null){
                randomAccessFile.close();
            }
        }

        }
}
