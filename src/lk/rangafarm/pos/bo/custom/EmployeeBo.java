package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface EmployeeBo extends SuperBo {
    public boolean saveEmployee(EmployeeDto dto) throws Exception;

    public boolean UpdateEmployee(EmployeeDto dto) throws Exception;

    public EmployeeDto getEmployee(String id) throws Exception;

    public boolean deleteEmployee(String id) throws Exception;

    public List<EmployeeDto> getAllEmployee() throws Exception;

    public String getId() throws Exception;

    public ArrayList<String> getEmployeeName() throws Exception;
}
