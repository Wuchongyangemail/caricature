package com.w.volunteer.serviceImpl;

import com.w.volunteer.mapper.CaricatureMapper;
import com.w.volunteer.pojo.param.CaricatureParam;
import com.w.volunteer.pojo.param.CommentParam;
import com.w.volunteer.service.CaricatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaricatureServiceImpl implements CaricatureService {

    @Autowired
    private CaricatureMapper caricatureMapper;

    @Override
    public List<CaricatureParam> getPageList(CaricatureParam caricatureParam) {
        return caricatureMapper.getPageList(caricatureParam);
    }

    @Override
    public Integer getPageListCount(CaricatureParam caricatureParam) {
        return caricatureMapper.getPageListCount(caricatureParam);
    }

    @Override
    public void changeShow(CaricatureParam param) {
        caricatureMapper.changeShow(param);
    }

    @Override
    public List<CommentParam> getDetailPageList(CommentParam commentParam) {
        return caricatureMapper.getDetailPageList(commentParam);
    }

    @Override
    public Integer getDetailPageCount(CommentParam commentParam) {
        return caricatureMapper.getDetailPageCount(commentParam);
    }

    @Override
    public List<String> getPicDetailPageList(CommentParam commentParam) {
        return caricatureMapper.getPicDetailPageList(commentParam);
    }
}
