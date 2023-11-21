package com.w.volunteer.mapper;

import com.w.volunteer.pojo.param.CaricatureParam;
import com.w.volunteer.pojo.param.CommentParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaricatureMapper {

    List<CaricatureParam> getPageList(CaricatureParam caricatureParam);

    Integer getPageListCount(CaricatureParam caricatureParam);

    void changeShow(CaricatureParam caricatureParam);

    List<CommentParam> getDetailPageList(CommentParam commentParam);

    Integer getDetailPageCount(CommentParam commentParam);

    List<String> getPicDetailPageList(CommentParam commentParam);

}
