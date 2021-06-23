//package edu.gdou.interceptor;
//
//import edu.gdou.usermanage.entity.Gmsuser;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//public class CheckUserInterceptor implements HandlerInterceptor {
//
//    //在执行handler(相当于controller)方法之前拦截
//    //true:放行，进入到handler
//    //false;不放行，不能进入handler
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //判断用户是否已经登陆？seesion
//        HttpSession session = request.getSession();
//        Gmsuser user = (Gmsuser) session.getAttribute("user");
////        System.out.println("!!!!!!:"+user);
//        if(user!=null){
//            return true;
//        }else{
//            String urlString = request.getRequestURI();
//            System.out.println("urlString:= "+urlString);
//            //这个地方要做判断不然会无限循环重定向，即执行过滤器、拦截器很多遍
//            if(urlString.endsWith("/user/login")){
//                return true;
//            }else{
//                //重定向到login.html
//                response.sendRedirect("/user/login");
//                return false;
//            }
//
//        }
//    }
//
//    //执行了handler之后，在return之前调用
//    //应用场景：在需要修改Handler的ModelAndView时可调用
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    //执行了handler之后，在return之后调用
//    //应用场景：会在调用完Handler时，做异常的日志记录
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
