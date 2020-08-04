import java.util.concurrent.CountDownLatch;

/**
 * @Author 李振华
 * @Date 2020/7/15 16:26
 */
public class Employee2 extends Thread{

    private String employeeName;

    private long time;

    private CountDownLatch countDownLatch;

    public Employee2(String employeeName,long time, CountDownLatch countDownLatch){
        this.employeeName = employeeName;
        this.time = time;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(employeeName+ " 第一阶段开始准备");
            Thread.sleep(time);
            System.out.println(employeeName+" 第一阶段准备完成");

            countDownLatch.countDown();

            System.out.println(employeeName+ " 第二阶段开始准备");
            Thread.sleep(time);
            System.out.println(employeeName+" 第二阶段准备完成");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Employee2 a = new Employee2("A", 3000,countDownLatch);
        Employee2 b = new Employee2("B", 3000,countDownLatch);
        Employee2 c = new Employee2("C", 4000,countDownLatch);

        b.start();
        c.start();
        countDownLatch.await();
        System.out.println("B,C准备完成");
        a.start();
    }
}
