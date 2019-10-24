package com.qf.dao;

import com.qf.entity.Record;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> findRecord(long uid);
}