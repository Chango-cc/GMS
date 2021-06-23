package edu.gdou.matchmanage.controller;


import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.bean.Referee;
import edu.gdou.matchmanage.service.MatchService;
import edu.gdou.utilities.Access;
import edu.gdou.utilities.ParentMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("match")
public class MatchController {
    @Autowired
    private MatchService service;

    @GetMapping("queryMatch")
    public String queryMatchPage(){
//        Timestamp a=new Timestamp(System.currentTimeMillis()/1000);
//        Match match=new Match("3","name3",  a,"place3","tools3","type3","describe3","null","null",1,"1");
//        boolean r=service.addMatch(match);
//        System.out.println("result:"+r);censorMatch
        return "match_query";
    }

    @GetMapping("censorMatch")
    public String censorMatchPage(){
        return "match_censor";
    }
    @GetMapping("newMatch")
    public String newMatchPage(){
//    public ModelAndView newMatchPage(){
//        Timestamp a=new Timestamp(System.currentTimeMillis()/1000);
//        Match match=new Match("3","name3",  a,"place3","tools3","type3","describe3","null","null",1,"1");
//        boolean r=service.addMatch(match);
//        System.out.println("result:"+r);
//        ModelAndView modelAndView=new ModelAndView("matchnew");
//        return modelAndView;
        return "match_new";
    }

//    @RequestMapping("queryMatchNum1")
//    @ResponseBody
//    public void queryMatchNum1(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        res.setHeader("Content-type", "application/json;charset=UTF-8");
//        res.setCharacterEncoding("UTF-8");
//        int num=service.queryMatchNum();
//        JsonObject jsonContainer =new JsonObject();
//        jsonContainer.addProperty("num", num);
//        res.getWriter().write(jsonContainer.toString());
//    }

    @RequestMapping("queryMatchNum")
    @ResponseBody
    public int queryMatchNum() {
        return service.queryMatchNum();
    }

    @RequestMapping("queryMatchList")
    @ResponseBody
    public List<Match> queryMatch(){
        List<Match> list=service.queryMatch();
        System.out.println(list);
        return list;
    }

    @RequestMapping("queryMatchL")
    @ResponseBody
    public List<Match> queryMatchL(@RequestBody String data){
        String[]arguments=data.split("&");
        String[] argument1=arguments[0].split("=");
        String[] argument2=arguments[1].split("=");
        if(argument1.length>1&&argument2.length>1) {
            List<Match> list = service.queryMatchLimit(Integer.parseInt(argument1[1]), Integer.parseInt(argument2[1]));
            return list;
        }else
        return null;
    }

    @RequestMapping("addMatch")
    @ResponseBody
    public String addMatch() {
        return null;
    }

    @RequestMapping("getMenu")
    @ResponseBody
    public List<ParentMenu> menu() {
        return Access.getList();
    }


    @RequestMapping("addReferee")
    @ResponseBody
    public boolean addReferee(@RequestBody Referee referee){
//        System.out.println(referee);
        return service.addReferee(referee);
    }

    @RequestMapping("removeReferee")
    @ResponseBody
    public boolean deleteReferee(int index){
        System.out.println(index);
        return false;
    }

    @RequestMapping("queryRefereeNum")
    @ResponseBody
    public int queryRefereeNum() {
        return service.queryRefereeNum();
    }

    @RequestMapping("inquireReferee")
    @ResponseBody
    public List<Referee> queryReferee(){
        return service.queryReferee();
    }

    @RequestMapping("queryRefereeL")
    @ResponseBody
    public List<Referee> queryRefereeL(int offset,int length){
        return service.queryRefereeLimit(offset,length);
    }

    @RequestMapping("queryReferee")
    public String queryRefereePage(){
        return "match_referee_query";
    }
    @RequestMapping("newReferee")
    public String newRefereePage(){
        return "match_referee_new";
    }
    @RequestMapping("deleteReferee")
    public String deleteRefereePage(){
        return "match_referee_delete";
    }
}
