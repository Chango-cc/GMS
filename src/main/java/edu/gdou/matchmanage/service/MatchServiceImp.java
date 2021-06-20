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
        return matchDao.addMatch(match);
    }

    @Override
    public boolean deleteMatch(Match match) {
        return matchDao.deleteMatch(match);
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
    public List<Match> queryMatchByUser(int id) {
        return matchDao.queryMatchByUser(id);
    }

    @Override
    public List<Match> queryMatchByStatus(String status) {
        return matchDao.queryMatchByStatus(status);
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
    public List<Referee> queryRefereeLimit(int offset, int length) {
        return matchDao.queryRefereeLimit(offset, length);
    }
}
