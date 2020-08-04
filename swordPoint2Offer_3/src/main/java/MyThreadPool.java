import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 李振华
 * @Date 2020/7/15 13:46
 */
public class MyThreadPool  {
    public void execute(Runnable command){
        if (command==null){
            throw new NullPointerException();
        }
    }
}
