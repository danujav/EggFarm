package lk.rangafarm.pos.entity;

public class Attendance implements SuperEntity{
    private String empId;
    private String dates;
    private String checkIn;
    private String checkOut;
    private String workingHours;

    public Attendance() {
    }

    public Attendance(String empId, String dates, String checkIn, String checkOut, String workingHours) {
        this.setEmpId(empId);
        this.setDates(dates);
        this.setCheckIn(checkIn);
        this.setCheckOut(checkOut);
        this.setWorkingHours(workingHours);
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

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "empId='" + empId + '\'' +
                ", dates='" + dates + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", workingHours='" + workingHours + '\'' +
                '}';
    }
}
