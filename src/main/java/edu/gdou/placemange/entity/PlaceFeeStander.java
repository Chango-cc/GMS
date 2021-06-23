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
            value = "place_type",
            type = IdType.INPUT
    )
    private String placeType;
    @TableField(value = "feestander_money")
    private Integer feestanderMoney;

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

    @Override
    public String toString() {
        return "PlaceFeeStander{" +
                "placeType='" + placeType + '\'' +
                ", feestanderMoney=" + feestanderMoney +
                '}';
    }
}
