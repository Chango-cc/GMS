package edu.gdou.placemange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName(
        value = "place"
)
public class Place  {

    //定义对应的属性，属性名对应表中的字段名
    @TableId(
            value = "place_id",
            type = IdType.INPUT
    )
    private Integer placeId;
    @TableField(value = "place_no")
    private String placeNo;//场地编号
    @TableField(value = "place_storey")
    private String placeStorey;//场地所属楼层
    @TableField(value = "place_type")
    private String placeType;//场地类型
    @TableField(value = "place_manageid")
    private Integer placeManageid;//场地负责人编号
    @TableField(value = "place_manage")
    private String placeManage;//场地负责人
    @TableField(value = "place_state")
    private String placeState;//场地状态

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
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

    public Integer getPlaceManageid() {
        return placeManageid;
    }

    public void setPlaceManageid(Integer placeManageid) {
        this.placeManageid = placeManageid;
    }

    public String getPlaceManage() {
        return placeManage;
    }

    public void setPlaceManage(String placeManage) {
        this.placeManage = placeManage;
    }

    public String getPlaceState() {
        return placeState;
    }

    public void setPlaceState(String placeState) {
        this.placeState = placeState;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", placeNo='" + placeNo + '\'' +
                ", placeStorey='" + placeStorey + '\'' +
                ", placeType='" + placeType + '\'' +
                ", placeManageid=" + placeManageid +
                ", placeManage='" + placeManage + '\'' +
                ", placeState='" + placeState + '\'' +
                '}';
    }
}
