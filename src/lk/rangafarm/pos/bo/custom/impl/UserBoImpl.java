package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.UserBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.custom.UserDao;
import lk.rangafarm.pos.dto.UserDto;
import lk.rangafarm.pos.entity.Users;

public class UserBoImpl implements UserBo {
    UserDao uDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.USERS);

    @Override
    public boolean signUp(UserDto dto) throws Exception {
        return uDao.save(new Users(
                dto.getUserName(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRoleType()
        ));
    }

    @Override
    public UserDto logIn(String userName, String password) throws Exception {
        Users s = uDao.search(userName, password);
        return new UserDto(
                s.getUserName(),
                s.getName(),
                s.getEmail(),
                s.getPassword(),
                s.getRoleType()
        );

    }
}
