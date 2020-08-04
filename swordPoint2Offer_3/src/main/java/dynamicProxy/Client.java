package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author 李振华
 * @Date 2020/7/20 17:57
 */
public class Client {
    public static void main(String[] args) {
        //一个剧组找演员：
        final Actor actor = new Actor();//直接

        IActor proxyActor = (IActor) Proxy.newProxyInstance(
                actor.getClass().getClassLoader(),
                actor.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的任何方法，都会经过该方法。
                     * 此方法有拦截的功能。
                     *
                     * 参数：
                     * proxy：代理对象的引用。不一定每次都用得到
                     * method：当前执行的方法对象
                     * args：执行方法所需的参数
                     * 返回值：
                     * 当前执行方法的返回值
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        String name = method.getName();
                        Float money = (Float) args[0];
                        Object rtValue = null;
                        //每个经纪公司对不同演出收费不一样，此处开始判断
                        if("basicAct".equals(name)){
                            //基本演出，没有 2000 不演
                            if(money > 2000){
                                //看上去剧组是给了 8000，实际到演员手里只有 4000
                                //这就是我们没有修改原来 basicAct 方法源码，对方法进行了增强
                                rtValue = method.invoke(actor, money/2);
                            }
                        }
                        if("dangerAct".equals(name)){
                            //危险演出,没有 5000 不演
                            if(money > 5000){
                                //看上去剧组是给了 50000，实际到演员手里只有 25000
                                //这就是我们没有修改原来 dangerAct 方法源码，对方法进行了增强
                                rtValue = method.invoke(actor, money/2);
                            }
                        }
                        return rtValue;
                    }
                });
        //没有经纪公司的时候，直接找演员。
        // actor.basicAct(1000f);
        // actor.dangerAct(5000f);
        //剧组无法直接联系演员，而是由经纪公司找的演员
        proxyActor.basicAct(8000f);
        proxyActor.dangerAct(50000f);
    }
}
