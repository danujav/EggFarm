package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.AttendanceBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.QueryDao;
import lk.rangafarm.pos.dao.custom.AttendanceDao;
import lk.rangafarm.pos.dto.AttendanceDto;
import lk.rangafarm.pos.entity.Attendance;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBoImpl implements AttendanceBo {
    AttendanceDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ATTENDANCE);
    QueryDao qDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);
    @Override
    public boolean saveAttendance(AttendanceDto dto) throws Exception {
        return dao.save(new Attendance(
           dto.getEmpId(),
           dto.getDates(),
           dto.getCheckIn(),
           dto.getCheckOut(),
           dto.getWorkingHour()
        ));
    }

    @Override
    public boolean updateAttendance(AttendanceDto dto) throws Exception {
        return dao.update(new Attendance(
                dto.getEmpId(),
                dto.getDates(),
                dto.getCheckIn(),
                dto.getCheckOut(),
                dto.getWorkingHour()
        ));
    }

    @Override
    public List<AttendanceDto> getAllAttendance(String empId) throws Exception {
        List<Attendance> all = dao.getAll(empId);
        ArrayList<AttendanceDto> atList = new ArrayList<>();
        for(Attendance a : all){
            atList.add(new AttendanceDto(
                    a.getEmpId(), a.getDates(), a.getCheckIn(), a.getCheckOut(), a.getWorkingHours()
            ));
        }
        return atList;
    }

    @Override
    public AttendanceDto getAttendance(String empId) throws Exception {
        Attendance search = dao.search(empId);

        return new AttendanceDto(search.getEmpId(), search.getDates(),
                search.getCheckIn(), search.getCheckOut(), search.getWorkingHours());
    }

    @Override
    public int checkAbsentEmployee() throws Exception {
        return dao.checkAbsentEmployee();
    }
}
