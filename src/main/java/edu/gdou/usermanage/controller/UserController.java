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
    @RequestMapping("updateUser")
    @ResponseBody
    public void updateUser(Integer id,String updateUserName,String updateUserPosition,String updateUserTel){
        Gmsuser updateuser=new Gmsuser();
        updateuser.setId(id);
        updateuser.setUserName(updateUserName);
        updateuser.setUserPosition(updateUserPosition);
        updateuser.setUserTel(updateUserTel);
        userService.updateUser(updateuser);
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
        userService.deleteUser(user);//?????????
        List<Gmsuser> list = userService.selectAll();//??????????????????????????????list
        return list;
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("logintest");
        return "../login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password,
                        String valideCode, HttpSession session, Model model){//ModleAndView
       Gmsuser loginUser =  userService.login(username,password);
        if(loginUser != null){ //??????
            //???Session??????????????????
            session.setAttribute("user",loginUser);
//            return "redirect:/queryAuctions";//queryAuction.html//?????????
            return "redirect:/index.html";
        }else {//??????
            model.addAttribute("errorMsg","?????????????????????");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/doLogout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:1";
    }

    @GetMapping("/register")
    public String register(){
        return "../register";
    }

    //BindingResult????????????????????????????????????????????????pojo????????????
    @PostMapping("/doRegister")
    public String doRegister(Model model,
                             @ModelAttribute("registerModel") @Validated Gmsuser gmsuser, BindingResult bindingResult, HttpServletResponse response) throws IOException {

        //?????????????????????????????????
        Boolean isRegiste = false;
        //???????????????????????????????????????????????????
        List<Gmsuser> users =userService.selectAll();//??????????????????
        for(int i=0;i<users.size();i++){
           if(users.get(i).getUserName().equals(gmsuser.getUserName())
                   || users.get(i).getUserId().equals(gmsuser.getUserId())) {
               isRegiste=true;//?????????
           }
        };
        if (!isRegiste) {//????????????
            userService.register(gmsuser);
            return "redirect:/user/login";
        }else {//????????????????????????????????????
            response.setContentType("text/html;charset=utf-8");
            response .getWriter() .write( "<script>alert('???????????????????????? ?????? ???????????????');</script>");
            response .getWriter() .write( "<script>alert('???????????????');window.location='/user/register';window.close();</script>");//window.local='/uesr/register'?????????????????????
            response.getWriter().flush();
            return "redirect:/user/register";
        }
    }

    @PostMapping("/updateUser")
    public String updateUser(Gmsuser updateuser,@RequestParam("userId") Integer id){
        System.out.println("id:"+id);
        updateuser.setId(id);
        System.out.println("!!!!!:"+updateuser);
        userService.updateUser(updateuser);
        return "redirect:/user/queryUser";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }


}
