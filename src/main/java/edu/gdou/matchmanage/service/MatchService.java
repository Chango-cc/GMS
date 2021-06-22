package edu.gdou.matchmanage.service;

import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.bean.Referee;

import java.util.List;

public interface MatchService {
    boolean addMatch(Match match);
    boolean deleteMatch(Match match);
    int queryMatchNum();
    List<Match> queryMatch();
    Match queryMatchById(int id);
    List<Match> queryMatchByUser(String id,int offset,int length);
    List<Match> queryMatchByStatus(String status);
    int queryMatchNumByCondition(String status, String type);
    List<Match> queryMatchByCondition(int offset,int length,String status,String type);
    List<Match> queryMatchLimit(int offset,int length);

    boolean addReferee(Referee referee);
    boolean deleteReferee(int id);
    int queryRefereeNum();
    List<Referee> queryReferee();
    List<Referee> queryRefereeLimit(int offset,int length);
}
