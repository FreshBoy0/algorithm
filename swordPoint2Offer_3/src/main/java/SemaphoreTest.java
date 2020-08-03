import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author 李振华
 * @Date 2020/7/15 15:36
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors. newFixedThreadPool(10) ;
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT ; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getId());
                        semaphore.release();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
    }



}
