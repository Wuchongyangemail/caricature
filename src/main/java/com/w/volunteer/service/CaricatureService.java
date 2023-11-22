package com.w.volunteer.service;

import com.w.volunteer.pojo.param.CaricatureParam;
import com.w.volunteer.pojo.param.CommentParam;

import java.util.List;

public interface CaricatureService {

    List<CaricatureParam> getPageList(CaricatureParam caricatureParam);

    Integer getPageListCount(CaricatureParam caricatureParam);

    void changeShow(CaricatureParam param);

    List<CommentParam> getDetailPageList(CommentParam commentParam);

    Integer getDetailPageCount(CommentParam commentParam);

    List<String> getPicDetailPageList(CommentParam commentParam);
}
