package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.UserDao;
import lk.rangafarm.pos.entity.Users;

import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(Users u) throws Exception {
        return CrudUtil.execute("INSERT INTO Users VALUES(?, ?, ?, md5(?), ?)",
                u.getUserName(),
                u.getName(),
                u.getEmail(),
                u.getPassword(),
                u.getRoleType());
    }

    @Override
    public boolean update(Users users) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Users search(String s) throws Exception {
        return null;
    }

    @Override
    public List<Users> getAll() throws Exception {
        return null;
    }

    @Override
    public Users search(String userName, String password) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Users WHERE (userName = ? || email = ? )  &&  password = md5(?)",
                userName, userName, password);
        while(set.next()){
            return new Users(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)
            );
        }
        return null;
    }
}
