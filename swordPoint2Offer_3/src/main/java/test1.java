import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.applet.AppletClassLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.*;

/**
 * @Author 李振华
 * @Date 2020/7/5 10:47
 */
public class test1 {
    public static void main(String[] args) throws IOException {
//        ArrayList<Integer> arrayList=new ArrayList<Integer>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
//        arrayList.add(5);
//        System.out.println(arrayList.indexOf(1));
//        System.out.println(arrayList.lastIndexOf(1));
//        arrayList.set(4,6);
//        arrayList.remove(4);
//        arrayList.forEach(integer -> System.out.println(integer));
//
//        LinkedList<Integer> array = new LinkedList<>();
//        array.add(0);
//        array.add(1);
//        array.add(2);
//        array.add(3);
//        array.add(4);
//        array.remove();
//        array.forEach(a-> System.out.println(a));
//
//        //ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,2,1000,1)
//
//        PriorityQueue priorityQueue = new PriorityQueue();
//        HashMap<Integer,String> hashMap = new HashMap();
//        hashMap.put(1, "1");
//
//        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
//        ReentrantLock reentrantLock;
//        ReadWriteLock readWriteLock;
//        ThreadPoolExecutor threadPoolExecutor;
//        FutureTask futureTask;
//        CountDownLatch countDownLatch;
//        CyclicBarrier barrier = new CyclicBarrier(2);
//        new Thread(()->{
//            System.out.println("第一个线程开始工作");
//            try {
//                Thread.sleep(2000);
//
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            try {
//                System.out.println(System.currentTimeMillis());
//                System.out.println("第一个线程等待其他线程完成工作");
//                barrier.await();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            System.out.println(System.currentTimeMillis());
//            System.out.println("第一个线程继续工作");
//        }).start();
//
//        new Thread(()->{
//            System.out.println("第二个线程开始工作");
//            try {
//                Thread.sleep(3000);
//
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            try {
//                System.out.println(System.currentTimeMillis());
//                System.out.println("第二个线程等待其他线程完成工作");
//                barrier.await();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            System.out.println(System.currentTimeMillis());
//            System.out.println("第二个线程继续工作");
//        }).start();
//
//        Buffer buffer;
//        ByteBuffer byteBuffer;

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


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

//
//    Thread thread;
//    AbstractQueuedSynchronizer abstractQueuedSynchronizer;
//    AppletClassLoader appletClassLoader;
//    ClassLoader classLoader;
//    ReentrantLock reentrantLock;
//    Condition condition;
//
//    ReentrantReadWriteLock reentrantReadWriteLock;
//    StampedLock stampedLock;
//    Semaphore semaphore;
//
//    CountDownLatch countDownLatch;
//    CyclicBarrier cyclicBarrier;
//
//    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
//
//
//    //线程池
//
//    FutureTask futureTask;
//    CompletableFuture completableFuture;


    //Mybatis
    SqlSessionFactoryBuilder sessionFactoryBuilder;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;










}
