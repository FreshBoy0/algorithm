package MyInterceptor;

/**
 * @Author 李振华
 * @Date 2020/7/24 17:38
 */

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*** 自定义拦截器1 * @author rt */
public class MyInterceptor1 implements HandlerInterceptor {


    /**
     *
     *controller方法执行前，进行拦截的方法
     *return true放行 * return false拦截
     *可以使用转发或者重定向直接跳转到指定的页面。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行了...");
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

        System.out.println("拦截器执行了...");

    }









}