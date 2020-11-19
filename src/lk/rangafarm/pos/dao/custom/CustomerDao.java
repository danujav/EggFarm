package lk.rangafarm.pos.dao.custom;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.entity.Customer;

import java.util.ArrayList;

public interface CustomerDao extends CrudDao<Customer, String> {
    public ArrayList<String> getCustomerId() throws Exception;

    public int getCustomerCount() throws Exception;

    public String getId() throws Exception;
}
