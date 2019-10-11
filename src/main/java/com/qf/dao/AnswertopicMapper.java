package com.qf.dao;

import com.qf.entity.Answertopic;

import java.util.List;

public interface AnswertopicMapper {
    int deleteByPrimaryKey(Long answerId);

    int insert(Answertopic record);

    int insertSelective(Answertopic record);

    Answertopic selectByPrimaryKey(Long answerId);

    int updateByPrimaryKeySelective(Answertopic record);

    int updateByPrimaryKey(Answertopic record);

    List<Answertopic> findByTid(long tid);
}