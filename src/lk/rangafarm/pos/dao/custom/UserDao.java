package lk.rangafarm.pos.dao.custom;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.entity.Users;

public interface UserDao extends CrudDao<Users, String> {
    public Users search(String userName, String password) throws Exception;
}
