package edu.gdou.utilities;

import edu.gdou.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        list.add("/css/**");
        list.add("/img/**");
        list.add("/vendor/**");
        list.add("/js/**");
        list.add("/defaultKaptcha");
        list.add("/user/doLogin");
        list.add("/user/login");
        list.add("/user/register");
        list.add("/user/doRegister");
        list.add("/register");//doRegister
        list.add("/register.html");
        list.add("/doRegister");
        list.add("/login.html");
        list.add("/login");
        list.add("/user/../pages/../login.html");
        list.add("/user/../pages/../register.html");
        list.add("/**/css/**");
        list.add("/**/js/**");
        list.add("/**/vendor/**");
        list.add("/error");
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns(list);
    }
}
