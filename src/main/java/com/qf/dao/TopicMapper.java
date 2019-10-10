package com.qf.dao;

import com.qf.entity.Topic;

import java.util.List;

public interface TopicMapper {
    int deleteByPrimaryKey(Long tId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Long tId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Topic> findByTitle(String topicWords);

    List<String> findType();

    List<Topic> findAllByType(String type);

    List<Topic> findByAnswer();
}