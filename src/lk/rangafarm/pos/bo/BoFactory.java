package lk.rangafarm.pos.bo;

import lk.rangafarm.pos.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return (null == boFactory) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoType{
        CUSTOMER, EMPLOYEE, CAGE, PRODUCT, EGGBUCKET, EGGBUCKETDETAIL, ATTENDANCE, ADDEGG, ORDER, SELLING, ORDERDETAIL,
        USER
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch ((type)){
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case EMPLOYEE:
                return (T) new EmployeeBoImpl();
            case CAGE:
                return (T) new CageBoImpl();
            case PRODUCT:
                return (T) new ProductBoImpl();
            case EGGBUCKET:
                return (T) new EggBucketBoImpl();
            case EGGBUCKETDETAIL:
                return (T) new EggBucketDetailBoImpl();
            case ATTENDANCE:
                return (T) new AttendanceBoImpl();
            case ADDEGG:
                return (T) new AddEggBoImpl();
            case ORDER:
                return (T) new OrderBoImpl();
            case SELLING:
                return (T) new SellingBoImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailBoImpl();
            case USER:
                return (T) new UserBoImpl();
            default:
                return null;
        }
    }
}
