package delayedQueue;

import java.util.concurrent.DelayQueue;

/**
 * @Author 李振华
 * @Date 2020/7/25 12:47
 */
public class Consumer implements Runnable {
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
