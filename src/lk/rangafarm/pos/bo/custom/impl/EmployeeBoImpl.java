package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.EmployeeBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.QueryDao;
import lk.rangafarm.pos.dao.custom.EmployeeDao;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.dto.EmployeeDto;
import lk.rangafarm.pos.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EmployeeBoImpl implements EmployeeBo {
    EmployeeDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);
    QueryDao qDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws Exception {
        return dao.save(new Employee(
                dto.getEmpId(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobileNumber()
        ));
    }

    @Override
    public boolean UpdateEmployee(EmployeeDto dto) throws Exception {
        return dao.update(new Employee(
                dto.getEmpId(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobileNumber()
        ));
    }

    @Override
    public EmployeeDto getEmployee(String id) throws Exception {
        Employee s = dao.search(id);
        return new EmployeeDto(s.getEmpId(), s.getName(), s.getAddress(), s.getMobileNumber());
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() throws Exception {
        List<Employee> all = dao.getAll();
        ArrayList<EmployeeDto> dtoList = new ArrayList<>();
        for(Employee e : all){
            dtoList.add(new EmployeeDto(
               e.getEmpId(),
               e.getName(),
               e.getAddress(),
               e.getMobileNumber()
            ));
        }
        return dtoList;
    }

    @Override
    public String getId() throws Exception {
        return dao.getEmpId();
    }

    @Override
    public ArrayList<String> getEmployeeName() throws Exception {
        return dao.getEmployeeId();
    }
}
