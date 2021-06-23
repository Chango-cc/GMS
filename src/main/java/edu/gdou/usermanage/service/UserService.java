package edu.gdou.usermanage.service;

import edu.gdou.usermanage.entity.Gmsuser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //@Component    调用数据访问层 Mapper
public interface UserService {

    /**
     * 登陆验证方法
     * @param username 账号
     * @param password 密码
     * @return    返回当前登录的用户对象，使用非空判断是否存在
     */
    public Gmsuser login(String username, String password);

    public void register(Gmsuser user);

    //无条件查询全部
    public List<Gmsuser> selectAll();//
    //无条件查询全部数据的条数
    public int selectAllCount();

    //条件查询//
    public List<Gmsuser> selectByMap(String user_position,String user_name);

    //

    //仿删除，将用户在职状态改为离任
    public void deleteUser(Gmsuser gmsuser);

    //更新用户信息
    public void updateUser(Gmsuser gmsuser);
}
