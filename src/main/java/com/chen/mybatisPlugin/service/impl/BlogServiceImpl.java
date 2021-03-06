package com.chen.mybatisPlugin.service.impl;

import com.chen.mybatisPlugin.annotation.TimeCaculate;
import com.chen.mybatisPlugin.dao.mapping.BlogDao;
import com.chen.mybatisPlugin.model.Blog;
import com.chen.mybatisPlugin.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 陈俊宏
 */
@Service
public class BlogServiceImpl implements BlogService {

    final BlogDao dao;

    @Autowired
    public BlogServiceImpl(BlogDao dao) {
        this.dao = dao;
    }

    @Override
    @TimeCaculate
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    @TimeCaculate
    public int insert(Blog record) {
        return dao.insert(record);
    }

    @Override
    @TimeCaculate
    public int insertSelective(Blog record) {
       return dao.insertSelective(record);
    }

    @Override
    @TimeCaculate
    public Blog selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    @TimeCaculate
    public int updateByPrimaryKeySelective(Blog record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    @TimeCaculate
    public int updateByPrimaryKey(Blog record) {
        return dao.updateByPrimaryKey(record);
    }
}
