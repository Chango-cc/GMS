package edu.gdou.usermanage.controller;

import edu.gdou.usermanage.entity.Gmsuser;
import edu.gdou.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/queryUser")
    public String queryUser(){
        return "user_query";
    }
    @RequestMapping("queryUserL")
    @ResponseBody
    public List<Gmsuser> queryMatchL(@RequestBody String data){
        List<Gmsuser> list = userService.selectAll();
        return list;
    }
    @RequestMapping("checkUserL")
    @ResponseBody
    public List<Gmsuser> checkUserL(String user_position,String user_name){
        List<Gmsuser> list = userService.selectByMap(user_position,user_name);
        return list;
    }
    @RequestMapping("refreshList")
    @ResponseBody
    public List<Gmsuser> refreshList(){
        List<Gmsuser> list = userService.selectAll();
        return list;
    }

    @RequestMapping("queryUserNum")
    @ResponseBody
    public int queryUserNum() {
        return userService.selectAllCount();
    }
    @RequestMapping("deleteUser")
    @ResponseBody
    public List<Gmsuser> deleteUser(@RequestBody Gmsuser user) {
//        System.out.println("1111111111:"+user);
        userService.deleteUser(user);//先删除
        List<Gmsuser> list = userService.selectAll();//再重新查询放到页面的list
        return list;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password,
                        String valideCode, HttpSession session, Model model){//ModleAndView
       Gmsuser loginUser =  userService.login(username,password);
        if(loginUser != null){ //成
            //在Session保存用户信
            session.setAttribute("user",loginUser);
//            return "redirect:/queryAuctions";//queryAuction.html//重定向
            return "redirect:/index.html";
        }else {//失败
            model.addAttribute("errorMsg","账号或密码错误");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/doLogout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    //BindingResult表示错误消息存放的容器，必须紧跟pojo对象之后
    @PostMapping("/doRegister")
    public String doRegister(Model model,
                             @ModelAttribute("registerModel") @Validated Gmsuser gmsuser, BindingResult bindingResult, HttpServletResponse response) throws IOException {

        //保存用户的信息到数据库
        Boolean isRegiste = false;
        //先查询是否已存在相同名字和学号的人
        List<Gmsuser> users =userService.selectAll();//查询全部用户
        for(int i=0;i<users.size();i++){
           if(users.get(i).getUser_name().equals(gmsuser.getUser_name())
                   || users.get(i).getUser_id().equals(gmsuser.getUser_id())) {
               isRegiste=true;//已注册
           }
        };
        if (!isRegiste) {//允许注册
            userService.register(gmsuser);
            return "redirect:/user/login";
        }else {//注册失败，弹出警示消息框
            response.setContentType("text/html;charset=utf-8");
            response .getWriter() .write( "<script>alert('注册失败：一卡通 或者 姓名已存在');</script>");
            response .getWriter() .write( "<script>alert('请重新注册');window.location='/user/register';window.close();</script>");//window.local='/uesr/register'表示返回的页面
            response.getWriter().flush();
            return "redirect:/user/register";
        }
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }


}
