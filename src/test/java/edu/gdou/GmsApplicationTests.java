package edu.gdou;

import edu.gdou.usermanage.entity.Gmsuser;
import edu.gdou.usermanage.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@SpringBootTest
class GmsApplicationTests {

	Boolean b=false;

	@Test
	void contextLoads() {
	}

	//使用自动注入，注入Mapper对象（Dao）
	@Autowired
	private UserMapper userDao;

	//登录
	@Test
	void testLogin(){
		String username="Mike";
		String password="00000";
		Map<String,Object> map = new HashMap<>();
		map.put("user_name",username);
		map.put("user_password",password);
		List<Gmsuser> list = userDao.selectByMap(map);
		if(list.size()>0){
			System.out.println(list.get(0));
		}
	}

	//增加用户
	@Test

	void testInsertUser(){
		// 创建对象
		Gmsuser gmsuser = new Gmsuser();
		//赋值
		gmsuser.setUser_id("201911703429");
		gmsuser.setUser_name("45678");
		gmsuser.setUser_password("11111");
		gmsuser.setUser_admin("2");
		gmsuser.setUser_question(null);
		gmsuser.setState("在职");

		List<Gmsuser> list = testselectAll();

		list.forEach(user ->{
			if(user.getUser_name().equals(gmsuser.getUser_name()) && user.getUser_id().equals(gmsuser.getUser_id())){
//				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				b=true;
			};
		});
		if (!b) {
			//调用对应的方法
			int rows = userDao.insert(gmsuser);
			System.out.println("插入产生的影响行数" + rows);
		}else System.out.println("没插入");
    
	void test(){
//		System.out.println("time:"+(System.currentTimeMillis()/1000)+" DAO:"+matchDao);
		Timestamp a=new Timestamp(System.currentTimeMillis());
		Match match=new Match("5","naame5",  a,"placeasdf44","toolfsasfs4","typsae4","descrasfibe4","null","null",1,"1");
		System.out.println(match);
//		boolean r=matchDao.addMatch(match);
//		boolean r1=matchDao.addMatch(match);
//		System.out.println("result:"+r);
		List<Match> list =matchDao.queryMatch();
		System.out.println(list);
//		System.out.println("num:"+matchDao.queryMatchNum());
//		System.out.println(matchDao.queryReferee());
//		Referee referee=new Referee("2","/sdfsaf","namea","sadhf");
//		boolean r=matchDao.addReferee(referee);
//		System.out.println("result:"+r);

	}

	//删除用户(靠主键id)
	@Test

	void testDeleteUserById(){
		 /*调用对应的方法
       delete place SET place_storey=?, place_type=?, place_manageid=?, place_manage=?, place_state=? WHERE place_no=?
       更新非空字段
        */
//		int rows = userDao.deleteById(8);
//		System.out.println("插入产生的影响行数"+rows);

		//仿删除，其实是将用户状态改为离任
		Gmsuser gmsuser = new Gmsuser();
		gmsuser.setId(12);
		gmsuser.setState("离任");
		int rows = userDao.updateById(gmsuser);
		System.out.println("已修改");
	}
	//删除用户（靠其他字段）
	@Test
	void testDeleteUserByMap(){
		//创建Map,封装查询条件
		Map<String,Object> map = new HashMap<>();
		map.put("user_name","zzz");
		map.put("user_id",11701234);
		 /*调用对应的方法
       delete place SET place_storey=?, place_type=?, place_manageid=?, place_manage=?, place_state=? WHERE user_name=? AND user_id =?
       更新非空字段
        */
		int rows = userDao.deleteByMap(map);
		System.out.println("插入产生的影响行数"+rows);
	}

	//查找用户（仅靠主键id查询）
	@Test
	void testCheckUserById(){
		  /*
        调用产生的sql语句
        SELECT place_no ,place_storey ,place_type ,place_manageid
        ,place_manage ,place_state  FROM place WHERE place_no=?
         */
		Gmsuser gmsuser = userDao.selectById(2);
		System.out.println("得到的用户信息是"+gmsuser);
	}

	//查找全部用户
	//如果要单独测试这个方法，返回类型设为void
	@Test
	List<Gmsuser> testselectAll(){
		List<Gmsuser> list = userDao.selectList(null);
		list.forEach(user ->{
			System.out.println("用户:"+user);
		});
		return list;
	}
//	//查询可用的场地（可任意个其他条件查询）
//	@Test
//	public  void  testPlaceSelectMap(){
//
//		//创建Map,封装查询条件
//		Map<String,Object> map = new HashMap<>();
//		map.put("place_state","可使用");
//		map.put("place_manageid",1);
//        /*
//        对应产生的sql语句SELECT place_no,place_storey,place_type,place_manageid,place_manage,place_state
//         FROM place WHERE place_state = ? AND place_manageid = ?
//         */
//		List<Place> places = placeDao.selectByMap(map);
//		places.forEach(place -> {
//			System.out.println("符合条件的场地有"+place);
//		});
//	}

	//更新用户信息
	@Test
	void testUpdateUser(){

	}

	void test1(){
//		matchDao.updateMatchStatus(2,"2");
//		System.out.println(matchDao.queryMatchLimit(1,3));
		String a="足球,篮球,羽毛球,排球,棒球";
		String[] type=a.split(",");
		System.out.println(matchDao.queryMatchByCondition3(0,8,type));
	}

}
