package ThreadLocal;

import java.lang.ref.WeakReference;

/**
 * @Author 李振华
 * @Date 2020/7/25 8:33
 */
public class MyThreadLocal {



    public Object get(){
        Thread thread = Thread.currentThread();
        ThreadLocalMap threadLocalMap = getMap(thread);
        int index = this.hashCode()&(table.length-1);
        ThreadLocalMap.Entry entry = threadLocalMap.table[index];
        return entry.value;
    }

    public void set(Object value) {
        Thread t = Thread.currentThread();
        MyThreadLocal.ThreadLocalMap map = getMap(t);
           // map.set(this, value);
    }

    static class ThreadLocalMap {
        static class Entry extends WeakReference<ThreadLocal<?>> {
            Object value;
            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }

        private static final int INITIAL_CAPACITY = 16;

        private MyThreadLocal.ThreadLocalMap.Entry[] table;

        ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
            table = new MyThreadLocal.ThreadLocalMap.Entry[INITIAL_CAPACITY];
            int i = firstKey.hashCode() & (INITIAL_CAPACITY - 1);
            table[i] = new MyThreadLocal.ThreadLocalMap.Entry(firstKey, firstValue);
        }

    }


    ThreadLocalMap getMap(Thread t) {
        //return t.threadLocals;
        return null;
    }
    private MyThreadLocal.ThreadLocalMap.Entry[] table;

    private MyThreadLocal.ThreadLocalMap.Entry getEntry(MyThreadLocal key) {
        int i = key.hashCode() & (table.length - 1);
        MyThreadLocal.ThreadLocalMap.Entry e = table[i];
        if (e != null)
            return e;
        else
            return null;
    }

}
