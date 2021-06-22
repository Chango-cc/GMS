package edu.gdou.usermanage.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.usermanage.entity.Gmsuser;
import edu.gdou.usermanage.mapper.UserMapper;
import edu.gdou.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service  //@Component    调用数据访问层 Mapper
public class UserServiceImpl extends ServiceImpl<UserMapper,Gmsuser> implements UserService {
    private Boolean isRegiste=false;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Gmsuser login(String username, String password) {
        System.out.println("lllllllllllaaiaiaisdiasdasd");
        Map<String,Object> map = new HashMap<>();
        map.put("user_name",username);
        map.put("user_password",password);
        List<Gmsuser> list = userMapper.selectByMap(map);
        //这里离任相当于注销用户，不允许登录
        if(list.size()>0 && !list.get(0).getState().equals("离任")){
            System.out.println("查到了用户："+list.get(0));
            return list.get(0);

        }
        return null;
    }

    @Override
    public void register(Gmsuser gmsuser) {
        gmsuser.setUser_admin("0");//默认是学生在注册
        gmsuser.setUser_position("学生");//默认是学生在注册
//        gmsuser.setUser_question(null);
        gmsuser.setState("在读");
      userMapper.insert(gmsuser);


    }

    @Override
    public List<Gmsuser> selectAll(){
//        List<Gmsuser> users = userMapper.selectList(null);
//        users.forEach(user ->{
//            System.out.println("用户:"+user);
//        });
        QueryWrapper<Gmsuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("state","离任");//查询 除了"state"="离任"这个条件外 的所有数据
        List<Gmsuser> users = userMapper.selectList( queryWrapper);
//        System.out.println("sdasda:");
        return users;
    }

    @Override
    public int selectAllCount() {
        List<Gmsuser> users = userMapper.selectList(null);
        return users.size();
    }

    //条件查询
    @Override
    public List<Gmsuser> selectByMap(String user_position, String user_name) {
        QueryWrapper<Gmsuser> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        //组装条件
        map.put("user_position",user_position);
        map.put("user_name",user_name);
        queryWrapper.allEq(map,false);
        queryWrapper.ne("state","离任");//查询 除了"state"="离任"这个条件外 的所有数据
        List<Gmsuser> list = userMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public void deleteUser(Gmsuser gmsuser) {
        gmsuser.setId(gmsuser.getId());
        gmsuser.setState("离任");
        userMapper.updateById(gmsuser);
    }

    @Override
    public void updateUser(Gmsuser gmsuser) {
        gmsuser.setId(gmsuser.getId());
        userMapper.updateById(gmsuser);
    }

}
