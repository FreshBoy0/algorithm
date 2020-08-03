package blockingQueue;

import org.aspectj.weaver.patterns.NotPointcut;

import javax.xml.stream.events.EndElement;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 李振华
 * @Date 2020/7/25 19:20
 */
public class ArrayBlockingQueue<T> {

    private T[] Items;
    private int capacity;
    private int takeIndex = 0;
    private int putIndex = 0;
    private int count = 0;
    final ReentrantLock takeLock = new ReentrantLock();
    final ReentrantLock putLock = new ReentrantLock();
    final Condition notEmpty = takeLock.newCondition();
    final Condition notFull = putLock.newCondition();

    public ArrayBlockingQueue(int capacity){
        this.capacity = capacity;
    }


    private void enqueue(T element){
        Items[putIndex] = element;
        if (++putIndex >= capacity){
            putIndex=0;
        }
        count++;
        notEmpty.signal();
    }

    private T dequeue(){

        T item = Items[takeIndex];
        Items[takeIndex] = null;
        if (++takeIndex>=capacity){
            takeIndex=0;
        }
        notFull.signal();
        return item;
    }





    public Iterator<T> iterator() {
        return null;
    }

    public int size() {
        return count;
    }

    public boolean offer(T t) {
        if (count==capacity){
            throw new NullPointerException();
        }
        putLock.lock();
        try {
            if (count == Items.length)
                return false;
            else {
                enqueue(t);
                return true;
            }
        } finally {
            putLock.unlock();
        }

    }

    public T poll() {
        if (Items[putIndex]==null){
            return null;
        }
        takeLock.lock();
        try {
            return dequeue();
        }finally {
            takeLock.unlock();
        }
    }

    public T peek() {
        putLock.lock();
        try {
            return Items[takeIndex];
        }finally {
            putLock.unlock();
        }
    }
}
