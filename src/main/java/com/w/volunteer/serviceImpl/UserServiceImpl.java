package com.w.volunteer.serviceImpl;

import com.w.volunteer.mapper.UserMapper;
import com.w.volunteer.pojo.common.User;
import com.w.volunteer.pojo.param.UserParam;
import com.w.volunteer.pojo.result.Result;
import com.w.volunteer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer create(UserParam userParam) throws Exception {
        int i = userMapper.create(userParam);
        return i;
    }

    @Override
    public User selectUser(UserParam userParam) throws Exception {
        return userMapper.selectUser(userParam);
    }

    @Override
    public Integer edit(UserParam userParam) throws Exception {
        return userMapper.editUser(userParam);
    }
}
