package lk.rangafarm.pos.view.tm;

import lk.rangafarm.pos.entity.SuperEntity;

public class AttendanceTM implements SuperEntity {
    private String date;
    private String checkIn;
    private String checkOut;
    private String workingHour;

    public AttendanceTM() {
    }

    public AttendanceTM(String date, String checkIn, String checkOut, String workingHour) {
        this.setDate(date);
        this.setCheckIn(checkIn);
        this.setCheckOut(checkOut);
        this.setWorkingHour(workingHour);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
