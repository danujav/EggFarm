package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.AttendanceDto;
import lk.rangafarm.pos.dto.CageDto;

import java.util.ArrayList;
import java.util.List;

public interface AttendanceBo extends SuperBo {
    public boolean saveAttendance(AttendanceDto dto) throws Exception;

    public boolean updateAttendance(AttendanceDto dto) throws Exception;

    public List<AttendanceDto> getAllAttendance(String empId) throws Exception;

    public AttendanceDto getAttendance(String empId) throws Exception;

    public int checkAbsentEmployee() throws Exception;

}
