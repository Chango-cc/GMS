package edu.gdou.matchmanage.controller;

import com.google.gson.JsonObject;
import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.bean.Referee;
import edu.gdou.matchmanage.service.MatchService;
import edu.gdou.usermanage.entity.Gmsuser;
import edu.gdou.utilities.Access;
import edu.gdou.utilities.ParentMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @GetMapping("modifyMatch")
    public String myMatchPage(){
        return "match_byuser";
    }

    @GetMapping("matchDetail")
    public String matchDetailPage(int id){
        ModelAndView modelAndView=new ModelAndView("match_detail");
        System.out.println("id:"+id);
        modelAndView.addObject("id",id);
        return "match_detail";
    }
//    @GetMapping("matchDetail")
//    public String detailMatchPage(){
//        return "match_detail";
//    }
    @GetMapping("censorMatchDetail")
    public String censorMatchDetailPage(){
        return "match_detail_censor";
    }
    @GetMapping("modifyMatchDetail")
    public String modifyMatchDetailPage(){
        return "match_detail_modify";
    }
    //获取记录行数
    @RequestMapping("queryMatchNum1")
    @ResponseBody
    public void queryMatchNum1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setHeader("Content-type", "application/json;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        int num=service.queryMatchNum();
        JsonObject jsonContainer =new JsonObject();
        jsonContainer.addProperty("num", num);
        res.getWriter().write(jsonContainer.toString());
    }
    //获取记录行数
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
    @RequestMapping("queryMatchById")
    @ResponseBody
    public Match queryMatchById(int id){
        Match match=service.queryMatchById(id);
        System.out.println(match);
        return match;
    }
    //获取记录行数
    @RequestMapping("queryMatchNumByUser")
    @ResponseBody
    public int queryMatchNumByUser(HttpSession httpSession) {
        Gmsuser user = (Gmsuser) httpSession.getAttribute("user");
        String id=user.getUserId();
        return service.queryMatchNumByUser(id);
    }
    @RequestMapping("queryMatchByUser")
    @ResponseBody
    public List<Match> queryMatchByUser(int offset,int length,HttpSession httpSession){
        Gmsuser user = (Gmsuser) httpSession.getAttribute("user");
        String id=user.getUserId();
        List<Match> list=service.queryMatchByUser(id,offset,length);
        System.out.println(list);
        return list;
    }
    //获取指定范围记录
    @RequestMapping("queryMatchL")
    @ResponseBody
    public List<Match> queryMatchL(@RequestBody String data){
        String[]arguments=data.split("&");
        String[] argument1=arguments[0].split("=");
        String[] argument2=arguments[1].split("=");
        if(argument1.length>1&&argument2.length>1) {
            List<Match> list = service.queryMatchLimit(Integer.parseInt(argument1[1]), Integer.parseInt(argument2[1]));
            System.out.println(list);
            return list;
        }else
        return null;
    }
    //获取记录行数
    @RequestMapping("queryMatchNumByCondition")
    @ResponseBody
    public int queryMatchNumByCondition(String status,String type) {
        return service.queryMatchNumByCondition(status, type);
    }
    @RequestMapping("queryMatchByCondition")
    @ResponseBody
    public List<Match> queryMatchByCondition(int offset,int length,String status,String type){
        return service.queryMatchByCondition(offset, length, status, type);
    }

    @RequestMapping("addMatch")
    @ResponseBody
    public boolean addMatch(@RequestBody Match match, HttpSession httpSession) {
        System.out.println(match);
        System.out.println(match.getKeepList());
        Gmsuser user = (Gmsuser) httpSession.getAttribute("user");
        match.setUserId(user.getUserId());
        match.setMatchUsername(user.getUserName());
        return service.addMatch(match);
    }
    @RequestMapping("updateMatch")
    @ResponseBody
    public boolean updateMatch(@RequestBody Match match, HttpSession httpSession) {
        System.out.println(match);
        return service.updateMatch(match);
    }
    @RequestMapping("censorMatchPass")
    @ResponseBody
    public boolean censorMatch(int id, String refereeId,String refereeName, HttpSession httpSession) {
//        System.out.println(match);
        return service.updateMatchStatus(id,refereeId,refereeName);
    }
    @RequestMapping("deleteMatch")
    @ResponseBody
    public boolean deleteMatch(int id) {
//        System.out.println(match);
        return service.deleteMatch(id);
    }

    @RequestMapping("getMenu")
    @ResponseBody
    public List<ParentMenu> menu(HttpSession httpSession) {
        Gmsuser user = (Gmsuser) httpSession.getAttribute("user");
        return Access.getList(user);
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
        return service.deleteReferee(index);
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

    @RequestMapping("inquireRefereeByType")
    @ResponseBody
    public List<Referee> queryRefereeByType(String type){
        return service.queryRefereeByType(type);
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
