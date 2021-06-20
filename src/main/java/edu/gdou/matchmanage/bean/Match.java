package edu.gdou.matchmanage.bean;

import java.sql.Timestamp;

public class Match {
    String matchId;
    String matchName;
    Timestamp matchTime;
    String matchPlace;
    String matchTools;
    String matchType;
    String matchDescribe;
    String userId;
    String reviewerId;
    int refereeId;
    String status;

    public Match(String matchId, String matchName, Timestamp matchTime, String matchPlace, String matchTools, String matchType, String matchDescribe, String userId, String reviewerId, int refereeId, String status) {
        this.matchId = matchId;
        this.matchName = matchName;
        this.matchTime = matchTime;
        this.matchPlace = matchPlace;
        this.matchTools = matchTools;
        this.matchType = matchType;
        this.matchDescribe = matchDescribe;
        this.userId = userId;
        this.reviewerId = reviewerId;
        this.refereeId = refereeId;
        this.status = status;
    }

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

    public Timestamp getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Timestamp matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchPlace() {
        return matchPlace;
    }

    public void setMatchPlace(String matchPlace) {
        this.matchPlace = matchPlace;
    }

    public String getMatchTools() {
        return matchTools;
    }

    public void setMatchTools(String matchTools) {
        this.matchTools = matchTools;
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

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", matchName='" + matchName + '\'' +
                ", matchTime=" + matchTime +
                ", matchPlace='" + matchPlace + '\'' +
                ", matchTools='" + matchTools + '\'' +
                ", matchType='" + matchType + '\'' +
                ", matchDescribe='" + matchDescribe + '\'' +
                ", userId='" + userId + '\'' +
                ", reviewerId='" + reviewerId + '\'' +
                ", refereeId=" + refereeId +
                ", status='" + status + '\'' +
                '}';
    }
}
