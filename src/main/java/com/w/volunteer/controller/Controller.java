package com.w.volunteer.controller;

import cn.hutool.core.collection.CollUtil;
import com.sun.media.jfxmedia.logging.Logger;
import com.w.volunteer.filter.JwtFilter;
import com.w.volunteer.pojo.common.Area;
import com.w.volunteer.pojo.common.School;
import com.w.volunteer.pojo.common.User;
import com.w.volunteer.pojo.importAndExport.ExcelUtils;
import com.w.volunteer.pojo.param.LoginParam;
import com.w.volunteer.pojo.param.SearchParam;
import com.w.volunteer.pojo.param.UserParam;
import com.w.volunteer.pojo.result.CollegeDto;
import com.w.volunteer.pojo.result.Result;
import com.w.volunteer.pojo.result.VerficationResult;
import com.w.volunteer.service.*;
import com.w.volunteer.util.AESUtil;
import com.w.volunteer.util.JwtUtils;
import com.w.volunteer.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/9 下午10:06
 **/
@RestController
@RequestMapping("/controller")
public class Controller {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private UserService userService;

    @PostMapping("/gainVerification")
    public VerficationResult gainVerification(@RequestBody LoginParam loginParam) {
        return schoolService.gainVerification(loginParam);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserParam userParam) {
        Result result = new Result();
        try {
            processUser(userParam);
            Integer i = userService.create(userParam);
            result.setCode("200");
            result.setMsg("创建成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage());
            result.setCode("500");
        }
        return result;
    }

    @PostMapping("/editUser")
    public Result editUser(@RequestBody UserParam userParam) {
        Result result = new Result();
        try {
            processUser(userParam);
            Integer edit = userService.edit(userParam);
            if (!ObjectUtils.isEmpty(edit) && edit > 0) {
                result.setMsg("更新成功！");
                result.setCode("200");
            } else {
                result.setMsg("更新失败！");
                result.setCode("500");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("更新失败！");
            result.setCode("500");
        }
        return result;
    }

    @PostMapping("/searchAuthData")
    public Result searchAuthData(ServletRequest request) {
        Result result = new Result();
        try {
            Object username = request.getAttribute("username");
            String[] split = username.toString().split("\\$¥");
            UserParam userParam = new UserParam();
            userParam.setUserName(split[0]);
            userParam.setPassword(AESUtil.encrypt(split[1]));
            User user = userService.searchAuthData(userParam);
            if (ObjectUtils.isEmpty(user)) {
                ResultUtil.addCodeAndMsg(result, "500", "查询失败！");
                return result;
            }
            user.setPassword(AESUtil.decrypt(user.getPassword()));
            result.setResults(user);
            ResultUtil.addCodeAndMsg(result, "200", "查询成功！");
        } catch(Exception e) {
            e.printStackTrace();
            ResultUtil.addCodeAndMsg(result, "500", "查询失败！");
        }
        return result;
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserParam userParam) {
        Result result = new Result();
        try {
            processUser(userParam);
            User user = userService.selectUser(userParam);
            if (!ObjectUtils.isEmpty(user)) {
                user.setPassword(AESUtil.decrypt(user.getPassword()));
                result.setToken(JwtUtils.generateToken(user.getUserName() + "$¥" + user.getPassword()));
                result.setCode("200");
                result.setMsg("登陆成功！");
            } else {
                result.setCode("500");
                result.setMsg("账号或密码输入错误！");
            }
        } catch (Exception e) {
            result.setCode("500");
            result.setMsg("登陆失败！");
            e.printStackTrace();
        }
        return result;
    }

    private void processUser(UserParam user) throws Exception {
        user.setPassword(AESUtil.encrypt(user.getPassword()));
    }
}
