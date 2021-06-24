package edu.gdou.placemange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(
        value = "place_notice"
)
public class PlaceNotice {

    @TableId(
            value = "notice_id",
            type = IdType.AUTO
    )
    private Integer noticeId;
    @TableField(value = "notice_title")
    private String noticeTitle;
    @TableField(value = "notice_content")
    private String noticeContent;
    @TableField(value = "notice_date")
    private Date noticeDate;
    @TableField(value = "notice_state")
    private String noticeState;
    @TableField(value = "place_manageid")
    private String placeManageid;
    @TableField(value = "place_manage")
    private String placeManage;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(String noticeState) {
        this.noticeState = noticeState;
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
        return "PlaceNotice{" +
                "noticeId=" + noticeId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeDate=" + noticeDate +
                ", noticeState='" + noticeState + '\'' +
                ", placeManageid='" + placeManageid + '\'' +
                ", placeManage='" + placeManage + '\'' +
                '}';
    }
}
