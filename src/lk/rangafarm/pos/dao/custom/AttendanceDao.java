package lk.rangafarm.pos.dao.custom;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.entity.Attendance;

import java.util.List;

public interface AttendanceDao extends CrudDao<Attendance, String> {
    public int checkAbsentEmployee() throws Exception;

    public List<Attendance> getAll(String empId) throws Exception;
}
