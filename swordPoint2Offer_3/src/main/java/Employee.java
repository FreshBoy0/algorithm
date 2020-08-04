import java.util.concurrent.CountDownLatch;

/**
 * @Author 李振华
 * @Date 2020/7/15 16:23
 */
public class Employee extends Thread{

    private String employeeName;

    private long time;

    private CountDownLatch countDownLatch;

    public Employee(String employeeName,long time, CountDownLatch countDownLatch){
        this.employeeName = employeeName;
        this.time = time;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(employeeName+ "开始准备");
            Thread.sleep(time);
            System.out.println(employeeName+" 准备完成");
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Employee a = new Employee("A", 3000,countDownLatch);
        Employee b = new Employee("B", 3000,countDownLatch);
        Employee c = new Employee("C", 4000,countDownLatch);

        b.start();
        c.start();
        countDownLatch.await();
        System.out.println("B,C准备完成");
        a.start();
    }
}