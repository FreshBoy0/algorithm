package threadPool;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 李振华
 * @Date 2020/7/25 16:06
 */
public class Executors<T extends Delayed> {

    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new java.util.concurrent.ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static ExecutorService newSingleThreadExecutor() {
        return new ThreadPoolExecutor(1, 1,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());
    }


    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return (ScheduledExecutorService) new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE,
                1, TimeUnit.MILLISECONDS,
                new DelayedWorkQueue());
    }




    static class DelayedWorkQueue extends AbstractQueue<Runnable>
            implements BlockingQueue<Runnable> {

        @Override
        public Iterator<Runnable> iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public void put(Runnable runnable) throws InterruptedException {

        }

        @Override
        public boolean offer(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public Runnable take() throws InterruptedException {
            return null;
        }

        @Override
        public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
            return null;
        }

        @Override
        public int remainingCapacity() {
            return 0;
        }

        @Override
        public int drainTo(Collection<? super Runnable> c) {
            return 0;
        }

        @Override
        public int drainTo(Collection<? super Runnable> c, int maxElements) {
            return 0;
        }

        @Override
        public boolean offer(Runnable runnable) {
            return false;
        }

        @Override
        public Runnable poll() {
            return null;
        }

        @Override
        public Runnable peek() {
            return null;
        }
    }



}
