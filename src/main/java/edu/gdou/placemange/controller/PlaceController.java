package edu.gdou.placemange.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.gdou.matchmanage.bean.Match;
import edu.gdou.placemange.entity.*;
import edu.gdou.placemange.service.PlaceApplyService;
import edu.gdou.placemange.service.PlaceFeeStanderService;
import edu.gdou.placemange.service.PlaceNoticeService;
import edu.gdou.placemange.service.PlaceService;
import edu.gdou.usermanage.entity.Gmsuser;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("place")
public class PlaceController {

    @Autowired
    PlaceService placeService;
    @Autowired
    PlaceApplyService placeApplyService;
    @Autowired
    PlaceFeeStanderService placeFeeStanderService;
    @Autowired
    PlaceNoticeService placeNoticeService;

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
    public IPage<Place> queryPlace(Integer current , Integer size){
        IPage<Place> list = placeService.PlaceIdSelect(current,size,"","","可使用");
        return list;

    }

    /*
    Vue钩子函数使用ajax请求数据更新
     */
    @RequestMapping("queryPlaceByChecked")
    @ResponseBody
    public IPage<Place> queryPlaceByChecked(Integer current , Integer size, String storey,String type){

        IPage<Place> list = placeService.PlaceIdSelect(current,size,storey,type,"可使用");
        return list;

    }

    /*
    删除场地
     */
    @RequestMapping("placeToDelete")
    @ResponseBody
    public void placeToDelete(Integer placeId, HttpSession session){

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");

        Place place = new Place();
        place.setPlaceId(placeId);
        place.setPlaceManageid(gmsuser.getUserId());
        place.setPlaceManage(gmsuser.getUserName());
        place.setPlaceState("已删除");
        System.out.println("----------------------------删除的："+placeId);
        placeService.PlaceDelete(place);

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
    进行场地添加
     */
    @RequestMapping("placeAdd")
    public String AddPlace(Place place, HttpSession session){

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");


        place.setPlaceManageid(gmsuser.getUserId());
        place.setPlaceManage(gmsuser.getUserName());
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

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");

        place.setPlaceManageid(gmsuser.getUserId());
        place.setPlaceManage(gmsuser.getUserName());
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

    /*
    场地预约修改,跳转到场地预约页面
     */
    @GetMapping("toPlaceKeepUpdate")
    public String toPlaceKeepUpdate(){

        return "place_keep_update";
    }


//    @RequestMapping("queryPlaceKeep")
//    @ResponseBody
//    public String queryPlaceKeep( HttpSession session){
//
////        System.out.println("chasdbs:"+place.toString());
////        placeService.PlaceUpdate(place);
//        //返回场地信息页面
//        return "success";
//    }

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
    public String placeKeeping(@RequestBody TimeCollection keep , HttpSession session) throws ParseException {

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");

        System.out.println("  - -- - - "+keep.getKeepList().get(0).toString()+"       --"+keep.getDate());
        for (PlaceAvailable placeAvailable : keep.getKeepList()) {
            System.out.println(placeAvailable.toString());
        }
        String stype = keep.getDate();
        if(gmsuser.getUserAdmin().equals("0")){

            stype="非赛事";

        }


            placeApplyService.PlaceApplyInserted(keep.getKeepList(),stype,gmsuser.getUserId());

//        }else placeApplyService.PlaceApplyInsert(keep.getKeepList(),gmsuser.getUserId());

//        List<PlaceAvailable> list = keepList;
//        System.out.println("-------------------------------"+keepList.toString());
//        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 0 ; i<list.size();i++){
//            System.out.println("对应的场地编号是：   "+list.get(i).getPlaceNo());
//            Date date = dateformat.parse(list.get(i).getApplyDate());
//            PlaceApply placeApply = new PlaceApply();
//            placeApply.setPlaceNo(list.get(i).getPlaceNo());
//            placeApply.setPlaceStorey(list.get(i).getPlaceStorey());
//            placeApply.setPlaceType(list.get(i).getPlaceType());
//            placeApply.setApplyDate(date);
//            placeApply.setApplyPeriod(list.get(i).getApplyPeriod());
//            placeApply.setApplyType("非赛事");
//            placeApply.setApplyState("待审核");
//            placeApplyService.PlaceApplyInsert(placeApply);
//        }

        return "success";
    }


    /*
    跳转到场地使用
     */
    @GetMapping("toPlaceUse")
    public String toPlaceUse(){

        return "place_use";
    }

    /*
    查询对应筛选的场地使用情况
     */
    @RequestMapping("placeToSelect")
    @ResponseBody
    public IPage<PlaceApply> placeToSelect(Integer current , Integer size,String storey, String type,String check ,HttpSession session){

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");
        if (gmsuser.getUserAdmin().equals("0")) {

            IPage<PlaceApply> list = placeApplyService.PlaceApplySelect(current,size,storey,type,check,gmsuser.getUserId());
            System.out.println("controller+------------"+storey+"-----"+check+"   "+type);
            return list;

        }else {
            IPage<PlaceApply> list = placeApplyService.PlaceApplySelect(current,size,storey,type,check,null);
            System.out.println("controller+------------"+storey+"-----"+check+"   "+type);
            return list;
        }

    }

    /*
    修改场地申请的状态
     */
    @RequestMapping("placeToUpdateState")
    @ResponseBody
    public boolean placeToUpdateState(Integer applyId,String applyState){
        System.out.println("controller层次------------------------"+applyId+"---------"+applyState);
        placeApplyService.PlaceApplyStateUpdate(applyId,applyState);
        return true;
    }

    /*
    去给对应位置添加纪录的时间
     */
    @RequestMapping("placeToSetTime")
    @ResponseBody
    public boolean placeToSetTime(Integer applyId,String timeStart,String timeLocal){

        System.out.println("controller赋予时间层次------------------------"+applyId+"---------"+timeStart+"    "+timeLocal);
        placeApplyService.PlaceApplyUsetime(applyId,timeStart,timeLocal);
//        placeApplyService.PlaceApplyStateUpdate(applyId,applyState);
        return true;
    }

    /*
    调用场地是否已失约
     */
    @RequestMapping("placeToChange")
    @ResponseBody
    public boolean placeToChange(){
        System.out.println("controller层次---------------------555555---");
        placeApplyService.PlaceApplyChange();
        return true;
    }

    /*
    跳转到场地收费标准
     */
    @GetMapping("toPlaceFee")
    public String toPlaceFee(){

        return "place_feestander";
    }

    /*
    查询所有收费
     */
    @RequestMapping("placeToSelectFee")
    @ResponseBody
    public IPage<PlaceFeeStander> placeToSelectFee(Integer current , Integer size){
        System.out.println("controller层次---------------------555555---");
        IPage<PlaceFeeStander> placeFeeStanders = placeFeeStanderService.PlaceFeeStanderSelect(current,size);
        return placeFeeStanders;
    }

    /*
    收费类型唯一性
     */
    @RequestMapping("placeToCheckedFee")
    @ResponseBody
    public boolean placeToCheckedFee(String placeType){
        System.out.println("controller层次---------------------555555---");
        return placeFeeStanderService.PlaceFeeTypeChecked(placeType);

    }

    /*
    添加收费类型
     */
    @RequestMapping("placeFeeToAdd")
    @ResponseBody
    public boolean placeFeeToAdd(String placeType, Integer money , HttpSession session){

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");
        PlaceFeeStander placeFeeStander = new PlaceFeeStander();
        placeFeeStander.setPlaceType(placeType);
        placeFeeStander.setFeestanderMoney(money);
        placeFeeStander.setPlaceManageid(gmsuser.getUserId());
        placeFeeStander.setPlaceManage(gmsuser.getUserName());
        placeFeeStanderService.PlaceFeeStanderInsert(placeFeeStander);
        return true;
    }

    /*
    修改收费标准
     */
    @RequestMapping("placeToChangeFee")
    @ResponseBody
    public boolean placeToChangeFee(Integer feestanderId,Integer changeMoney,HttpSession session){
        System.out.println("修改场地收费标准-------------------------------controlled---"+feestanderId+"-----"+changeMoney);
        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");
        PlaceFeeStander placeFeeStander = new PlaceFeeStander();
        placeFeeStander.setFeestanderId(feestanderId);
        placeFeeStander.setFeestanderMoney(changeMoney);
        placeFeeStander.setPlaceManageid(gmsuser.getUserId());
        placeFeeStander.setPlaceManage(gmsuser.getUserName());
        placeFeeStanderService.PlaceFeeStanderUpdate(placeFeeStander);
        return true;
    }

    /*
    删除收费标准
     */
    @RequestMapping("placeToDeleteFee")
    @ResponseBody
    public boolean placeToDeleteFee(Integer feestanderId){

        placeFeeStanderService.PlaceFeeStanderDelete(feestanderId);
        return true;
    }

    /*
    跳转到场馆公告页面
     */
    @GetMapping("toPlaceNotice")
    public String toPlaceNotice(){

        return "palce_notice";
    }

    /*
    去查询全部”已发布的“公告
     */
    @RequestMapping("placeToSelectNotice")
    @ResponseBody
    public IPage<PlaceNotice> placeToSelectNotice(Integer current , Integer size ){


        IPage<PlaceNotice> placeNotices = placeNoticeService.PlaceNoticeSelecting(current,size);
        return placeNotices;
    }


    /*
    去添加场馆公告
     */
    @RequestMapping("placeNoticeToAdd")
    @ResponseBody
    public boolean placeNoticeToAdd( String noticeTitle , String noticeContent ,HttpSession session){

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");
        Date now = new Date();
        LocalDate localDate=now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date newDate=java.sql.Date.valueOf(localDate);
        PlaceNotice placeNotice = new PlaceNotice();
        placeNotice.setNoticeTitle(noticeTitle);
        placeNotice.setNoticeContent(noticeContent);
        placeNotice.setNoticeDate(newDate);
        placeNotice.setNoticeState("已发布");
        placeNotice.setPlaceManageid(gmsuser.getUserId());
        placeNotice.setPlaceManage(gmsuser.getUserName());
        placeNoticeService.PlaceNoticeInsert(placeNotice);
        return true;
    }

    /*
    修改场馆公告
     */
    @RequestMapping("placeToChangeNotice")
    @ResponseBody
    public Date placeToChangeNotice(Integer noticeId ,  String noticeTitle , String noticeContent ,HttpSession session){

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");
        Date now = new Date();
        LocalDate localDate=now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date newDate=java.sql.Date.valueOf(localDate);
        PlaceNotice placeNotice = new PlaceNotice();
        placeNotice.setNoticeTitle(noticeTitle);
        placeNotice.setNoticeContent(noticeContent);
        placeNotice.setNoticeDate(newDate);
        placeNotice.setNoticeId(noticeId);
        placeNotice.setPlaceManageid(gmsuser.getUserId());
        placeNotice.setPlaceManage(gmsuser.getUserName());
        placeNotice.setNoticeState("已发布");
        placeNoticeService.PlaceNoticeUpdate(placeNotice);
        return newDate;
    }

    /*
    删除场馆公告
     */
    @RequestMapping("placeToDeleteNotice")
    @ResponseBody
    public boolean placeToDeleteNotice(Integer noticeId , HttpSession session){

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");
        Date now = new Date();
        LocalDate localDate=now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date newDate=java.sql.Date.valueOf(localDate);
        PlaceNotice placeNotice = new PlaceNotice();
        placeNotice.setNoticeDate(newDate);
        placeNotice.setNoticeId(noticeId);
        placeNotice.setPlaceManageid(gmsuser.getUserId());
        placeNotice.setPlaceManage(gmsuser.getUserName());
        placeNotice.setNoticeState("已删除");
        placeNoticeService.PlaceNoticeUpdate(placeNotice);
        return true;
    }

    @RequestMapping("toChangeKeeped")
    @ResponseBody
    public String toChangeKeeped(){

        return "palce_use";
    }

    @RequestMapping("placeToSelectWeek")
    @ResponseBody
    public IPage<PlaceApply> placeToSelectWeek(String week , Integer current , Integer size , HttpSession session){

        Gmsuser gmsuser = (Gmsuser) session.getAttribute("user");
        String userId ;
        if (gmsuser.getUserAdmin().equals("0")) {

            userId = gmsuser.getUserId();

        }else {
            userId="";
        }

            Calendar now = Calendar.getInstance();
        boolean isFirstSunday = (now.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekDay = now.get(Calendar.DAY_OF_WEEK);
        if(isFirstSunday){
            weekDay = weekDay - 1;
            if(weekDay == 0){
                weekDay = 7;
            }
        }
        LocalDate localDateing = LocalDate.now();   //  2019-01-31

        if(week.equals("上周")){

            LocalDate weekDateStartpre = localDateing.minusDays(weekDay+6);
            ZonedDateTime zonedDateStartpre = weekDateStartpre.atStartOfDay(ZoneId.systemDefault());
            LocalDate weekDateEndpre = localDateing.minusDays(weekDay);
            ZonedDateTime zonedDateEndpre = weekDateEndpre.atStartOfDay(ZoneId.systemDefault());
            IPage<PlaceApply> placeApplyIPage = placeApplyService.PlaceApplyDate(Date.from(zonedDateStartpre.toInstant()),Date.from(zonedDateEndpre.toInstant()),current,size,userId);
            return placeApplyIPage;
        }
        if(week.equals("本周")){

            LocalDate weekDateStart = localDateing.minusDays(weekDay-1);
            ZonedDateTime zonedDateStart = weekDateStart.atStartOfDay(ZoneId.systemDefault());
            LocalDate weekDateEnd = localDateing.plusDays(7-weekDay);
            ZonedDateTime zonedDateEnd = weekDateEnd.atStartOfDay(ZoneId.systemDefault());
            IPage<PlaceApply> placeApplyIPage = placeApplyService.PlaceApplyDate(Date.from(zonedDateStart.toInstant()),Date.from(zonedDateEnd.toInstant()),current,size,userId);

            return placeApplyIPage;
        }
        if(week.equals("下周")){

            LocalDate weekDateStartnext = localDateing.plusDays(7-weekDay+1);
            ZonedDateTime zonedDateStartnext = weekDateStartnext.atStartOfDay(ZoneId.systemDefault());
            LocalDate weekDateEndnext = localDateing.plusDays(7-weekDay+7);
            ZonedDateTime zonedDateEndnext = weekDateEndnext.atStartOfDay(ZoneId.systemDefault());
            IPage<PlaceApply> placeApplyIPage = placeApplyService.PlaceApplyDate(Date.from(zonedDateStartnext.toInstant()),Date.from(zonedDateEndnext.toInstant()),current,size,userId);
            return placeApplyIPage;
        }

        return null;
    }

    /*
    点击跳转到“普通场地总览”
     */
    @GetMapping("toPlaceUser")
    public String toPlaceUser(){

        return "place_query_user";
    }

    /*
    点击跳转到”普通场地申请“
     */
    @GetMapping("toPlaceKeepUser")
    public String toPlaceKeepUser(){

        return "place_keep_user";
    }

    /*
    跳转到”普通场地用况“
     */
    @GetMapping("toPlaceUseUser")
    public String toPlaceUseUser(){

        return "place_use_user";
    }

    /*
    跳转到”普通场地收费“
     */
    @GetMapping("toPlaceFeeUser")
    public String toPlaceFeeUser(){

        return "place_feestander_user";
    }

    /*
    跳转到”普通场地公告“
     */
    @GetMapping("toPlaceNoticeUser")
    public String toPlaceNoticeUser(){

        return "place_notice_user";

    }
}
