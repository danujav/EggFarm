package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;

public interface CustomerBo extends SuperBo {
    public boolean saveCustomer(CustomerDto dto) throws Exception;

    public boolean UpdateCustomer(CustomerDto dto) throws Exception;

    public CustomerDto getCustomer(String id) throws Exception;

    public List<CustomerDto> getAllCustomer() throws Exception;

    public String getId() throws Exception;

    public int getCustomerCount() throws Exception;

    public ArrayList<String> getCustomerId() throws Exception;
}
