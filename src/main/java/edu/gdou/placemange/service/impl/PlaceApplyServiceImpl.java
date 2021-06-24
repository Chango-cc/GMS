package edu.gdou.placemange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.placemange.entity.Place;
import edu.gdou.placemange.entity.PlaceApply;
import edu.gdou.placemange.entity.PlaceAvailable;
import edu.gdou.placemange.mapper.PlaceApplyMapper;
import edu.gdou.placemange.service.PlaceApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class PlaceApplyServiceImpl extends ServiceImpl<PlaceApplyMapper, PlaceApply> implements PlaceApplyService {

    @Autowired
    PlaceApplyMapper placeApplyMapper;

    /*
    插入场地申请信息
     */
    @Override
    public void PlaceApplyInsert(List<PlaceAvailable> keepList , String userId ) throws ParseException {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0 ; i<keepList.size();i++){
            System.out.println("对应的场地编号是：   "+keepList.get(i).getPlaceNo());
            Date date = dateformat.parse(keepList.get(i).getApplyDate());
            PlaceApply placeApply = new PlaceApply();
            placeApply.setPlaceNo(keepList.get(i).getPlaceNo());
            placeApply.setPlaceStorey(keepList.get(i).getPlaceStorey());
            placeApply.setPlaceType(keepList.get(i).getPlaceType());
            placeApply.setApplyDate(date);
            placeApply.setUserId(userId);
            placeApply.setApplyPeriod(keepList.get(i).getApplyPeriod());
            placeApply.setApplyType("非赛事");
            placeApply.setApplyState("待审核");
            int rows = placeApplyMapper.insert(placeApply);
            System.out.println("插入产生的影响行数"+rows);
        }

    }

    /*
    插入的赛事场地
     */
    @Override
    public void PlaceApplyInserted(List<PlaceAvailable> keepList,String applyType , String userId ) throws ParseException {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0 ; i<keepList.size();i++){
            System.out.println("对应的场地编号是：   "+keepList.get(i).getPlaceNo());
            Date date = dateformat.parse(keepList.get(i).getApplyDate());
            PlaceApply placeApply = new PlaceApply();
            placeApply.setPlaceNo(keepList.get(i).getPlaceNo());
            placeApply.setPlaceStorey(keepList.get(i).getPlaceStorey());
            placeApply.setPlaceType(keepList.get(i).getPlaceType());
            placeApply.setApplyDate(date);
            placeApply.setUserId(userId);
            placeApply.setApplyPeriod(keepList.get(i).getApplyPeriod());
            placeApply.setApplyType(applyType);
            if(applyType.equals("非赛事")){
                placeApply.setApplyState("待审核");
            }else {
                placeApply.setApplyState("已通过");
            }
            int rows = placeApplyMapper.insert(placeApply);
            System.out.println("插入产生的影响行数"+rows);
        }

    }

    /*
    返回对应的赛事预约信息
     */
    public  List<PlaceApply> PlaceApplySelectMatch(String matchId){

        QueryWrapper<PlaceApply> queryWrapper = new QueryWrapper<>();
        //组装条件
        //获得对应场地编号
        queryWrapper.eq("apply_type",matchId);
        List<PlaceApply> list = placeApplyMapper.selectList(queryWrapper);
        return list;
    }
    /*
    修改场地申请状态
     */
    public void PlaceApplyStateUpdate(Integer applyId,String applyState){
        System.out.println("数据库层次------------------------------"+applyId);
        PlaceApply placeApply = new PlaceApply();
        //赋值
        placeApply.setApplyId(applyId);
        placeApply.setApplyState(applyState);
        int rows = placeApplyMapper.updateById(placeApply);
        System.out.println("成功修改场地状态的行数为"+rows);


    }

    /*
   查询场地可用信息
    */
    @Override
    public List<PlaceApply> PlaceApplySelect(String placeNo) {

        QueryWrapper<PlaceApply> queryWrapper = new QueryWrapper<>();
        //组装条件
        queryWrapper.in("apply_state","待审核","待使用");
        //获得对应场地编号
        queryWrapper.eq("place_no",placeNo);
        //查询得到场地预约状态和场地编号符合的场地预约信息
        List<PlaceApply> list = placeApplyMapper.selectList(queryWrapper);
        //进行时间段匹配

        return list;
    }

    @Override
    public IPage<PlaceApply> PlaceApplySelect(Integer current , Integer size,String storey,String type,String check,String userId) {

        Date now = new Date();
        LocalDate localDate=now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date nowDate=java.sql.Date.valueOf(localDate);



        QueryWrapper<PlaceApply> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        //组装条件
        map.put("place_storey",storey);
        map.put("place_type",type);

        map.put("user_id",userId);
        if(check.equals("待使用")){
            map.put("apply_state","已通过");
        map.put("apply_date",nowDate);
        }
        else
        {
            map.put("apply_state",check);
        }
        if(check.equals("已通过")){

            queryWrapper.allEq(map,false).gt("apply_date",nowDate);
        }else
        queryWrapper.allEq(map,false);


        IPage<PlaceApply> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        IPage<PlaceApply> result=placeApplyMapper.selectPage(page,queryWrapper);
        List<PlaceApply> places = result.getRecords();
        System.out.println("palaces.size=---  " + places.size());
        long pages = result.getPages();
        System.out.println("页数：  " + pages);
        System.out.println("总记录数--  "+result.getTotal());
        System.out.println("当前页码：    " + result.getCurrent());
        System.out.println("每页的纪录数： " + result.getSize());
//        List<PlaceApply> list = placeApplyMapper.selectList(queryWrapper);
        //进行时间段匹配

        return result;
    }

    /*
    查询场地一周信息
     */
    @Override
    public IPage<PlaceApply> PlaceApplyDate(Date dateStart, Date dateEnd , Integer current , Integer size, String userId) {

        QueryWrapper<PlaceApply> queryWrapper = new QueryWrapper<>();
        //组装条件
        Map<String,Object> map = new HashMap<>();
        //组装条件
        map.put("user_id",userId);
        queryWrapper.allEq(map,false);

        queryWrapper.between("apply_date",dateStart,dateEnd);

        IPage<PlaceApply> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        IPage<PlaceApply> result=placeApplyMapper.selectPage(page,queryWrapper);
        List<PlaceApply> places = result.getRecords();
        System.out.println("palaces.size=---  " + places.size());
        long pages = result.getPages();
        System.out.println("页数：  " + pages);
        System.out.println("总记录数--  "+result.getTotal());
        System.out.println("当前页码：    " + result.getCurrent());
        System.out.println("每页的纪录数： " + result.getSize());
//        List<PlaceApply> list = placeApplyMapper.selectList(queryWrapper);
        //进行时间段匹配
//        List<PlaceApply> list = placeApplyMapper.selectList(queryWrapper);
        places.forEach(placeApply ->System.out.println(placeApply));
        return result;
    }

    /*
    显示场地目前预约的对应场地状态信息
     */
    @Override
    public List<PlaceApply> PlaceApplyed(String checked, String userId) {

        //创建Map,封装查询条件
        Map<String,Object> map = new HashMap<>();
        map.put("apply_state",checked);
        map.put("apply_id",userId);
        /*
        对应产生的sql语句
         */
        List<PlaceApply> placeApplies = placeApplyMapper.selectByMap(map);
        placeApplies.forEach(placeApply -> {
            System.out.println("预约的场地状态为\""+checked+"\"的有"+placeApply);
        });
        return placeApplies;
    }

    /*
    退订个人目前的场地预约信息
     */
    @Override
    public void PlaceApplyCancel(Integer applyId, Date applyDate) {

        PlaceApply placeApply = new PlaceApply();
        //赋值
        placeApply.setApplyId(applyId);
        placeApply.setApplyState("已退订");
       /*
       调用对应的方法
       UPDATE place_apply SET apply_state=? WHERE apply_id=?
       更新非空字段
        */
        long num = (new Date().getTime()-applyDate.getTime())/(24*60*60*1000);
        String msg = "退订成功";
        if(num>1) {
            int rows = placeApplyMapper.updateById(placeApply);
            System.out.println("插入产生的影响行数"+rows);
        }else {
            msg = "退订失败";
            System.out.println(msg);
        }
    }

    /*
   修改个人场地预约的信息
    */
    @Override
    public void PlaceApplyChange(PlaceApply placeApply) {

        //输入信息
        long num = (new Date().getTime()-placeApply.getApplyDate().getTime())/(24*60*60*1000);
        String msg = "修改成功";
        if(num>1){
            int rows = placeApplyMapper.updateById(placeApply);
            System.out.println("插入产生的影响行数"+rows);
        }else {
            msg = "修改失败";
            System.out.println(msg);
        }
        System.out.println("相隔天数为"+num);
    }

    /*
    场地预约失约处理
     */
    @Override
    public void PlaceApplydeal(Integer applyId) {

        // 创建对象
        PlaceApply placeApply = new PlaceApply();
        //赋值
        placeApply.setApplyId(1);
        placeApply.setApplyState("已处理");
       /*
       调用对应的方法
       UPDATE place_apply SET apply_state=? WHERE apply_id=?
       更新非空字段
        */
        int rows = placeApplyMapper.updateById(placeApply);
        System.out.println("插入产生的影响行数"+rows);

    }

    /*
    场地使用情况查询
     */
    @Override
    public List<PlaceApply> PlaceApplyUse(String placeStorey, String placeType, String userId) {

        QueryWrapper<PlaceApply> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        //组装条件
        map.put("apply_state","待使用");
        map.put("place_storey",placeStorey);
        map.put("place_type",placeType);
        map.put("user_id",userId);
        queryWrapper.allEq(map,false);
        List<PlaceApply> list = placeApplyMapper.selectList(queryWrapper);
        list.forEach(place ->System.out.println(place));
        return list;

    }

    /*
    插入场地使用的开始时间和结束时间
     */
    @Override
    public void PlaceApplyUsetime(Integer applyId,String applyTime,String timeLocal) {

        PlaceApply placeApply = new PlaceApply();
        //赋值
        placeApply.setApplyId(applyId);
        if (timeLocal.equals("start")){
            placeApply.setApplyStart(applyTime);
        }else {
            placeApply.setApplyState("已使用");
            placeApply.setApplyEnd(applyTime);
        }
        //组装条件
        placeApplyMapper.updateById(placeApply);
    }

    @Override
    public void PlaceApplyChange() {

        System.out.println("----------------------这不是开玩笑的");
        Date now = new Date();
        LocalDate localDate=now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date nowDate=java.sql.Date.valueOf(localDate);
        QueryWrapper<PlaceApply> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        //组装条件
        map.put("apply_state","已通过");
        queryWrapper.allEq(map,false).lt("apply_date",nowDate);
        PlaceApply placeApply = new PlaceApply();
        placeApply.setApplyState("已失约");
        placeApplyMapper.update(placeApply,queryWrapper);

        QueryWrapper<PlaceApply> queryWrappered = new QueryWrapper<>();
        Date date = new Date();
        int hours = date.getHours();
        queryWrappered.allEq(map,false).eq("apply_date",nowDate);
        List<PlaceApply> list = placeApplyMapper.selectList(queryWrappered);
        List<PlaceApply> listed = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            int start = list.get(i).getApplyPeriod().indexOf("-");
            System.out.println("----------------------"+start+"----------------------");

            System.out.println("----------------------"+start+"----------------------"+Integer.parseInt(list.get(i).getApplyPeriod().substring(6,8))+"------"+hours);
            if(Integer.parseInt(list.get(i).getApplyPeriod().substring(6,8))<=hours){
                PlaceApply placeApplyed = new PlaceApply();
                placeApplyed.setApplyState("已失约");
                placeApplyed.setApplyId(list.get(i).getApplyId());
                placeApplyMapper.updateById(placeApplyed);
            }
        }
    }

}
