import com.sun.javafx.image.BytePixelSetter;

import javax.imageio.ImageTranscoder;
import java.util.concurrent.RecursiveTask;

/**
 * @Author 李振华
 * @Date 2020/7/15 16:42
 */
public class CountTask extends RecursiveTask {
    private static final int THRESHOLD = 2;
    private int start;
    private int end;
    public CountTask(int strat,int end){
        this.start = strat;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean flag = (end-start)<=THRESHOLD;
        if (flag){
            for (int i = start; i <= end; i++) {
                sum+=i;
            }
        }else{
            int middle = (start+end)/2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle+1, end);
            leftTask.fork();
            rightTask.fork();
            int leftResult = (int) leftTask.join();
            int rightResult = (int) rightTask.join();
            sum = leftResult+rightResult;
        }

        return sum;
    }
}
