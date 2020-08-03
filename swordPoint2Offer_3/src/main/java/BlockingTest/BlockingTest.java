package BlockingTest;

import threadPool.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 李振华
 * @Date 2020/7/28 16:09
 */
public class BlockingTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(100);
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Consumer consumer = new Consumer(arrayBlockingQueue);
        Produce produce = new Produce(arrayBlockingQueue);
        pool.submit(consumer);
        pool.submit(produce);
//        consumer.start();
//        produce.start();

    }

    static class Consumer extends Thread {

        ArrayBlockingQueue<Integer> arrayBlockingQueue = null;

        public Consumer(ArrayBlockingQueue<Integer> arrayBlockingQueue) {
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        @Override
        public void run() {
            int consumerValue;
            while (true) {
                try {
                    consumerValue = arrayBlockingQueue.take();
                    System.out.println(String.format("消费者消费了一个元素【%s】", consumerValue));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Produce extends Thread {
        ArrayBlockingQueue<Integer> arrayBlockingQueue;

        public Produce(ArrayBlockingQueue<Integer> arrayBlockingQueue) {
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                arrayBlockingQueue.add(i);
                System.out.println(String.format("生产者生产了一个元素【%s】", i));
            }
        }
    }
}
