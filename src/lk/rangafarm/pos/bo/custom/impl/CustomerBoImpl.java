package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.CustomerBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.QueryDao;
import lk.rangafarm.pos.dao.custom.CustomerDao;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.dto.ProductDto;
import lk.rangafarm.pos.entity.Customer;
import lk.rangafarm.pos.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CUSTOMER);
    QueryDao qDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws Exception {
        return dao.save(new Customer(
                    dto.getCustId(),
                    dto.getName(),
                    dto.getShopName(),
                    dto.getAddress(),
                    dto.getMobileNumber()));
    }

    @Override
    public boolean UpdateCustomer(CustomerDto dto) throws Exception {
        return dao.update(new Customer(
                dto.getCustId(),
                dto.getName(),
                dto.getShopName(),
                dto.getAddress(),
                dto.getMobileNumber()
        ));
    }

    @Override
    public CustomerDto getCustomer(String id) throws Exception {
        Customer search = dao.search(id);
        return new CustomerDto(
                search.getCustId(),
                search.getName(),
                search.getShopName(),
                search.getAddress(),
                search.getMobileNumber()
        );
    }

    @Override
    public List<CustomerDto> getAllCustomer() throws Exception {
        List<Customer> all = dao.getAll();
        ArrayList<CustomerDto> dtoList = new ArrayList<>();
        for(Customer c : all){
            dtoList.add(new CustomerDto(
                    c.getCustId(),
                    c.getName(),
                    c.getShopName(),
                    c.getAddress(),
                    c.getMobileNumber()
            ));
        }
        return dtoList;
    }

    @Override
    public String getId() throws Exception {
        return dao.getId();
    }

    @Override
    public int getCustomerCount() throws Exception {
        return dao.getCustomerCount();
    }

    @Override
    public ArrayList<String> getCustomerId() throws Exception {
        return dao.getCustomerId();
    }

}
