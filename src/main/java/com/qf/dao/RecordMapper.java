package com.qf.dao;

import com.qf.entity.Record;

public interface RecordMapper {
    int deleteByPrimaryKey(Long tId);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Long tId);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
}