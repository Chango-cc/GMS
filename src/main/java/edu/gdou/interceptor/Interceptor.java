package edu.gdou.interceptor;

import edu.gdou.usermanage.entity.Gmsuser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Interceptor implements HandlerInterceptor {
    //handler执行前拦截
    //true 放行
    //false 不能进入handler
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        System.out.println("Interceptor-url:"+request.getRequestURL());
        HttpSession session = request.getSession();
        Gmsuser user = (Gmsuser) session.getAttribute("user");
        if(user != null){
            return true;
        }else{
            String url=request.getRequestURL().toString().replaceAll(request.getRequestURI().toString(),"");
            url+="/login.html";
            response.sendRedirect(url);
            return false;
        }
    }

    //执行handler后调用，在return之前调用
    //应用场景：需要修改handler的modelAndView时可调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //执行handler后调用，在return之后调用
    //应用场景：在调用完handler时，作异常日志的记录
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
