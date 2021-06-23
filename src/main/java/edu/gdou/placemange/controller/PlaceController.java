package edu.gdou.placemange.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.gdou.matchmanage.bean.Match;
import edu.gdou.placemange.entity.*;
import edu.gdou.placemange.service.PlaceApplyService;
import edu.gdou.placemange.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("place")
public class PlaceController {

    @Autowired
    PlaceService placeService;
    @Autowired
    PlaceApplyService placeApplyService;

    /*
    点击跳转到“场地总览”
     */
    @GetMapping("toPlace")
    public String toplace(){

        return "place_query";
    }

    /*
    Vue钩子函数使用ajax请求数据更新
     */
    @RequestMapping("queryPlace")
    @ResponseBody
    public List<Place> queryPlace(){

        List<Place> list = placeService.PlaceIdSelect("","","可使用");
        return list;

    }

    /*
    Vue钩子函数使用ajax请求数据更新
     */
    @RequestMapping("queryPlaceByChecked")
    @ResponseBody
    public List<Place> queryPlaceByChecked(String storey,String type){

        List<Place> list = placeService.PlaceIdSelect(storey,type,"可使用");
        return list;

    }

    /*
    删除场地
     */
    @RequestMapping("placeToDelete")
    @ResponseBody
    public void placeToDelete(Integer placeId){

        System.out.println("----------------------------删除的："+placeId);
        placeService.PlaceDelete(placeId,"已删除");

    }

    /*
    插入场地前的场地编号唯一性验证
     */
    @RequestMapping("placeToChecked")
    @ResponseBody
    public boolean placeToChecked(String placeNo){

        return placeService.PlaceNoChecked(placeNo);

    }

    /*
    进行场地修改
     */
    @RequestMapping("placeAdd")
    public String AddPlace(Place place, HttpSession session){
        place.setPlaceManageid(1);
        place.setPlaceManage("卓世隆");
        place.setPlaceState("可使用");
//        place.setPlace_leader((String) session.getAttribute("username"));
        //插入数据
        placeService.PlaceInsert(place);
        //返回场地信息页面
        return "redirect:/place/toPlace";
    }

    /*
    进行场地信息修改
     */
    @RequestMapping("placeUpdate")
    @ResponseBody
    public String placeUpdate(@RequestBody Place place, HttpSession session){

        System.out.println("chasdbs:"+place.toString());
        placeService.PlaceUpdate(place);
        //返回场地信息页面
        return "success";
    }


    /*
    场地预约,跳转到场地预约页面
     */
    @GetMapping("toPlaceKeep")
    public String toPlaceKeep(){

        return "place_keep";
    }


    @RequestMapping("queryPlaceKeep")
    @ResponseBody
    public String queryPlaceKeep( HttpSession session){

//        System.out.println("chasdbs:"+place.toString());
//        placeService.PlaceUpdate(place);
        //返回场地信息页面
        return "success";
    }

    @RequestMapping("placeKeepSelect")
    @ResponseBody
    public List<PlaceAvailable> placeKeepSelect(@RequestBody SelectMsg selectMsg) throws ParseException {

        System.out.println("要选择的筛选的场地楼层为"+selectMsg.getStorey());
        System.out.println("要选择的筛选的场地类型为"+selectMsg.getStorey());
        System.out.println("要选择的筛选的时间为为"+selectMsg.getTimeCollection().size());
        for (int i = 0; i< selectMsg.getTimeCollection().size();i++){
            System.out.println("选择的日期"+selectMsg.getTimeCollection().get(i).getDate());
            System.out.println("选择的时间："+selectMsg.getTimeCollection().get(i).getTimelist().size());
            for (int k = 0;k<selectMsg.getTimeCollection().get(i).getTimelist().size();k++){
                System.out.println("选择的时间段为"+selectMsg.getTimeCollection().get(i).getTimelist().get(k));
            }
        }

        List<Place> listaim = placeService.PlaceIdSelected(selectMsg.getStorey(),selectMsg.getType(),"可使用");
        listaim.forEach(place ->System.out.println("要匹配的场地编号为"+place));
        List<PlaceAvailable> listfinal = new ArrayList<>();

        for(int m=0 ; m<selectMsg.getTimeCollection().size() ; m++){
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateformat.parse(selectMsg.getTimeCollection().get(m).getDate());
            for ( int i = 0 ;i<listaim.size();i++){
                List<String> listtime = new ArrayList<>();
                if(selectMsg.getTimeCollection().get(m).getTimelist().size()!=0){
                    listtime = selectMsg.getTimeCollection().get(m).getTimelist();
                }
                else {
                    listtime.add("08:00-09:00");
                    listtime.add("09:00-10:00");
                    listtime.add("10:00-11:00");
                    listtime.add("11:00-12:00");
                    listtime.add("14:00-15:00");
                    listtime.add("15:00-16:00");
                    listtime.add("16:00-17:00");
                    listtime.add("17:00-18:00");
                    listtime.add("18:00-19:00");
                    listtime.add("19:00-20:00");
                    listtime.add("20:00-21:00");
                    listtime.add("21:00-22:00");
                }

                List<PlaceApply> list = placeApplyService.PlaceApplySelect(listaim.get(i).getPlaceNo());
                //进行时间段匹配
                for ( int j = 0;j < list.size();j++){
                    if(list.get(j).getApplyDate().equals(date)){
                        for(int k = 0 ; k<listtime.size(); k++  ){
                            if(list.get(j).getApplyPeriod().equals(listtime.get(k))){
                                listtime.remove(k);
                            }
                        }
                        System.out.println("匹配完的时长剩余量为"+listtime.size());
                    }
                }
                if(listtime.size() != 0){
                    for ( int l = 0 ; l<listtime.size() ; l++){
                        PlaceAvailable placeAvailable = new PlaceAvailable(listaim.get(i).getPlaceNo(),
                                listaim.get(i).getPlaceStorey(),listaim.get(i).getPlaceType(),selectMsg.getTimeCollection().get(m).getDate(),
                                listtime.get(l),listaim.get(i).getPlaceManage());
                        listfinal.add(placeAvailable);
                    }
                }
            }
        }
        listfinal.forEach(placeAvailable ->System.out.println("可用的场地可预约为"+placeAvailable));
        System.out.println("                              "+"确实是查询；               ");
        return listfinal;

    }

    @RequestMapping("placeKeeping")
    @ResponseBody
    public String placeKeeping(@RequestBody List<PlaceAvailable> keepList ,HttpSession session) throws ParseException {

        List<PlaceAvailable> list = keepList;
        System.out.println("-------------------------------"+keepList.toString());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0 ; i<list.size();i++){
            System.out.println("对应的场地编号是：   "+list.get(i).getPlaceNo());
            Date date = dateformat.parse(list.get(i).getApplyDate());
            PlaceApply placeApply = new PlaceApply();
            placeApply.setPlaceNo(list.get(i).getPlaceNo());
            placeApply.setPlaceStorey(list.get(i).getPlaceStorey());
            placeApply.setPlaceType(list.get(i).getPlaceType());
            placeApply.setApplyDate(date);
            placeApply.setApplyPeriod(list.get(i).getApplyPeriod());
            placeApply.setApplyType("非赛事");
            placeApply.setApplyState("待审核");
            placeApplyService.PlaceApplyInsert(placeApply);
        }

        return "success";
    }

    /*
    跳转到场地使用
     */
    @GetMapping("toPlaceUse")
    public String toPlaceUse(){

        return "place_use";
    }

    @RequestMapping("placeToSelect")
    @ResponseBody
    public List<PlaceApply> placeToSelect(String storey, String type,String check,HttpSession session){

        System.out.println("controller+------------"+storey+"-----"+check+"   "+type);
        List<PlaceApply> list = placeApplyService.PlaceApplySelect(storey,type,check,null);
        return list;

    }

    @RequestMapping("placeToUpdateState")
    @ResponseBody
    public boolean placeToUpdateState(Integer applyId,String applyState){
        System.out.println("controller层次------------------------"+applyId+"---------"+applyState);
        placeApplyService.PlaceApplyStateUpdate(applyId,applyState);
        return true;
    }

    @RequestMapping("placeToSetTime")
    @ResponseBody
    public boolean placeToSetTime(Integer applyId,String timeStart,String timeLocal){

        System.out.println("controller赋予时间层次------------------------"+applyId+"---------"+timeStart+"    "+timeLocal);
        placeApplyService.PlaceApplyUsetime(applyId,timeStart,timeLocal);
//        placeApplyService.PlaceApplyStateUpdate(applyId,applyState);
        return true;
    }

    @RequestMapping("placeToChange")
    @ResponseBody
    public boolean placeToChange(){
        System.out.println("controller层次---------------------555555---");
        placeApplyService.PlaceApplyChange();
        return true;
    }
}
