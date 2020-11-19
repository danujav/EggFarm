package lk.rangafarm.pos.dao.custom;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.entity.Employee;

import java.util.ArrayList;

public interface EmployeeDao extends CrudDao<Employee, String>{
    public ArrayList<String> getEmployeeId() throws Exception;

    public String getEmpId() throws Exception;
}
