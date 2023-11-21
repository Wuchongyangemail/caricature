package com.w.volunteer.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.w.volunteer.mapper.SchoolMapper;
import com.w.volunteer.pojo.param.LoginParam;
import com.w.volunteer.pojo.result.VerficationResult;
import com.w.volunteer.service.SchoolService;
import com.w.volunteer.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/9 下午10:05
 **/
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public VerficationResult gainVerification(LoginParam loginParam) {
        VerficationResult result = new VerficationResult();
//        Verification loginParam1 = schoolMapper.findByLoginParam(loginParam);
//        if (!ObjectUtil.isNull(loginParam1)) {
//            Date createTime = loginParam1.getCreateTime();
//            if (((new Date()).getTime() - createTime.getTime()) <= 60000) {
//                result.setResultStatus(0);
//                result.setResultMessage("请一分钟后尝试再次发送");
//                return result;
//            }
//        }
//        Verification verification = new Verification();
//        verification.setCreateTime(new Date());
//        verification.setUsercode(loginParam.getUserCode());
//        verification.setPassword(loginParam.getPassword());
//        verification.setEmail(loginParam.getEmail());
        String s = RandomUtil.randomString(6);
//        verification.setCode(s);
//        schoolMapper.createVerification(verification);
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("本次注册验证码: ");
        stringBuffer.append(s);
        emailUtil.sendMessage(loginParam.getEmail(), "志愿预测填报系统注册验证", StrUtil.toString(stringBuffer));
        result.setResultStatus(1);
        result.setResultMessage("发送成功，请注意接收邮件");
        return result;
    }
}
