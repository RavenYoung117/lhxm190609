package com.qf.dao;

import com.qf.entity.Collection;

public interface CollectionMapper {
    int deleteByPrimaryKey(Long cId);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Long cId);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
}