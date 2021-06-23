//package edu.gdou.config;
//
//import edu.gdou.interceptor.CheckUserInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration //SpringMvc的配置类
//public class WebConfigure implements WebMvcConfigurer {
//
//    //addResourceHandler("")虚拟的访问路径 http请求访问图片
//    //addResourceLocations("")文件的物理路径  file: +path
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/pic/**").addResourceLocations("file:d:/file/");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> paths = new ArrayList<>();
//        paths.add("/bootstrap-4.6.0-dist/**");
//        paths.add("/css/**");
//        paths.add("/img/**");
//        paths.add("/js/**");
//        paths.add("/defaultKaptcha");
//        paths.add("/doLogin");
//        paths.add("/login");
//        paths.add("/register");
//        paths.add("/doRegister");
//        paths.add("/vendor/jquery-validation/**");
//        registry.addInterceptor(new CheckUserInterceptor()).addPathPatterns("/**").excludePathPatterns(paths);
//    }
//}
