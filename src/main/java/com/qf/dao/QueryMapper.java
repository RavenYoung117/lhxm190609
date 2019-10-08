package com.qf.dao;

import com.qf.entity.Query;

public interface QueryMapper {
    int deleteByPrimaryKey(Long qId);

    int insert(Query record);

    int insertSelective(Query record);

    Query selectByPrimaryKey(Long qId);

    int updateByPrimaryKeySelective(Query record);

    int updateByPrimaryKey(Query record);
}