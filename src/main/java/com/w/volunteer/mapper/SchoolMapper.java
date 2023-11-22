package com.w.volunteer.mapper;

import com.w.volunteer.pojo.common.School;
import com.w.volunteer.pojo.common.User;
import com.w.volunteer.pojo.common.Verification;
import com.w.volunteer.pojo.param.LoginParam;
import com.w.volunteer.pojo.param.SearchParam;
import com.w.volunteer.pojo.result.CollegeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolMapper {
    School findById(@Param("id") Long id);

    int create(School school);

    User findByUserCode(@Param("userName") String userCode);

    Verification findByLoginParam(LoginParam loginParam);

    int createVerification(Verification verification);

    int createUser(User user);
}
