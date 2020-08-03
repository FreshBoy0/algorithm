import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 李振华
 * @Date 2020/7/15 9:26
 * 有界队列是一种特殊的队列，当队列为空时，队列的获取操作将会阻塞获取线程，直到队列中有新增元素；
 * 当队列已满时，队列中的插入操作将会阻塞线程，直到队列中出现空位
 */
@Transactional
public class BoundedQueue<T> {
    private Object[] items;
    //添加的下标，删除的下标和数组当前的数量
    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    public BoundedQueue(int size){
        this.items = new Object[size];
    }

    //添加元素，如果数组满则添加线程进入等待状态，直到有空位
    public void add(T t) throws InterruptedException{
        lock.lock();
        try {
            while (count == items.length){
                notEmpty.await();
            }
            items[addIndex] = t;
            if (++addIndex==items.length)
                addIndex = 0;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    //头部删除一个元素
    public T remove() throws InterruptedException{
        lock.lock();
        try {
            while (count ==0){
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex==items.length)
                removeIndex=0;
            --count;
            notFull.signal();
            return (T)x;
        }finally {
            lock.unlock();
        }
    }



}
