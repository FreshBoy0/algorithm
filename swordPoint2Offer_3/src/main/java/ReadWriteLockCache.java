import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author 李振华
 * @Date 2020/7/15 10:06
 */
public class ReadWriteLockCache {
    private Map<String, Object> cache = new HashMap<String, Object>();

    public static void main(String[] args) {
        String key = "name";
        ReadWriteLockCache cacheDemo = new ReadWriteLockCache();
        System.out.println(cacheDemo.getData(key)); //从数据库获取数据
        System.out.println(cacheDemo.getData(key)); //从缓存获取数据
        System.out.println(cacheDemo.getData(key)); //从缓存获取数据
    }

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key) {
        rwl.readLock().lock(); //上读锁
        Object value = null;
        try {
            value = cache.get(key); //先查询内部存储器中有没有要的值
            if (value == null) { //如果没有，就去数据库中查询，并将查到的结果存入内部存储器中
                //释放读锁、上写锁
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (value == null) { //再次进行判断，防止多个写线程堵在这个地方重复写
                        System.out.println("read data from database");
                        value = "张三";
                        cache.put(key, value);
                    }
                } finally {
                    //设置完成 释放写锁
                    rwl.writeLock().unlock();
                }
                //恢复读写状态
                rwl.readLock().lock();
            }else{
                System.out.println("read data from cache");
            }
        } finally {
            rwl.readLock().unlock(); //释放读锁
        }
        return value;
    }
}
