package edu.gdou.matchmanage.bean;

public class Referee {
    String refereeId;
    String refereePhoto;
    String refereeName;
    String refereeDescribe;

    public Referee(String refereeId, String refereePhoto, String refereeName, String refereeDescribe) {
        this.refereeId = refereeId;
        this.refereePhoto = refereePhoto;
        this.refereeName = refereeName;
        this.refereeDescribe = refereeDescribe;
    }

    public String getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }

    public String getRefereePhoto() {
        return refereePhoto;
    }

    public void setRefereePhoto(String refereePhoto) {
        this.refereePhoto = refereePhoto;
    }

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public String getRefereeDescribe() {
        return refereeDescribe;
    }

    public void setRefereeDescribe(String refereeDescribe) {
        this.refereeDescribe = refereeDescribe;
    }

    @Override
    public String toString() {
        return "Referee{" +
                "refereeId='" + refereeId + '\'' +
                ", refereePhoto='" + refereePhoto + '\'' +
                ", refereeName='" + refereeName + '\'' +
                ", refereeDescribe='" + refereeDescribe + '\'' +
                '}';
    }
}
