import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 李振华
 * @Date 2020/7/15 16:05
 */
public class JoinCountDownLatch {
    private static CountDownLatch countDownLatch = new CountDownLatch(2) ;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("child threadOne over");
            }
        });


        threadPool.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("child threadTwo over");
            }
        });
        countDownLatch.await();
        System.out.println("wait all child thread over");
        threadPool.shutdown();
    }
}