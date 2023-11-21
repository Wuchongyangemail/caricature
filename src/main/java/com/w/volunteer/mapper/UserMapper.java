package com.w.volunteer.mapper;

import com.w.volunteer.pojo.common.User;
import com.w.volunteer.pojo.param.UserParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int create(UserParam userParam);

    User selectUser(UserParam userParam);

    int editUser(UserParam userParam);
}
