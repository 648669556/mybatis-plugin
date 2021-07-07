package com.chen.mybatisPlugin.dao.mapping;

import com.chen.mybatisPlugin.model.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 陈俊宏
 */
@Mapper
public interface BlogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);


}