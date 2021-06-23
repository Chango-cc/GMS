package edu.gdou.matchmanage.service;

import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.bean.Referee;
import edu.gdou.matchmanage.dao.MatchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImp implements MatchService{
    @Autowired
    private MatchDao matchDao;
    @Override
    public boolean addMatch(Match match) {
        match.setStatus("待审核");
        return matchDao.addMatch(match);
    }

    @Override
    public boolean deleteMatch(int id) {
        return matchDao.deleteMatch(id);
    }

    @Override
    public boolean updateMatch(Match match) {
        match.setStatus("待审核");
        return matchDao.updateMatch(match);
    }

    @Override
    public boolean updateMatchStatus(int id, String status) {
        status="已审核";
        return matchDao.updateMatchStatus(id,status);
    }

    @Override
    public int queryMatchNum() {
        return matchDao.queryMatchNum();
    }

    @Override
    public List<Match> queryMatch() {
        return matchDao.queryMatch();
    }

    @Override
    public Match queryMatchById(int id) {
        return matchDao.queryMatchById(id);
    }

    @Override
    public List<Match> queryMatchByUser(String id,int offset,int length) {
        return matchDao.queryMatchByUser(id,offset,length);
    }

    @Override
    public List<Match> queryMatchByStatus(String status) {
        return matchDao.queryMatchByStatus(status);
    }

    @Override
    public int queryMatchNumByCondition(String status, String type) {
        String[] types=type.split(",");
        if (status.equals("all"))
            return matchDao.queryMatchNumByCondition1(types);
        else return matchDao.queryMatchNumByCondition2(status,types);
    }
    @Override
    public List<Match> queryMatchByCondition(int offset, int length, String status, String type) {
        String[] types=type.split(",");
        if (status.equals("all")){
            List<Match> list=matchDao.queryMatchByCondition1(offset, length, types);
            return list;}
        else return matchDao.queryMatchByCondition2(offset, length, status, types);
    }

    @Override
    public List<Match> queryMatchLimit(int offset, int length) {
        return matchDao.queryMatchLimit(offset,length);
    }

    @Override
    public boolean addReferee(Referee referee) {
        return matchDao.addReferee(referee);
    }

    @Override
    public boolean deleteReferee(int id) {
        return matchDao.deleteReferee(id);
    }

    @Override
    public int queryRefereeNum() {
        return matchDao.queryRefereeNum();
    }

    @Override
    public List<Referee> queryReferee() {
        return matchDao.queryReferee();
    }

    @Override
    public List<Referee> queryRefereeByType(String type) {
        return matchDao.queryRefereeByType(type);
    }

    @Override
    public List<Referee> queryRefereeLimit(int offset, int length) {
        return matchDao.queryRefereeLimit(offset, length);
    }
}
