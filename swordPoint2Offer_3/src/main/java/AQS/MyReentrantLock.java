package AQS;

import lombok.SneakyThrows;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author 李振华
 * @Date 2020/7/29 4:27
 */
public class MyReentrantLock implements Lock {

    sharedSync readLock = new MyReentrantLock.sharedSync(10);
    execusiveSync writeLock = new MyReentrantLock.execusiveSync();

    private static final class execusiveSync extends AbstractQueuedSynchronizer{

        @SneakyThrows
        protected boolean tryAcquire(int acquires) {

            Thread currentThread = Thread.currentThread();
            int state = getState();
            if (state == 0){
                compareAndSetState(0,acquires);
                setExclusiveOwnerThread(currentThread);
                return true;
            }else if(currentThread == getExclusiveQueuedThreads()){
                int nextS = state+acquires;
                if (nextS<0){
                    throw new Exception("请求参数有误");
                }
                compareAndSetState(state,nextS);
            }
            return false;
        }

        @SneakyThrows
        protected boolean tryRelease(int acquires) {

            Thread currentThread = Thread.currentThread();
            if (currentThread!=getExclusiveOwnerThread()){
                throw new Exception("当前线程未获得锁，锁释放失败");
            }
            boolean flag = false;
            int nexts = getState()-acquires;

            if (nexts==0){
                compareAndSetState(getState(),0);
                flag = true;
            }else{
                compareAndSetState(getState(),nexts);
            }
            return flag;

        }


    }

    private static final class sharedSync extends AbstractQueuedSynchronizer{

        public sharedSync(int initCount){
            setState(initCount);
        }

        protected int tryAcquireShared(int acquires) {
            while (true){
                int nextState = getState() - acquires;
                if (nextState<0||compareAndSetState(getState(),nextState)){
                    return nextState;
                }
            }
        }
        protected boolean tryReleaseShared(int acquires) {
            while (true){
                int nextState = getState() + acquires;
                if (compareAndSetState(getState(),nextState)){
                    return true;
                }
            }
        }

    }

    @Override
    public void lock() {
        writeLock.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        writeLock.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return writeLock.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return writeLock.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        writeLock.release(1);
    }

    @Override
    public Condition newCondition() {
        return newCondition();
    }
}
