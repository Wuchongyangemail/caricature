package com.w.volunteer.service;

import com.w.volunteer.pojo.common.School;
import com.w.volunteer.pojo.param.LoginParam;
import com.w.volunteer.pojo.param.SearchParam;
import com.w.volunteer.pojo.result.CollegeDto;
import com.w.volunteer.pojo.result.VerficationResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SchoolService {

    VerficationResult gainVerification(LoginParam loginParam);

}
