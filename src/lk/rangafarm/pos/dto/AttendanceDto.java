package lk.rangafarm.pos.dto;

public class AttendanceDto {
    private String empId;
    private String dates;
    private String checkIn;
    private String checkOut;
    private String workingHour;

    public AttendanceDto() {
    }

    public AttendanceDto(String empId, String dates, String checkIn, String checkOut, String workingHour) {
        this.setEmpId(empId);
        this.setDates(dates);
        this.setCheckIn(checkIn);
        this.setCheckOut(checkOut);
        this.setWorkingHour(workingHour);
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

    public String getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(String workingHour) {
        this.workingHour = workingHour;
    }

    @Override
    public String toString() {
        return "AttendanceDto{" +
                "empId='" + empId + '\'' +
                ", dates='" + dates + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", workingHour='" + workingHour + '\'' +
                '}';
    }
}
