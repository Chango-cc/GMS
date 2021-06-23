package edu.gdou.placemange.entity;

public class PlaceAvailable {

    private String placeNo;//申请的场地编号
    private String placeStorey;//申请的场地楼层
    private String placeType;//申请的场地类型
    private String applyDate;//申请的场地日期
    private String applyPeriod;//场地的使用时间断
    private String placeManage;//场地负责人

    public PlaceAvailable(String placeNo, String placeStorey, String placeType, String applyDate, String applyPeriod, String placeManage) {
        this.placeNo = placeNo;
        this.placeStorey = placeStorey;
        this.placeType = placeType;
        this.applyDate = applyDate;
        this.applyPeriod = applyPeriod;
        this.placeManage = placeManage;
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public String getPlaceStorey() {
        return placeStorey;
    }

    public void setPlaceStorey(String placeStorey) {
        this.placeStorey = placeStorey;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyPeriod() {
        return applyPeriod;
    }

    public void setApplyPeriod(String applyPeriod) {
        this.applyPeriod = applyPeriod;
    }

    public String getPlaceManage() {
        return placeManage;
    }

    public void setPlaceManage(String placeManage) {
        this.placeManage = placeManage;
    }

    @Override
    public String toString() {
        return "PlaceAvailable{" +
                "placeNo='" + placeNo + '\'' +
                ", placeStorey='" + placeStorey + '\'' +
                ", placeType='" + placeType + '\'' +
                ", applyDate=" + applyDate +
                ", applyPeriod='" + applyPeriod + '\'' +
                ", placeManage='" + placeManage + '\'' +
                '}';
    }
}
