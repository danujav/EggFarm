package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.UserDto;

public interface UserBo extends SuperBo {
    public boolean signUp(UserDto dto) throws Exception;

    public UserDto logIn(String userName, String password) throws Exception;
}
