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
    List<Match> queryMatchByUser(int id);
    List<Match> queryMatchByStatus(String status);
    List<Match> queryMatchLimit(int offset,int length);
    //Referee
    boolean addReferee(Referee referee);
    boolean deleteReferee(int id);
    int queryRefereeNum();
    List<Referee> queryReferee();
    List<Referee> queryRefereeLimit(int offset,int length);

}
