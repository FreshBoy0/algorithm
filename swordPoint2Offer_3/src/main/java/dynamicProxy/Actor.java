package dynamicProxy;

/**
 * @Author 李振华
 * @Date 2020/7/20 17:57
 */
public class Actor implements IActor{
    public void basicAct(float money){
        System.out.println("拿到钱，开始基本的表演： "+money);
    }
    public void dangerAct(float money){
        System.out.println("拿到钱，开始危险的表演： "+money);
    }
}
