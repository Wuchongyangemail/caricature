package com.w.volunteer.controller;

import com.w.volunteer.pojo.param.CaricatureParam;
import com.w.volunteer.pojo.param.CommentParam;
import com.w.volunteer.pojo.result.Result;
import com.w.volunteer.service.CaricatureService;
import com.w.volunteer.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/caricature")
public class CaricatureController {

    @Autowired
    private CaricatureService caricatureService;

    @PostMapping("/getPageList")
    public Result getPageList(@RequestBody CaricatureParam caricatureParam) {
        Result result = new Result();
        try {
            processData(caricatureParam);
            //查询分页数据
            List<CaricatureParam> pageList = caricatureService.getPageList(caricatureParam);
            Integer pageListCount = caricatureService.getPageListCount(caricatureParam);
            result.setResults(pageList);
            result.setTotal(pageListCount);
            ResultUtil.addCodeAndMsg(result, "200", "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.addCodeAndMsg(result, "500", "查询失败！");
        }
        return result;
    }

    @PostMapping("/isShow")
    public Result changeShow(@RequestBody CaricatureParam caricatureParam) {
        Result result = new Result();
        try {
            caricatureService.changeShow(caricatureParam);
            ResultUtil.addCodeAndMsg(result, "200", "状态已修改！");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.addCodeAndMsg(result, "500", "状态修改失败！");
        }
        return result;
    }

    @PostMapping("/searchDetail")
    public Result searchDetail(@RequestBody CommentParam commentParam) {
        Result result = new Result();
        try {
            processDetailData(commentParam);
            result.setResults(caricatureService.getDetailPageList(commentParam));
            result.setTotal(caricatureService.getDetailPageCount(commentParam));
            ResultUtil.addCodeAndMsg(result, "200", "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.addCodeAndMsg(result, "500", "查询失败！");
        }
        return result;
    }

    @PostMapping("/searchPicDetail")
    public Result searchPicDetail(@RequestBody CommentParam commentParam) {
        Result result = new Result();
        try {
            result.setResults(caricatureService.getPicDetailPageList(commentParam));
            ResultUtil.addCodeAndMsg(result, "200", "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.addCodeAndMsg(result, "500", "查询失败！");
        }
        return result;
    }

    private void processData(CaricatureParam caricatureParam) {
        caricatureParam.setCount((caricatureParam.getPage() - 1) * caricatureParam.getLimit());
    }

    private void processDetailData(CommentParam commentParam) {
        commentParam.setCount((commentParam.getPage() - 1) * commentParam.getLimit());
    }
}
