package com.qf.dao;

import com.qf.entity.Blog;

public interface BlogMapper {
    int deleteByPrimaryKey(Long bolgId);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long bolgId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);
}