package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.CustomerDao;
import lk.rangafarm.pos.entity.Attendance;
import lk.rangafarm.pos.entity.Customer;
import lk.rangafarm.pos.entity.Product;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.execute("INSERT INTO Customer VALUES (?, ?, ?, ?, ?)",
                customer.getCustId(),
                customer.getName(),
                customer.getShopName(),
                customer.getAddress(),
                customer.getMobileNumber());
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET name = ?, " +
                "shopName = ?, address = ?, mobileNumber = ? WHERE custId = ?",
                customer.getName(),
                customer.getShopName(),
                customer.getAddress(),
                customer.getMobileNumber(),
                customer.getCustId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Customer search(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer WHERE custId = ?", s);

        while(set.next()){
            return new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getInt(5));
        }
        return null;

    }

    @Override
    public List<Customer> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while(set.next()){
            customers.add(new Customer(
               set.getString(1),
               set.getString(2),
               set.getString(3),
               set.getString(4),
               set.getInt(5)
            ));
        }
        return customers;
    }

    @Override
    public ArrayList<String> getCustomerId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT custId FROM Customer");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()){
            list.add(set.getString(1));

        }
        return list;
    }

    @Override
    public int getCustomerCount() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT COUNT(*) FROM Customer");
        int count = 0;
        while(set.next()){
            count = set.getInt(1);
        }
        return count;
    }

    @Override
    public String getId() throws Exception {
        /*ResultSet set = CrudUtil.execute("SELECT custId FROM Customer ORDER BY custId DESC LIMIT 1");

        String id = "C001";
        if(set.next()){
            String tempId = set.getString(1);
            String []cs = tempId.split("C");
            int selectedId = Integer.parseInt(cs[1]);
            if(selectedId < 10000){
                id = "C00" + (selectedId + 1);
            }
        }
        return id;*/

        ResultSet set = CrudUtil.
                execute("SELECT custId FROM Customer ORDER BY custId DESC LIMIT 1");
        String code="C001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("C");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                code="C00"+(selectedId+1);
            }else if(selectedId >= 9 && selectedId <99){
                code="C0"+(selectedId+1);
            }else if(selectedId >= 99){
                code="C"+(selectedId+1);
            }
        }
        return code;
    }
}
