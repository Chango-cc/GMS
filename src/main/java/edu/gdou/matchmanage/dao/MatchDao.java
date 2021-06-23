package edu.gdou.matchmanage.dao;

import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.bean.Referee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface MatchDao {
    boolean addMatch(Match match);
    boolean deleteMatch(Match match);
    boolean updateMatch(Match match);
    boolean updateMatchStatus(int id,String status);
    int queryMatchNum();
    List<Match> queryMatch();
    Match queryMatchById(int id);
    List<Match> queryMatchByUser(String id,int offset,int length);
    List<Match> queryMatchByStatus(String status);
    int queryMatchNumByCondition1(String[] type);
    int queryMatchNumByCondition2(String status,String[] type);
    List<Match> queryMatchByCondition1(int offset,int length,String[] type);
    List<Match> queryMatchByCondition2(int offset,int length,String status,String[] type);
    List<Match> queryMatchByCondition3(int offset,int length,String[] type);
    List<Match> queryMatchLimit(int offset,int length);
    //Referee
    boolean addReferee(Referee referee);
    boolean deleteReferee(int id);
    int queryRefereeNum();
    List<Referee> queryReferee();
    List<Referee> queryRefereeByType(String type);
    List<Referee> queryRefereeLimit(int offset,int length);

}
