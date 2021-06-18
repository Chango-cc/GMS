package edu.gdou.matchmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MatchController {
    @RequestMapping(path = {"/helloSpringBoot"})
    @ResponseBody
    public String HelloSpring (){
        System.out.println("hello spring boot");
        return "hello spring boot";
    }
}
