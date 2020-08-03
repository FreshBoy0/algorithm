package dynamicProxy.account.factory;

import dynamicProxy.account.AccountServiceImpl;
import dynamicProxy.account.IAccountService;
import dynamicProxy.account.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author 李振华
 * @Date 2020/7/20 18:35
 */
public class BeanFactory {

    /**
     * 创建账户业务层实现类的代理对象
     * @return
     */
    TransactionManager transactionManager;
    public IAccountService getAccountService() {
        //1.定义被代理对象
        final IAccountService accountService = new AccountServiceImpl();
        //2.创建代理对象
        IAccountService proxyAccountService = (IAccountService)
                Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                        accountService.getClass().getInterfaces(),new
                                InvocationHandler() {
                                    /**
                                     * 执行被代理对象的任何方法，都会经过该方法。
                                     * 此处添加事务控制
                                     */
                                    @Override
                                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                        Object rtValue = null;
                                        try {
                                            //开启事务
                                            transactionManager.beginTransaction();
                                            //执行业务层方法
                                            rtValue = method.invoke(accountService, args);
                                            //提交事务
                                            transactionManager.commit();
                                        }catch(Exception e) {
                                            //回滚事务
                                            transactionManager.rollback();
                                            e.printStackTrace();
                                        }finally {
                                            //释放资源
                                            transactionManager.release();
                                        }
                                        return rtValue;
                                    }
                                });
        return proxyAccountService;
    }
}



