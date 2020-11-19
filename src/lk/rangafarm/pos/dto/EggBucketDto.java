package lk.rangafarm.pos.dto;

public class EggBucketDto {
    private String eggBucketId;
    private String cageId;
    private String empId;
    private String dates;
    private String times;
    private int qty;

    public EggBucketDto() {
    }

    public EggBucketDto(String eggBucketId, String cageId, String empId, String dates, String times, int qty) {
        this.setEggBucketId(eggBucketId);
        this.setCageId(cageId);
        this.setEmpId(empId);
        this.setDates(dates);
        this.setTimes(times);
        this.setQty(qty);
    }

    public String getEggBucketId() {
        return eggBucketId;
    }

    public void setEggBucketId(String eggBucketId) {
        this.eggBucketId = eggBucketId;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "EggBucketDto{" +
                "eggBucketId='" + eggBucketId + '\'' +
                ", cageId='" + cageId + '\'' +
                ", empId='" + empId + '\'' +
                ", dates='" + dates + '\'' +
                ", times='" + times + '\'' +
                ", qty=" + qty +
                '}';
    }
}
