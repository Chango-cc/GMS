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
    private String user_id;//一卡通/

    private String user_name;//名字//
    private String user_password;//密码
    private String user_cardno;//身份证
    private String user_tel;//电话号码
    private String user_admin;//用户身份
    private String user_position;//用户身份职位
    private String user_question;//
    private String user_answer;//
    private String state;//用户在职状态


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_cardno() {
        return user_cardno;
    }

    public void setUser_cardno(String user_cardno) {
        this.user_cardno = user_cardno;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_admin() {
        return user_admin;
    }

    public void setUser_admin(String user_admin) {
        this.user_admin = user_admin;
    }

    public String getUser_position() {
        return user_position;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }

    public String getUser_question() {
        return user_question;
    }

    public void setUser_question(String user_question) {
        this.user_question = user_question;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
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
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_cardno='" + user_cardno + '\'' +
                ", user_tel='" + user_tel + '\'' +
                ", user_admin='" + user_admin + '\'' +
                ", user_position='" + user_position + '\'' +
                ", user_question='" + user_question + '\'' +
                ", user_answer='" + user_answer + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
