package edu.gdou.placemange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdou.placemange.entity.PlaceApply;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PlaceApplyService extends IService<PlaceApply> {

    /*
    插入场地申请信息
     */
    public void PlaceApplyInsert(PlaceApply placeApply);

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
    public List<PlaceApply> PlaceApplySelect(String storey,String type,String check,String userId);

    /*
    查询场地一周信息
     */
    public List<PlaceApply> PlaceApplyDate(Date dateStart,Date dateEnd);

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
