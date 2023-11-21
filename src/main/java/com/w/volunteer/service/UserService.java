package com.w.volunteer.service;

import com.w.volunteer.pojo.common.User;
import com.w.volunteer.pojo.param.UserParam;
import com.w.volunteer.pojo.result.Result;

public interface UserService {

    Integer create(UserParam userParam) throws Exception;

    User selectUser(UserParam userParam) throws Exception;

    Integer edit(UserParam userParam) throws Exception;
}
