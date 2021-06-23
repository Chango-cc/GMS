package edu.gdou.usermanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

//绑定数据库里的表名,不绑定的话，要求实体类名与数据库表名相同。
@TableName(
        value = "announcement"
)
public class Announcement {

    //绑定主键
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Integer id;
    private Date time;
    private String announcement;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }//

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", time=" + time +
                ", announcement='" + announcement + '\'' +
                '}';
    }
}
