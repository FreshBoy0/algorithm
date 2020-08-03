package dynamicProxy;

/**
 * @Author 李振华
 * @Date 2020/7/20 17:56
 */
public interface IActor {
    /**
     * 基本演出
     * @param money
     */
    public void basicAct(float money);
    /**
     * 危险演出
     * @param money
     */
    public void dangerAct(float money);
}
