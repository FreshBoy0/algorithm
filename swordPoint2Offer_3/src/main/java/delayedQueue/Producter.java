package delayedQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李振华
 * @Date 2020/7/28 15:15
 */
public class Producter implements Runnable {

    private DelayQueue<Message> delayQueue;
    private int count = 0;

    public Producter(DelayQueue<Message> delayQueue){
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message message = new Message(count, "消息"+count, (long) (System.currentTimeMillis()+1000*Math.random()));
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
