package edu.gdou;

import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.bean.Referee;
import edu.gdou.matchmanage.dao.MatchDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class GmsApplicationTests {
	@Autowired
	private MatchDao matchDao;
	@Test
	void contextLoads() {

	}
	@Test
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
	@Test
	void test1(){
//		matchDao.updateMatchStatus(2,"2");
//		System.out.println(matchDao.queryMatchLimit(1,3));
		String a="足球,篮球,羽毛球,排球,棒球";
		String[] type=a.split(",");
		System.out.println(matchDao.queryMatchByCondition3(0,8,type));
	}
}
