package edu.gdou.matchmanege.dao;


import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.dao.MatchDao;
import edu.gdou.matchmanage.service.MatchService;
import edu.gdou.matchmanage.service.MatchServiceImp;
import edu.gdou.utilities.Access;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

@MapperScan("edu.gdou.matchmanage.dao")
public class MatchDaoTest {
    @Autowired
    private MatchService matchService;
    @Autowired
    private MatchDao matchDao;
    @Test
    public void Test(){
//        matchService=new MatchServiceImp();
//        System.out.println("Service:"+matchService);
//        System.out.println("DAO:"+matchDao);
//        Timestamp a=new Timestamp(1242153214);
//        Match match=new Match("3","name3", "a","place3","tools3","type3","describe3","null","null",1,"1");
//        boolean r=matchDao.addMatch(match);
//        System.out.println("result:"+r);
//        List<Match>list =matchDao.queryMatch();
//        System.out.println(list);
    }
    @Test
    public void test1(){
        Timestamp a=new Timestamp(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(a);
//        System.out.println(Access.getList());
    }
}
