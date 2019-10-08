package com.qf.dao;

import com.qf.entity.Replyblog;

public interface ReplyblogMapper {
    int deleteByPrimaryKey(Long rId);

    int insert(Replyblog record);

    int insertSelective(Replyblog record);

    Replyblog selectByPrimaryKey(Long rId);

    int updateByPrimaryKeySelective(Replyblog record);

    int updateByPrimaryKey(Replyblog record);
}