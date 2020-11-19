package lk.rangafarm.pos.dao;

import lk.rangafarm.pos.bo.custom.impl.AttendanceBoImpl;
import lk.rangafarm.pos.dao.custom.EmployeeDao;
import lk.rangafarm.pos.dao.custom.impl.*;
import lk.rangafarm.pos.entity.Product;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DaoFactory() : daoFactory;
    }

    public enum DaoType{
        CUSTOMER, QUERY, EMPLOYEE, CAGE, PRODUCT, EGGBUCKET, EGGBUCKETDETAIL, ATTENDANCE, ORDER, ORDERDETAIL, USERS
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case QUERY:
                return (T) new QueryDaoImpl();
            case EMPLOYEE:
                return (T) new EmployeeDaoImpl();
            case CAGE:
                return (T) new CageDaoImpl();
            case PRODUCT:
                return (T) new ProductDaoImpl();
            case EGGBUCKET:
                return (T) new EggBucketDaoImpl();
            case EGGBUCKETDETAIL:
                return (T) new EggBucketDetailDaoImpl();
            case ATTENDANCE:
                return (T) new AttendanceDaoImpl();
            case ORDER:
                return (T) new OrderDaoImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailDaoImpl();
            case USERS:
                return (T) new UserDaoImpl();
            default:
                return null;
        }
    }
}
