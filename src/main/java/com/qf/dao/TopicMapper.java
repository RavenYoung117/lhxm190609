package com.qf.dao;

import com.qf.entity.Topic;

public interface TopicMapper {
    int deleteByPrimaryKey(Long tId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Long tId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}