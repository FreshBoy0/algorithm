package code.offer.src.Chap6;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import jdk.concurrent.AQS.AbstractQueuedSynchronizer;
import jdk.concurrent.AQS.ReentrantLock;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import sun.misc.Unsafe;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 定义一个队列，实现max方法得到队列中的最大值。
 * 要求入列、出列以及邱最大值的方法时间复杂度都是O(1)
 */
public class MaxQueue {
    private Deque<Integer> maxDeque = new LinkedList<>();
    private Deque<Integer> dataDeque = new LinkedList<>();

    public void offer(int number) {
        dataDeque.offerLast(number);
        // 即将要存入的元素比当前队列最大值还大，存入该元素
        if (maxDeque.isEmpty() || number > maxDeque.peekFirst()) {
            maxDeque.offerFirst(number);
        }
        // 即将要存入的元素不超过当前队列最大值，再将最大值存入一次
        else {
            maxDeque.offerFirst(maxDeque.peekFirst());
        }
    }

    public void poll() {
        if (dataDeque.isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        dataDeque.pollFirst();
        maxDeque.pollFirst();
    }

    public int max() {
        if (maxDeque.isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return maxDeque.peekFirst();
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.offer(2);
        maxQueue.offer(3);
        maxQueue.offer(4);
        maxQueue.offer(2);
        maxQueue.offer(6);
        maxQueue.offer(2);
        maxQueue.offer(5);
        maxQueue.offer(1);

        System.out.println(maxQueue.max());
        maxQueue.poll();
        maxQueue.poll();
        maxQueue.poll();
        maxQueue.poll();
        System.out.println(maxQueue.max());
        maxQueue.poll();
        System.out.println(maxQueue.max());
        maxQueue.poll();
        System.out.println(maxQueue.max());

        System.out.println(DateFormatUtils.format(new Date(),"MMdd"));



        CountDownLatch countDownLatch;
        AbstractOwnableSynchronizer abstractOwnableSynchronizer;//独占
        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        ReentrantLock reentrantLock;
        ReentrantReadWriteLock reentrantReadWriteLock;
        Unsafe unsafe;


    }
}
