package delayedQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李振华
 * @Date 2020/7/25 12:50
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建延时队列
        DelayQueue<Message> queue = new DelayQueue<Message>();
        // 添加延时消息,m1 延时3s
//        Message m1 = new Message(2, "world", 3000);
//        // 添加延时消息,m2 延时10s
//        Message m2 = new Message(1, "hello", 5000);
        //将延时消息放到延时队列中
//        queue.offer(m2);
//        queue.offer(m1);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.submit(new Consumer(queue));
        exec.submit(new Producter(queue));
        exec.shutdown();
    }


    static class Consumer implements Runnable {
        // 延时队列 ,消费者从其中获取消息进行消费
        private DelayQueue<Message> queue;

        public Consumer(DelayQueue<Message> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Message take;
            while (true) {
                try {
                    take = queue.take();
                    System.out.println("消费消息id：" + take.getId() + " 消息体：" + take.getBody());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    static class Producter implements Runnable {

        private DelayQueue<Message> delayQueue;
        private int count = 0;

        public Producter(DelayQueue<Message> delayQueue){
            this.delayQueue = delayQueue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Message message = new Message(count, "消息"+count, (long) (10000*Math.random()));
                    delayQueue.put(message);
                    count++;
                    System.out.println("生产消息id：" + message.getId() + " 消息体：" + message.getBody());
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }


    }









}