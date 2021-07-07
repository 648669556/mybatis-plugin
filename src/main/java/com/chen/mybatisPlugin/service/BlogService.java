package com.chen.mybatisPlugin.service;

import com.chen.mybatisPlugin.model.Blog;

public interface BlogService {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);
}
