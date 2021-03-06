package edu.gdou.matchmanage.service;

import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.bean.Referee;

import java.util.List;

public interface MatchService {
    boolean addMatch(Match match);
    boolean deleteMatch(int id);
    boolean updateMatch(Match match);
    boolean updateMatchStatus(int id,String refereeId,String refereeName);
    int queryMatchNum();
    List<Match> queryMatch();
    Match queryMatchById(int id);
    int queryMatchNumByUser(String id);
    List<Match> queryMatchByUser(String id,int offset,int length);
    List<Match> queryMatchByStatus(String status);
    int queryMatchNumByCondition(String status, String type);
    List<Match> queryMatchByCondition(int offset,int length,String status,String type);
    List<Match> queryMatchLimit(int offset,int length);

    boolean addReferee(Referee referee);
    boolean deleteReferee(int id);
    int queryRefereeNum();
    List<Referee> queryReferee();
    List<Referee> queryRefereeByType(String type);
    List<Referee> queryRefereeLimit(int offset,int length);
}
