package edu.gdou.placemange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(
        value = "place_apply"
)
public class PlaceApply {

    @TableId(
            value = "apply_id",
            type = IdType.AUTO
    )
    private Integer applyId;//场地申请的编号
    @TableField(value = "user_id")
    private String userId;//场地申请的一卡通
    @TableField(value = "place_no")
    private String placeNo;//申请的场地编号
    @TableField(value = "place_storey")
    private String placeStorey;//申请的场地楼层
    @TableField(value = "place_type")
    private String placeType;//申请的场地类型
    @TableField(value = "apply_date")
    private Date applyDate;//申请的场地日期 2018-09-11
    @TableField(value = "apply_period")
    private String applyPeriod;//场地的使用时间段  08:00-09:00
    @TableField(value = "apply_type")
    private String applyType;//场地申请的类型
    @TableField(value = "apply_start")
    private String applyStart;//场地开始使用时间
    @TableField(value = "apply_end")
    private String applyEnd;//场地结束使用时间
    @TableField(value = "apply_state")
    private String applyState;//场地申请的状态

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public String getPlaceStorey() {
        return placeStorey;
    }

    public void setPlaceStorey(String placeStorey) {
        this.placeStorey = placeStorey;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyPeriod() {
        return applyPeriod;
    }

    public void setApplyPeriod(String applyPeriod) {
        this.applyPeriod = applyPeriod;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getApplyStart() {
        return applyStart;
    }

    public void setApplyStart(String applyStart) {
        this.applyStart = applyStart;
    }

    public String getApplyEnd() {
        return applyEnd;
    }

    public void setApplyEnd(String applyEnd) {
        this.applyEnd = applyEnd;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    @Override
    public String toString() {
        return "PlaceApply{" +
                "applyId=" + applyId +
                ", userId='" + userId + '\'' +
                ", placeNo='" + placeNo + '\'' +
                ", placeStorey='" + placeStorey + '\'' +
                ", placeType='" + placeType + '\'' +
                ", applyDate=" + applyDate +
                ", applyPeriod='" + applyPeriod + '\'' +
                ", applyType='" + applyType + '\'' +
                ", applyStart='" + applyStart + '\'' +
                ", applyEnd='" + applyEnd + '\'' +
                ", applyState='" + applyState + '\'' +
                '}';
    }
}
