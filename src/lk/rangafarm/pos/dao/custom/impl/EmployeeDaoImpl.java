package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.EmployeeDao;
import lk.rangafarm.pos.entity.Employee;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{
    @Override
    public boolean save(Employee emp) throws Exception {
        return CrudUtil.execute("INSERT INTO Employee(empId, name, address, mobileNumber) VALUES (?, ?, ?, ?)",
                emp.getEmpId(),
                emp.getName(),
                emp.getAddress(),
                emp.getMobileNumber());
    }

    @Override
    public boolean update(Employee emp) throws Exception {
        return CrudUtil.execute("UPDATE Employee SET name = ?, address = ?, mobileNumber = ? " +
                "WHERE empId = ?",  emp.getName(), emp.getAddress(), emp.getMobileNumber(), emp.getEmpId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Employee WHERE empId = ?", s);
    }

    @Override
    public Employee search(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * from Employee WHERE empId = ?", s);

        while(set.next()){
              return new Employee(set.getString(1), set.getString(2),
                set.getString(3), set.getInt(4));
        }
        return null;
    }

    @Override
    public List<Employee> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Employee");
        ArrayList<Employee> employeeList = new ArrayList<>();

        while(set.next()){
            employeeList.add(new Employee(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getInt(4)
            ));
        }
        return employeeList;
    }

    @Override
    public ArrayList<String> getEmployeeId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT empId FROM Employee");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()){
            list.add(set.getString(1));

        }
        return list;
    }

    @Override
    public String getEmpId() throws Exception {
        /*ResultSet set = CrudUtil.execute("SELECT empId FROM Employee ORDER BY empId DESC LIMIT 1");

        String id = "E001";
        if(set.next()){
            String tempId = set.getString(1);
            String []cs = tempId.split("E");
            int selectedId = Integer.parseInt(cs[1]);
            if(selectedId < 10000){
                id = "E00" + (selectedId + 1);
            }
        }
        return id;*/
        ResultSet set = CrudUtil.
                execute("SELECT empId FROM Employee ORDER BY empId DESC LIMIT 1");
        String code="E001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("E");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                code="E00"+(selectedId+1);
            }else if(selectedId >= 9 && selectedId <99){
                code="E0"+(selectedId+1);
            }else if(selectedId >= 99){
                code="E"+(selectedId+1);
            }
        }
        return code;

    }
}
