package edu.gdou.matchmanage.bean;

import edu.gdou.equipmentmanage.bean.Ereservation;
import edu.gdou.placemange.entity.PlaceApply;
import edu.gdou.placemange.entity.PlaceAvailable;

import java.sql.Timestamp;
import java.util.List;

public class Match {
    String matchId;
    String matchName;
    String matchUsername;//赛事申请人名字
    String matchRefereename;//裁判
    String matchReviewername;//赛事审核人名字
    String matchType;
    String matchDescribe;
    String userId;
    String reviewerId;
    int refereeId;
    String status;
    List<PlaceAvailable> keepList;
    List<PlaceApply> applyList;
    List<Ereservation> ereservationList;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchUsername() {
        return matchUsername;
    }

    public void setMatchUsername(String matchUsername) {
        this.matchUsername = matchUsername;
    }

    public String getMatchRefereename() {
        return matchRefereename;
    }

    public void setMatchRefereename(String matchRefereename) {
        this.matchRefereename = matchRefereename;
    }

    public String getMatchReviewername() {
        return matchReviewername;
    }

    public void setMatchReviewername(String matchReviewername) {
        this.matchReviewername = matchReviewername;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchDescribe() {
        return matchDescribe;
    }

    public void setMatchDescribe(String matchDescribe) {
        this.matchDescribe = matchDescribe;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public int getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(int refereeId) {
        this.refereeId = refereeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PlaceAvailable> getKeepList() {
        return keepList;
    }

    public void setKeepList(List<PlaceAvailable> keepList) {
        this.keepList = keepList;
    }

    public List<PlaceApply> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<PlaceApply> applyList) {
        this.applyList = applyList;
    }

    public List<Ereservation> getEreservationList() {
        return ereservationList;
    }

    public void setEreservationList(List<Ereservation> ereservationList) {
        this.ereservationList = ereservationList;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", matchName='" + matchName + '\'' +
                ", matchUsername='" + matchUsername + '\'' +
                ", matchRefereename='" + matchRefereename + '\'' +
                ", matchReviewername='" + matchReviewername + '\'' +
                ", matchType='" + matchType + '\'' +
                ", matchDescribe='" + matchDescribe + '\'' +
                ", userId='" + userId + '\'' +
                ", reviewerId='" + reviewerId + '\'' +
                ", refereeId=" + refereeId +
                ", status='" + status + '\'' +
                ", keepList=" + keepList +
                ", applyList=" + applyList +
                ", ereservationList=" + ereservationList +
                '}';
    }
}
