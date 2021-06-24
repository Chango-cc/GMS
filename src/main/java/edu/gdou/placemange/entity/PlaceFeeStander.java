package edu.gdou.placemange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(
        value = "place_feestander"
)
public class PlaceFeeStander {

    @TableId(
            value = "feestander_id",
            type = IdType.AUTO
    )
    private  Integer feestanderId;
    @TableField(value = "place_type")
    private String placeType;
    @TableField(value = "feestander_money")
    private Integer feestanderMoney;
    @TableField(value = "place_manageid")
    private String placeManageid;
    @TableField(value = "place_manage")
    private String placeManage;

    public Integer getFeestanderId() {
        return feestanderId;
    }

    public void setFeestanderId(Integer feestanderId) {
        this.feestanderId = feestanderId;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public Integer getFeestanderMoney() {
        return feestanderMoney;
    }

    public void setFeestanderMoney(Integer feestanderMoney) {
        this.feestanderMoney = feestanderMoney;
    }

    public String getPlaceManageid() {
        return placeManageid;
    }

    public void setPlaceManageid(String placeManageid) {
        this.placeManageid = placeManageid;
    }

    public String getPlaceManage() {
        return placeManage;
    }

    public void setPlaceManage(String placeManage) {
        this.placeManage = placeManage;
    }

    @Override
    public String toString() {
        return "PlaceFeeStander{" +
                "feestanderId=" + feestanderId +
                ", placeType='" + placeType + '\'' +
                ", feestanderMoney=" + feestanderMoney +
                ", placeManageid='" + placeManageid + '\'' +
                ", placeManage='" + placeManage + '\'' +
                '}';
    }
}
