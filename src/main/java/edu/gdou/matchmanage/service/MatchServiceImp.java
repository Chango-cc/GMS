package edu.gdou.matchmanage.service;

import edu.gdou.equipmentmanage.bean.Ereservation;
import edu.gdou.equipmentmanage.service.EquipmentService;
import edu.gdou.matchmanage.bean.Match;
import edu.gdou.matchmanage.bean.Referee;
import edu.gdou.matchmanage.dao.MatchDao;
import edu.gdou.placemange.service.PlaceApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class MatchServiceImp implements MatchService{
    @Autowired
    private MatchDao matchDao;
    @Autowired
    PlaceApplyService placeApplyService;
    @Autowired
    EquipmentService equipmentService;
    @Override
    public boolean addMatch(Match match) {
        match.setStatus("待审核");
        System.out.println("match:"+match);
        System.out.println(match.getEreservationList());
        boolean result=matchDao.addMatch(match);
        for (Ereservation e:match.getEreservationList()) {
            e.setUserid(match.getUserId());
            e.setMatchid(match.getMatchId());
            equipmentService.addEreservation(e);
            equipmentService.updateEquipmentByEno(e.getEno(),"租用中");
        }
        try {
            placeApplyService.PlaceApplyInserted(match.getKeepList(),match.getMatchId(),match.getUserId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteMatch(int id) {
        //取消场地预约，
        String matchId=id+"";
        placeApplyService.PlaceApplyStateUpdateMatch(matchId);
//        equipmentService.
        return matchDao.deleteMatch(id);
    }

    @Override
    public boolean updateMatch(Match match) {
        match.setStatus("待审核");
        return matchDao.updateMatch(match);
    }

    @Override
    public boolean updateMatchStatus(int id, String refereeId,String refereeName) {
        String status="已审核";
        return matchDao.updateMatchStatus(id,status,  refereeId, refereeName);
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
        Match match=matchDao.queryMatchById(id);
        match.setApplyList(placeApplyService.PlaceApplySelectMatch(match.getMatchId()));
        match.setEreservationList(equipmentService.queryEreservationByMatchId(match.getMatchId()));
        return match;
    }

    @Override
    public int queryMatchNumByUser(String id) {
        return matchDao.queryMatchNumByUser(id);
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
            return matchDao.queryMatchNumByConditionOne(types);
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
