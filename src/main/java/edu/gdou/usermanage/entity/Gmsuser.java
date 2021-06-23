package edu.gdou.usermanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//绑定数据库里的表名,不绑定的话，要求实体类名与数据库表名相同。
@TableName(
        value = "gmsuser"
)
public class Gmsuser {
    //绑定主键
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    //绑定数据库里的列名，不绑定的话要求字段名和数据库列名相同
    @TableField(value = "id")
    private Integer id;//自增id

    @TableField(value = "user_id")
    private String userId;//一卡通/

    private String userName;//名字//
    private String userPassword;//密码
    private String userCardno;//身份证
    private String userTel;//电话号码
    private String userAdmin;//用户身份
    private String userPosition;//用户身份职位
    private String userQuestion;//
    private String userAnswer;//
    private String state;//用户在职状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCardno() {
        return userCardno;
    }

    public void setUserCardno(String userCardno) {
        this.userCardno = userCardno;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Gmsuser{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCardno='" + userCardno + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userAdmin='" + userAdmin + '\'' +
                ", userPosition='" + userPosition + '\'' +
                ", userQuestion='" + userQuestion + '\'' +
                ", userAnswer='" + userAnswer + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
