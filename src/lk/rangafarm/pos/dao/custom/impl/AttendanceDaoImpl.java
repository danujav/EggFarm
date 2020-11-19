package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.AttendanceDao;
import lk.rangafarm.pos.entity.Attendance;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDaoImpl implements AttendanceDao {
    @Override
    public boolean save(Attendance a) throws Exception {
        return CrudUtil.execute("INSERT INTO Attendance VALUES (?, ?, ?, ?, ? )",
                a.getEmpId(), a.getDates(), a.getCheckIn(), a.getCheckOut(), a.getWorkingHours());
    }

    @Override
    public boolean update(Attendance a) throws Exception {
        return CrudUtil.execute("" +
                "UPDATE Attendance SET checkOut = ?, workingHour = timediff(?, ?) WHERE empId = ?",
                a.getCheckOut(), a.getCheckOut(), a.getCheckIn() ,a.getEmpId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Attendance search(String s) throws Exception {
        String dates = LocalDate.now().toString();
        ResultSet set = CrudUtil.execute("SELECT * FROM Attendance WHERE empId = ? && Dates = ?", s, dates);

        Attendance a = new Attendance();

        while(set.next()){
            return a = new Attendance(set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5));
        }
        return a;

    }

    @Override
    public List<Attendance> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Attendance");
        ArrayList<Attendance> attendances = new ArrayList();
        while(set.next()){
            attendances.add(new Attendance(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)
            ));
        }
        return attendances;
    }

    @Override
    public int checkAbsentEmployee() throws Exception {
        String date = LocalDate.now().toString();
        ResultSet set = CrudUtil.execute("SELECT count(checkIn) from Attendance WHERE " +
                "checkIn = 'Absent' && Dates = ?", date);

        int count = 0;

        while (set.next()){
            count = set.getInt(1);
        }
        return count;
    }

    @Override
    public List<Attendance> getAll(String empId) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Attendance WHERE empId = ?", empId);
        ArrayList<Attendance> attendances = new ArrayList();
        while(set.next()){
            attendances.add(new Attendance(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)
            ));
        }
        return attendances;
    }
}
