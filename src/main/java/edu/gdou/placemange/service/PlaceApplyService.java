package edu.gdou.placemange.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdou.placemange.entity.PlaceApply;
import edu.gdou.placemange.entity.PlaceAvailable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface PlaceApplyService extends IService<PlaceApply> {

    /*
    插入场地申请信息
     */
//    public void PlaceApplyInsert(PlaceApply placeApply);
    public void PlaceApplyInsert(List<PlaceAvailable> keepList , String userId ) throws ParseException;

    /*
    插入赛事场地申请,或预留类型
     */
    public void PlaceApplyInserted(List<PlaceAvailable> keepList,String applyType , String userId ) throws ParseException;

    /*
    查询对应的场地申请
     */
    public List<PlaceApply> PlaceApplySelectMatch(String matchId);

    /*
    修改场地申请状态
     */
    public void PlaceApplyStateUpdate(Integer applyId,String applyState);
    /*
    查询场地可用信息
     */
    public List<PlaceApply> PlaceApplySelect(String placeNo);

    /*
    查询待审核的场地
     */
    public IPage<PlaceApply> PlaceApplySelect(Integer current , Integer size, String storey, String type, String check, String userId);

    /*
    查询场地一周信息
     */
    public IPage<PlaceApply> PlaceApplyDate(Date dateStart,Date dateEnd,Integer current , Integer size , String userId);

    /*
    显示场地目前预约的对应场地状态信息
     */
    public List<PlaceApply> PlaceApplyed(String checked,String userId);

    /*
    退订个人目前的场地预约信息
     */
    public void PlaceApplyCancel(Integer applyId,Date applyDate);

    /*
    修改个人场地预约的信息
     */
    public void PlaceApplyChange(PlaceApply placeApply);

    /*
    场地预约失约处理
     */
    public void PlaceApplydeal(Integer applyId);

    /*
    场地使用情况查询
     */
    public List<PlaceApply> PlaceApplyUse(String placeStorey,String placeType,String userId);

    /*
    插入场地使用的开始时间和结束时间
     */
    public void PlaceApplyUsetime(Integer applyId,String applyTime,String timeLocal);

    /*
   插入场地使用的开始时间和结束时间
    */
    public void PlaceApplyChange();

}
